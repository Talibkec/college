package com.college.upgradetest;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Requests every page of the application and compares status + kind of response with the baselines recorded on
 * the pre-upgrade version:
 * <ul>
 *   <li>{@code upgradetest/anonymous-pages.txt}: every anonymous GET URL (~300),</li>
 *   <li>{@code upgradetest/role-pages.txt}: pages behind login, per role, incl. access denied / login redirects.</li>
 * </ul>
 * Kinds: PAGE = complete HTML page (contains {@code </html>}, i.e. the JSP incl. footer rendered), BODY = other
 * non-empty content, EMPTY = empty body, REDIRECT:&lt;path&gt; for 3xx.
 *
 * <p>Re-record (only on purpose, e.g. after an approved behaviour change):
 * {@code mvn test -Dtest=PagesBaselineTest -Dupgradetest.record=true}; results go to {@code target/*.recorded.txt}
 * for review and must be copied over the baseline by hand.
 */
public class PagesBaselineTest extends AbstractAppTest {

    @Test
    public void everyAnonymousPageBehavesAsBeforeTheUpgrade() throws Exception {
        check("anonymous-pages.txt", false);
    }

    @Test
    public void everyRolePageBehavesAsBeforeTheUpgrade() throws Exception {
        check("role-pages.txt", true);
    }

    private void check(String file, boolean withRole) throws Exception {
        boolean record = Boolean.getBoolean("upgradetest.record");
        Map<String, WebSession> sessions = new HashMap<>();
        String facultyId = String.valueOf(data.facultyId(TestData.FACULTY.username));
        List<String> recorded = new ArrayList<>();
        List<String> mismatches = new ArrayList<>();

        for (String line : readLines("/upgradetest/" + file)) {
            if (line.trim().isEmpty() || line.startsWith("#")) {
                recorded.add(line);
                continue;
            }
            String comment = line.contains("#") ? "  " + line.substring(line.indexOf('#')) : "";
            String[] parts = (line.contains("#") ? line.substring(0, line.indexOf('#')) : line).trim().split("\\s+");
            int i = 0;
            String role = withRole ? parts[i++] : "ANON";
            String path = parts[i++];
            String expected = parts.length >= i + 2 ? parts[i] + " " + parts[i + 1] : "<no baseline>";

            String actual;
            try {
                WebSession s = sessions.get(role);
                if (s == null) {
                    s = "ANON".equals(role) ? anonymous() : loginAs(user(role));
                    sessions.put(role, s);
                }
                actual = describe(s.get(path.replace("{facultyId}", facultyId)));
            } catch (Exception e) {
                actual = "EXCEPTION " + e.getClass().getSimpleName();
            }
            String key = withRole ? role + " " + path : path;
            recorded.add(String.format("%-55s %s%s", key, actual, comment).replaceAll("\\s+$", ""));
            if (!expected.equals(actual)) {
                mismatches.add(key + ": expected [" + expected + "] but was [" + actual + "]");
            }
        }

        if (record) {
            File out = new File("target/" + file.replace(".txt", ".recorded.txt"));
            try (PrintWriter pw = new PrintWriter(out, "UTF-8")) {
                for (String l : recorded) pw.println(l);
            }
            System.out.println("Recorded baseline to " + out.getAbsolutePath());
            return;
        }
        if (!mismatches.isEmpty()) {
            fail(mismatches.size() + " page(s) in " + file + " behave differently than before the upgrade:\n  "
                    + String.join("\n  ", mismatches));
        }
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

    static String describe(WebSession.Response r) {
        if (r.isRedirect()) {
            return r.status + " REDIRECT:" + r.redirectPath().replaceAll(";jsessionid=[^?]*", "").replaceAll("\\d{2,}$", "{id}");
        }
        if (r.status != 200) {
            return r.status + " -";
        }
        String body = r.body();
        if (body.isEmpty()) return "200 EMPTY";
        if (body.contains("</html>")) return "200 PAGE";
        return "200 BODY";
    }

    private List<String> readLines(String resource) throws Exception {
        assertNotNull("missing " + resource, getClass().getResourceAsStream(resource));
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(resource), StandardCharsets.UTF_8))) {
            String l;
            while ((l = br.readLine()) != null) lines.add(l);
        }
        return lines;
    }
}
