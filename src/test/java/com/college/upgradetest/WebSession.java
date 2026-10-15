package com.college.upgradetest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Minimal browser-like HTTP client: keeps cookies (JSESSIONID), never follows redirects automatically.
 * Built on HttpURLConnection only, so it works unchanged on every Spring Boot / Java version of the upgrade.
 */
public class WebSession {

    private final String baseUrl;
    private final Map<String, String> cookies = new LinkedHashMap<>();

    public WebSession(int port) {
        this.baseUrl = "http://localhost:" + port;
    }

    public Response get(String path) throws IOException {
        return send("GET", path, null, null);
    }

    /** GET and follow redirects (max 5), like a browser. */
    public Response getFollow(String path) throws IOException {
        Response r = get(path);
        for (int i = 0; i < 5 && r.isRedirect(); i++) {
            r = get(r.redirectPath());
        }
        return r;
    }

    public Response postForm(String path, String... nameValuePairs) throws IOException {
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < nameValuePairs.length; i += 2) {
            if (body.length() > 0) body.append('&');
            body.append(URLEncoder.encode(nameValuePairs[i], "UTF-8")).append('=')
                    .append(URLEncoder.encode(nameValuePairs[i + 1], "UTF-8"));
        }
        return send("POST", path, "application/x-www-form-urlencoded", body.toString().getBytes(StandardCharsets.UTF_8));
    }

    /** Multipart POST. {@code fields} are text fields; {@code files} maps field name to (file name, bytes). */
    public Response postMultipart(String path, Map<String, String> fields, Map<String, FilePart> files) throws IOException {
        String boundary = "----upgradetest" + UUID.randomUUID().toString().replace("-", "");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (Map.Entry<String, String> f : fields.entrySet()) {
            write(out, "--" + boundary + "\r\n");
            write(out, "Content-Disposition: form-data; name=\"" + f.getKey() + "\"\r\n\r\n");
            write(out, f.getValue() + "\r\n");
        }
        for (Map.Entry<String, FilePart> f : files.entrySet()) {
            write(out, "--" + boundary + "\r\n");
            write(out, "Content-Disposition: form-data; name=\"" + f.getKey() + "\"; filename=\"" + f.getValue().fileName + "\"\r\n");
            write(out, "Content-Type: " + f.getValue().contentType + "\r\n\r\n");
            out.write(f.getValue().bytes);
            write(out, "\r\n");
        }
        write(out, "--" + boundary + "--\r\n");
        return send("POST", path, "multipart/form-data; boundary=" + boundary, out.toByteArray());
    }

    /** Logs in through the Spring Security form (fields {@code username}/{@code password}). */
    public Response login(String username, String password) throws IOException {
        get("/login"); // obtain a session first, like a browser
        return postForm("/login", "username", username, "password", password);
    }

    private static void write(OutputStream out, String s) throws IOException {
        out.write(s.getBytes(StandardCharsets.UTF_8));
    }

    private Response send(String method, String path, String contentType, byte[] body) throws IOException {
        HttpURLConnection c = (HttpURLConnection) new URL(path.startsWith("http") ? path : baseUrl + path).openConnection();
        c.setInstanceFollowRedirects(false);
        c.setRequestMethod(method);
        c.setConnectTimeout(10000);
        c.setReadTimeout(60000);
        if (!cookies.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> e : cookies.entrySet()) {
                if (sb.length() > 0) sb.append("; ");
                sb.append(e.getKey()).append('=').append(e.getValue());
            }
            c.setRequestProperty("Cookie", sb.toString());
        }
        if (body != null) {
            c.setDoOutput(true);
            c.setRequestProperty("Content-Type", contentType);
            try (OutputStream os = c.getOutputStream()) {
                os.write(body);
            }
        }
        int status = c.getResponseCode();
        List<String> setCookies = c.getHeaderFields().get("Set-Cookie");
        if (setCookies != null) {
            for (String sc : setCookies) {
                String pair = sc.split(";", 2)[0];
                int eq = pair.indexOf('=');
                if (eq > 0) cookies.put(pair.substring(0, eq).trim(), pair.substring(eq + 1).trim());
            }
        }
        byte[] bytes;
        try (InputStream in = status >= 400 ? c.getErrorStream() : c.getInputStream()) {
            bytes = in == null ? new byte[0] : readAll(in);
        }
        return new Response(status, c.getHeaderField("Location"), c.getContentType(), bytes);
    }

    private static byte[] readAll(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[8192];
        int n;
        while ((n = in.read(buf)) > 0) out.write(buf, 0, n);
        return out.toByteArray();
    }

    public static class FilePart {
        final String fileName;
        final String contentType;
        final byte[] bytes;

        public FilePart(String fileName, String contentType, byte[] bytes) {
            this.fileName = fileName;
            this.contentType = contentType;
            this.bytes = bytes;
        }
    }

    public static class Response {
        public final int status;
        public final String location;
        public final String contentType;
        public final byte[] bytes;

        Response(int status, String location, String contentType, byte[] bytes) {
            this.status = status;
            this.location = location;
            this.contentType = contentType;
            this.bytes = bytes;
        }

        public String body() {
            return new String(bytes, StandardCharsets.UTF_8);
        }

        public boolean isRedirect() {
            return status >= 300 && status < 400 && location != null;
        }

        /** Redirect target without scheme/host, e.g. {@code /login}. */
        public String redirectPath() {
            if (location == null) return null;
            return location.replaceFirst("^https?://[^/]+", "");
        }

        @Override
        public String toString() {
            String b = body();
            return status + (location != null ? " -> " + location : "") + " [" + contentType + "] "
                    + (b.length() > 300 ? b.substring(0, 300) + "..." : b);
        }
    }
}
