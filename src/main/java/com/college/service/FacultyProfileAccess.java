package com.college.service;

import com.college.repository.FacultyRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

/**
 * Who may change a faculty profile (details, designation, headings and the content blocks of the profile page):
 * the faculty member themselves and Admin (decision 2026-10-28). Nobody else, not the HOD or other faculty.
 */
@Service
public class FacultyProfileAccess {
    private final FacultyRepository facultyRepository;

    public FacultyProfileAccess(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public boolean canEdit(Authentication authentication, Long facultyId) {
        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken || facultyId == null) {
            return false;
        }
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if ("Admin".equals(authority.getAuthority())) {
                return facultyRepository.existsById(facultyId);
            }
        }
        String owner = facultyRepository.findUsernameById(facultyId);
        return owner != null && owner.equalsIgnoreCase(authentication.getName());
    }
}
