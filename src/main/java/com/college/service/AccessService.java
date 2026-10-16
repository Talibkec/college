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

    public AccessService(UserService userService) {
        this.userService = userService;
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

    private Iterable<Role> roles(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return user.getRoles();
    }
}
