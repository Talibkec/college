package com.college.core.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class ControllerUtility {
    public static String getRole() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userRole = null;
        if (principal instanceof UserDetails) {
            UserDetails userDetails = ((UserDetails)principal);
            for (GrantedAuthority authority : userDetails.getAuthorities()) {
                userRole = authority.getAuthority();
            }

        }
        return userRole;
    }

}
