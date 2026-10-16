package com.college.upgradetest;

import org.junit.Assume;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;

/**
 * Refactoring safety net: compares the full response body of every URL in the page baselines
 * ({@code anonymous-pages.txt}, {@code role-pages.txt}) plus {@code refactor-pages.txt} (role-dependent views and
 * endpoints with manual access checks) with a snapshot taken before the refactoring.
 * {@link PagesBaselineTest} only checks status and kind; this test checks that pages look the same.
 *
 * <p>Snapshots contain real data from the local DB copy, so they are kept out of git ({@code /html-snapshots/}).
 * Skipped unless requested. Run it on its own, so that rows left by other tests do not show up in pages:
 * <pre>
 * mvn test -Dtest=HtmlSnapshotTest -Dupgradetest.snapshot=record    # before the change
 * mvn test -Dtest=HtmlSnapshotTest -Dupgradetest.snapshot=compare   # after the change
 * </pre>
 * Optional {@code -Dupgradetest.snapshot.dir=...} (default {@code html-snapshots}).
 */
public class HtmlSnapshotTest extends AbstractAppTest {

    @Test
    public void everyPageLooksAsBeforeTheRefactoring() throws Exception {
        String mode = System.getProperty("upgradetest.snapshot", "");
        Assume.assumeTrue("snapshot test not requested", mode.equals("record") || mode.equals("compare"));
        boolean record = mode.equals("record");
        Path dir = Paths.get(System.getProperty("upgradetest.snapshot.dir", "html-snapshots"));
        Files.createDirectories(dir);

        String facultyId = String.valueOf(data.facultyId(TestData.FACULTY.username));
        Map<String, WebSession> sessions = new HashMap<>();
        List<String> mismatches = new ArrayList<>();
        int count = 0;

        for (String[] entry : entries()) {
            String role = entry[0];
            String path = entry[1];
            WebSession s = sessions.get(role);
            if (s == null) {
                s = "ANON".equals(role) ? anonymous() : loginAs(user(role));
                sessions.put(role, s);
            }
            WebSession.Response r;
            try {
                r = s.get(path.replace("{facultyId}", facultyId));
            } catch (Exception e) {
                continue; // reported by PagesBaselineTest
            }
            String snapshot = "status " + r.status + "\ncontent-type " + r.contentType
                    + "\ncontent-disposition " + r.contentDisposition + "\n" + normalize(r, facultyId);
            Path file = dir.resolve((role + "_" + path).replaceAll("[^A-Za-z0-9._-]", "_") + ".txt");
            count++;
            if (record) {
                Files.writeString(file, snapshot);
            } else if (!Files.exists(file)) {
                mismatches.add(role + " " + path + ": no snapshot recorded");
            } else {
                String before = Files.readString(file);
                if (!before.equals(snapshot) && !(UNORDERED_PAGES.matcher(path).find() && sameLines(before, snapshot))) {
                    mismatches.add(role + " " + path + ": " + firstDifference(before, snapshot));
                    Files.writeString(Paths.get(file + ".actual"), snapshot);
                }
            }
        }
        System.out.println((record ? "Recorded " : "Compared ") + count + " page snapshots in " + dir.toAbsolutePath());
        if (!mismatches.isEmpty()) {
            fail(mismatches.size() + " page(s) differ from the snapshot (see *.actual next to the snapshots):\n  "
                    + String.join("\n  ", mismatches));
        }
    }

    /**
     * Faculty lists come from queries without ORDER BY (MyISAM physical order). The test faculty row is re-created
     * on every run and may land in another slot, so on these pages only the order of the lines may differ.
     */
    private static final java.util.regex.Pattern UNORDERED_PAGES = java.util.regex.Pattern.compile("^/(faculty\\?|facultyDetails\\?)");

    private static boolean sameLines(String a, String b) {
        List<String> la = new ArrayList<>(List.of(a.split("\n", -1)));
        List<String> lb = new ArrayList<>(List.of(b.split("\n", -1)));
        java.util.Collections.sort(la);
        java.util.Collections.sort(lb);
        return la.equals(lb);
    }

    /** Text bodies with run-specific values masked; binary bodies as a hash. */
    private static String normalize(WebSession.Response r, String facultyId) throws Exception {
        String type = r.contentType == null ? "" : r.contentType;
        boolean text = type.isEmpty() || type.startsWith("text/") || type.contains("json") || type.contains("javascript");
        if (r.location != null) {
            return "location " + r.redirectPath().replaceAll(";jsessionid=[^?]*", "") + "\n";
        }
        if (!text) {
            return "binary " + type + " sha256=" + HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(r.bytes)) + "\n";
        }
        return r.body()
                .replaceAll(";jsessionid=[0-9A-Fa-f]+", "")
                // only where the test faculty id appears (facultyId=157, value="157", "/157/image"); a looser pattern
                // also matches inside SVG paths and base64 image data
                .replaceAll("(?<=facultyId=)" + facultyId + "(?!\\d)", "{facultyId}")
                .replaceAll("(?<=value=\")" + facultyId + "(?=\")", "{facultyId}")
                .replaceAll("(?<=\"/)" + facultyId + "(?=/(image|documents)\")", "{facultyId}");
    }

    private static String firstDifference(String a, String b) {
        String[] la = a.split("\n", -1);
        String[] lb = b.split("\n", -1);
        for (int i = 0; i < Math.max(la.length, lb.length); i++) {
            String x = i < la.length ? la[i] : "<end>";
            String y = i < lb.length ? lb[i] : "<end>";
            if (!x.equals(y)) {
                return "line " + (i + 1) + ": [" + abbreviate(x) + "] -> [" + abbreviate(y) + "]";
            }
        }
        return "?";
    }

    private static String abbreviate(String s) {
        s = s.trim();
        return s.length() > 160 ? s.substring(0, 160) + "..." : s;
    }

    private List<String[]> entries() throws Exception {
        List<String[]> result = new ArrayList<>();
        for (String line : readLines("/upgradetest/anonymous-pages.txt")) {
            String[] p = fields(line);
            if (p != null) result.add(new String[]{"ANON", p[0]});
        }
        for (String line : readLines("/upgradetest/role-pages.txt")) {
            String[] p = fields(line);
            if (p != null) result.add(new String[]{p[0], p[1]});
        }
        for (String line : readLines("/upgradetest/refactor-pages.txt")) {
            String[] p = fields(line);
            if (p != null) result.add(new String[]{p[0], p[1]});
        }
        return result;
    }

    private static String[] fields(String line) {
        String l = line.contains("#") ? line.substring(0, line.indexOf('#')) : line;
        return l.trim().isEmpty() ? null : l.trim().split("\\s+");
    }

    private static TestData.TestUser user(String role) {
        switch (role) {
            case "ADMIN": return TestData.ADMIN;
            case "HOD": return TestData.HOD;
            case "FACULTY": return TestData.FACULTY;
            case "SM": return TestData.SM;
            case "SK": return TestData.SK;
            default: throw new IllegalArgumentException("unknown role " + role);
        }
    }

    private List<String> readLines(String resource) throws Exception {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(resource), StandardCharsets.UTF_8))) {
            String l;
            while ((l = br.readLine()) != null) lines.add(l);
        }
        return lines;
    }
}
