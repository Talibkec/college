package com.college.core.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Upgrade compatibility: the pages link stored files with the file's extension, e.g. {@code /42/slideImage.JPG},
 * {@code /384/documents.pdf}, {@code /7/image.png}. Spring Boot 1.3 matched those URLs by suffix and sent the type of the
 * extension; Spring 6 has no suffix matching (the controllers now map {@code .{ext}} explicitly). This filter sets the
 * content type from the extension for those URLs again, for a fixed list of image, PDF and Office types only (anything
 * else stays a download), so pictures and PDFs open in the browser as before.
 */
@Component
public class FileExtensionContentTypeFilter extends OncePerRequestFilter {
    private static final Pattern FILE_URL =
            Pattern.compile("/[0-9]+/(?:image|documents|galleryImage|achievementImage|slideImage)\\.([A-Za-z0-9]{1,5})");
    private static final Map<String, String> TYPES = new HashMap<>();

    static {
        TYPES.put("jpg", "image/jpeg");
        TYPES.put("jpeg", "image/jpeg");
        TYPES.put("png", "image/png");
        TYPES.put("gif", "image/gif");
        TYPES.put("webp", "image/webp");
        TYPES.put("pdf", "application/pdf");
        TYPES.put("doc", "application/msword");
        TYPES.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        TYPES.put("xls", "application/vnd.ms-excel");
        TYPES.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        TYPES.put("ppt", "application/vnd.ms-powerpoint");
        TYPES.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !FILE_URL.matcher(request.getRequestURI().substring(request.getContextPath().length())).matches();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        Matcher m = FILE_URL.matcher(request.getRequestURI().substring(request.getContextPath().length()));
        if (m.matches()) {
            // a Content-Type set before the controller is kept by Spring's message converters
            String type = TYPES.get(m.group(1).toLowerCase(Locale.ROOT));
            response.setContentType(type != null ? type : "application/octet-stream");
        }
        chain.doFilter(request, response);
    }
}
