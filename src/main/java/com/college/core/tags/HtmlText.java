package com.college.core.tags;

/** Output of editor-entered texts in pages. */
public final class HtmlText {

    private HtmlText() {
    }

    /**
     * Escapes the characters that could start markup or end an attribute ({@code < > " '}). {@code &} is left as it is,
     * so character references keep working ("&copy 2018", "Pin &#8211; 854106" render as before, byte for byte);
     * a lone {@code &} can only form a character reference, never a tag or an attribute.
     */
    public static String escapeMarkup(String text) {
        if (text == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(text.length() + 16);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            switch (c) {
                case '<': sb.append("&lt;"); break;
                case '>': sb.append("&gt;"); break;
                case '"': sb.append("&#34;"); break;
                case '\'': sb.append("&#39;"); break;
                default: sb.append(c);
            }
        }
        return sb.toString();
    }
}
