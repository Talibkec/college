package com.college.core.tags;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

import java.util.regex.Pattern;

/**
 * Sanitising of editor-entered page HTML (page sections are printed unescaped). Allows the markup the pages use today
 * (text formatting, lists, tables, images, links, Tailwind classes, inline styles from a CSS whitelist) and embedded
 * YouTube videos / Google Maps; removes scripts, event handlers, forms and javascript: links.
 */
public final class PageHtml {
    private static final Pattern EMBED = Pattern.compile(
            "https://(www\\.)?(youtube\\.com/embed/|youtube-nocookie\\.com/embed/|google\\.com/maps/embed)[^\\s\"'<>]*");

    /** A path on this website ("/jsp/academic/regulation.pdf"), not "//host" and without a scheme. */
    private static final Pattern LOCAL_URL = Pattern.compile("/(?![/\\\\])[^\\s\"'<>:]*");

    private static final PolicyFactory POLICY = new HtmlPolicyBuilder()
            .allowElements("p", "br", "hr", "strong", "b", "em", "i", "u", "s", "sub", "sup", "small", "span", "div",
                    "section", "blockquote", "pre", "code", "h1", "h2", "h3", "h4", "h5", "h6", "ul", "ol", "li", "dl", "dt",
                    "dd", "table", "thead", "tbody", "tfoot", "tr", "td", "th", "caption", "img", "a", "figure", "figcaption",
                    "address")
            .allowAttributes("class", "id", "title").globally()
            .allowStyling()
            .allowUrlProtocols("http", "https", "mailto", "tel")
            .allowAttributes("href", "target", "rel").onElements("a")
            .allowAttributes("src", "alt", "width", "height").onElements("img")
            .allowAttributes("colspan", "rowspan").onElements("td", "th")
            .allowElements("iframe")
            .allowAttributes("src").matching(EMBED).onElements("iframe")
            .allowAttributes("width", "height", "frameborder", "allow", "allowfullscreen", "loading").onElements("iframe")
            // PDFs of this website shown inside the page (e.g. /jsp/academic/regulation.pdf): local paths only
            .allowElements("object", "embed")
            .allowAttributes("data").matching(LOCAL_URL).onElements("object")
            .allowAttributes("src").matching(LOCAL_URL).onElements("embed")
            .allowAttributes("type").matching(Pattern.compile("application/pdf")).onElements("object", "embed")
            .allowAttributes("width", "height").onElements("object", "embed")
            .toFactory();

    private PageHtml() {
    }

    public static String sanitize(String html) {
        return html == null ? null : POLICY.sanitize(html);
    }
}
