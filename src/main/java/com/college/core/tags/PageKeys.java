package com.college.core.tags;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Page keys of page blocks: derived from the page URL ("/about/history" → "about.history", "/" → "home") and mapped to
 * the content area whose editors may change the page (docs/DYNAMIC_CONTENT_FINDINGS.md §1.3.3).
 */
public final class PageKeys {
    private static final Pattern KEY = Pattern.compile("[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+){0,4}");
    private static final Set<String> SECTIONS = new HashSet<>(Arrays.asList("home", "about", "academic", "approval",
            "category", "department", "facilities", "gallery", "nba", "rti", "trainingplacement", "legal", "page", "faculty"));

    /**
     * The shared lab page {@code /academic/lab?dept_id=<n>} is one page per department: key {@code academic.lab.<n>},
     * edited by that department. {@code n} is the {@code lab_document.dep_id} the department sidebars link to (findings
     * §7: ECE/ECE-VLSI link to 4 = EEE, FP links to 5 = ASH). Moves to {@code department_site.lab_dept_id} with the
     * departments step.
     */
    private static final Map<String, String> LAB_SITES = new HashMap<>();
    private static final Pattern LAB_KEY = Pattern.compile("academic\\.lab\\.([0-9]{1,4})");
    /** Faculty profile {@code /facultyDetails?facultyId=<n>}: key {@code faculty.<n>}, area {@code faculty.<n>}. */
    private static final Pattern FACULTY_KEY = Pattern.compile("faculty\\.([0-9]{1,10})");

    static {
        LAB_SITES.put("1", "cse");
        LAB_SITES.put("2", "civil");
        LAB_SITES.put("3", "mechanical");
        LAB_SITES.put("4", "eee");
        LAB_SITES.put("5", "ash");
        LAB_SITES.put("7", "mechandsmartmanufacturing");
    }

    private PageKeys() {
    }

    /**
     * Like {@link #fromPath(String, String)}; {@code facultyId} is the request parameter {@code facultyId} (faculty
     * profile {@code /facultyDetails} only).
     */
    public static String fromPath(String path, String deptId, String facultyId) {
        if (path != null && path.replaceAll("/+$", "").equals("/facultyDetails")) {
            return facultyId != null && facultyId.matches("[0-9]{1,10}") ? "faculty." + facultyId : null;
        }
        return fromPath(path, deptId);
    }

    /** Like {@link #fromPath(String)}; {@code deptId} is the request parameter {@code dept_id} (lab page only). */
    public static String fromPath(String path, String deptId) {
        String key = fromPath(path);
        if ("academic.lab".equals(key) && deptId != null && deptId.matches("[0-9]{1,4}")) {
            return key + "." + deptId;
        }
        return key;
    }

    /** The page key of a request path (without context path), or null if the path cannot have blocks. */
    public static String fromPath(String path) {
        if (path == null) return null;
        String p = path.replaceAll("/+$", "");
        if (p.isEmpty()) return "home";
        if (!p.startsWith("/")) return null;
        String key = p.substring(1).replace('/', '.');
        return valid(key) ? key : null;
    }

    /** True for well-formed keys of the public sections of the site. */
    public static boolean valid(String key) {
        return key != null && KEY.matcher(key).matches() && SECTIONS.contains(key.split("\\.")[0]);
    }

    /** URL of the page ("about.history" → "/about/history", "home" → "/"). */
    public static String url(String key) {
        java.util.regex.Matcher lab = key == null ? null : LAB_KEY.matcher(key);
        if (lab != null && lab.matches()) return "/academic/lab?dept_id=" + lab.group(1);
        java.util.regex.Matcher faculty = key == null ? null : FACULTY_KEY.matcher(key);
        if (faculty != null && faculty.matches()) return "/facultyDetails?facultyId=" + faculty.group(1);
        return "home".equals(key) ? "/" : "/" + key.replace('.', '/');
    }

    /** The content area whose editors may add blocks to the page, or null. */
    public static String area(String key) {
        if (!valid(key)) return null;
        String[] parts = key.split("\\.");
        String page = parts.length > 1 ? parts[1].toLowerCase() : "";
        switch (parts[0]) {
            case "home":
                return "home";
            case "department":
                return parts.length > 1 ? "department." + parts[1] : null;
            case "about":
                return in(page, "staff", "allotment", "administration") ? "office" : "about";
            case "academic":
                if (in(page, "calender", "syllabus", "attendance", "universityresults")) return "exam";
                if (in(page, "holidays")) return "office";
                if (in(page, "paymenttutorial")) return "fees";
                if (in(page, "lab") && parts.length == 3) {
                    String site = LAB_SITES.get(parts[2]);
                    return site == null ? "facilities" : "department." + site;
                }
                if (in(page, "lab", "labs")) return "facilities";
                return "academic";
            case "facilities":
                if (in(page, "bank")) return "fees";
                if (in(page, "centrallibrary")) return "library";
                if (in(page, "hostels")) return "hostel";
                if (in(page, "club", "clubs", "sportsfacilities", "gymnasium")) return "activities";
                if (in(page, "placementbrochure", "startupcell")) return "tp";
                if (in(page, "downloads")) return "downloads";
                if (in(page, "contactus")) return "site";
                return "facilities";
            case "trainingplacement":
                return "tp";
            case "approval":
                return "approval";
            case "rti":
                return "rti";
            case "gallery":
                return "gallery";
            case "nba":
                return "nba";
            case "legal":
                if (in(page, "refundandcancellation", "feestructure")) return "fees";
                if (in(page, "contactus")) return "site";
                return "legal";
            case "faculty": // own profile page: the faculty member and Admin (FacultyProfileAccess)
                return FACULTY_KEY.matcher(key).matches() ? key : null;
            case "page": // new pages /page/<name>, created through the main menu
                return parts.length == 2 ? "menu" : null;
            case "category":
                if (in(page, "notices", "tenders")) return "notices";
                if (in(page, "kecwebteam")) return "system";
                return "site";
            default:
                return null;
        }
    }

    private static boolean in(String page, String... names) {
        for (String name : names) {
            if (name.equals(page)) return true;
        }
        return false;
    }
}
