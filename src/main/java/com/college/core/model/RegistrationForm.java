package com.college.core.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Form of /auth/registration (was bound to the User entity). Same rules as before: the field checks are Bean
 * Validation constraints, the duplicate user name and the password confirmation are checked by UserValidator.
 * Messages come from validation.properties ({NotEmpty} via Spring's message source, Size.userForm.* by code).
 */
public class RegistrationForm {

    @NotBlank(message = "{NotEmpty}")
    @Size(min = 3, max = 32)
    private String username;

    @NotBlank(message = "{NotEmpty}")
    @Size(min = 8, max = 32)
    private String password;

    private String passwordConfirm;

    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
