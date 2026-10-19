package com.college.core.tags;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * Upload check by file content (not by the browser's Content-Type): images and documents of page blocks.
 * SVG and HTML are never accepted (they could carry scripts).
 */
public final class FileTypes {

    private FileTypes() {
    }

    /** image/jpeg, image/png, image/gif or image/webp, or null. */
    public static String image(byte[] data) {
        if (starts(data, 0xFF, 0xD8, 0xFF)) return "image/jpeg";
        if (starts(data, 0x89, 'P', 'N', 'G', 0x0D, 0x0A, 0x1A, 0x0A)) return "image/png";
        if (ascii(data, 0, "GIF87a") || ascii(data, 0, "GIF89a")) return "image/gif";
        if (ascii(data, 0, "RIFF") && ascii(data, 8, "WEBP")) return "image/webp";
        return null;
    }

    /** application/pdf or an Office type (by content + extension), or null. */
    public static String document(byte[] data, String fileName) {
        String ext = extension(fileName);
        if (ascii(data, 0, "%PDF-")) return "application/pdf";
        if (starts(data, 'P', 'K', 3, 4)) { // Office Open XML (zip)
            switch (ext) {
                case "docx": return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                case "xlsx": return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                case "pptx": return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
                default: return null;
            }
        }
        if (starts(data, 0xD0, 0xCF, 0x11, 0xE0, 0xA1, 0xB1, 0x1A, 0xE1)) { // legacy Office (OLE)
            switch (ext) {
                case "doc": return "application/msword";
                case "xls": return "application/vnd.ms-excel";
                case "ppt": return "application/vnd.ms-powerpoint";
                default: return null;
            }
        }
        return null;
    }

    /** Shown inside the browser (images, PDF); everything else is downloaded. */
    public static boolean inline(String contentType) {
        return contentType != null && (contentType.startsWith("image/") || contentType.equals("application/pdf"));
    }

    /** A safe file name: letters, digits, space . _ ( ) -; at most 120 characters; keeps the extension. */
    public static String cleanName(String fileName, String fallback) {
        String name = fileName == null ? "" : fileName.replaceAll(".*[/\\\\]", "");
        name = name.replaceAll("[^A-Za-z0-9 ._()-]", "_").replaceAll("_+", "_").trim();
        if (name.isEmpty() || name.startsWith(".")) name = fallback + name;
        if (name.length() > 120) {
            String ext = extension(name);
            name = name.substring(0, 110 - Math.min(ext.length(), 9)) + (ext.isEmpty() ? "" : "." + ext);
        }
        return name;
    }

    static String extension(String fileName) {
        if (fileName == null || fileName.lastIndexOf('.') < 0) return "";
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase(Locale.ROOT);
    }

    private static boolean starts(byte[] data, int... prefix) {
        if (data == null || data.length < prefix.length) return false;
        for (int i = 0; i < prefix.length; i++) {
            if ((data[i] & 0xFF) != prefix[i]) return false;
        }
        return true;
    }

    private static boolean ascii(byte[] data, int offset, String text) {
        byte[] t = text.getBytes(StandardCharsets.US_ASCII);
        if (data == null || data.length < offset + t.length) return false;
        for (int i = 0; i < t.length; i++) {
            if (data[offset + i] != t[i]) return false;
        }
        return true;
    }
}
