package com.college.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Who may upload and delete the lab documents of a department (decision 2026-10-28): Admin for every department, an
 * HOD only for the own department, i.e. when the HOD may edit that department's lab page {@code academic.lab.<n>}
 * (department_site.hod_username or the HOD's own faculty department). Before, every HOD could do it for all
 * departments.
 */
@Service
public class LabDocumentAccess {
    /** lab_document.dep_id values offered by the upload form. */
    public static final int[] DEPARTMENTS = {1, 2, 3, 4, 5, 6, 7, 8};

    private final PageBlockService pageBlockService;

    public LabDocumentAccess(PageBlockService pageBlockService) {
        this.pageBlockService = pageBlockService;
    }

    public boolean canManage(Authentication authentication, Integer deptId) {
        if (authentication == null || deptId == null) return false;
        if (has(authentication, "Admin")) return true;
        return has(authentication, "HOD") && "E".equals(pageBlockService.access(authentication, "academic.lab." + deptId));
    }

    /** The departments (as text, for the JSP) whose lab documents the user may upload. */
    public Set<String> departments(Authentication authentication) {
        Set<String> allowed = new LinkedHashSet<>();
        for (int dept : DEPARTMENTS) {
            if (canManage(authentication, dept)) allowed.add(String.valueOf(dept));
        }
        return allowed;
    }

    private static boolean has(Authentication authentication, String role) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (role.equals(authority.getAuthority())) return true;
        }
        return false;
    }
}
