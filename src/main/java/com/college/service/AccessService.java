package com.college.service;

import com.college.core.entity.Role;
import com.college.core.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Role checks done inside controllers (show delete buttons, allow deletes on public URLs). They read the roles of
 * the logged-in user from the database, as the controllers did before, not from the session's authorities.
 */
@Service
public class AccessService {

    private final UserService userService;
    private final FacultyService facultyService;

    public AccessService(UserService userService, FacultyService facultyService) {
        this.userService = userService;
        this.facultyService = facultyService;
    }

    /** {@code true} if the user is logged in and has the role "Admin" or "HOD" (exact spelling). */
    public boolean isAdminOrHod(Authentication authentication) {
        if (authentication == null) {
            return false;
        }
        for (Role role : roles(authentication)) {
            if (Objects.equals(role.getName(), "Admin") || Objects.equals(role.getName(), "HOD")) {
                return true;
            }
        }
        return false;
    }

    /** {@code true} if the user is logged in and has the role "Admin" (any letter case). */
    public boolean isAdmin(Authentication authentication) {
        if (authentication == null) {
            return false;
        }
        for (Role role : roles(authentication)) {
            if ("Admin".equalsIgnoreCase(role.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * For the faculty list and delete faculty: 2 = "Admin" (any case), 1 = "HOD" (any case) of department
     * {@code deptId} (from the HOD's own faculty row), 0 = anyone else or not logged in.
     */
    public int facultyPageUserType(Authentication authentication, long deptId) {
        if (authentication == null) {
            return 0;
        }
        Iterable<Role> roles = roles(authentication);
        for (Role role : roles) {
            if ("Admin".equalsIgnoreCase(role.getName())) {
                return 2;
            }
        }
        for (Role role : roles) {
            if ("HOD".equalsIgnoreCase(role.getName())
                    && deptId == facultyService.getFaculty(authentication.getName()).getDepartmentId()) {
                return 1;
            }
        }
        return 0;
    }

    private Iterable<Role> roles(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return user.getRoles();
    }
}
