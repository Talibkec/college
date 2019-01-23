package com.college.service;

public interface SecurityService {
    public void autologin(String username, String password);
    public String findLoggedInUsername();
    public void getAuthorities();
}
