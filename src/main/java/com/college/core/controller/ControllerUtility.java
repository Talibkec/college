package com.college.core.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class ControllerUtility {

    public static List<String> getRole() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> userRole = new ArrayList<>();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = ((UserDetails)principal);
            for (GrantedAuthority authority : userDetails.getAuthorities()) {
                userRole.add(authority.getAuthority());
            }

        }
        return userRole;
    }

    public static String getUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = null;
        if (principal instanceof UserDetails) {
            userName= ((UserDetails) principal).getUsername();
        }
        return userName;
    }
}
