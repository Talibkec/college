package com.college.validator;

import com.college.core.model.RegistrationForm;
import com.college.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * Checks of the registration form that Bean Validation cannot express: the user name must be free and the
 * password confirmation must match. (The field checks are constraints on {@link RegistrationForm}.)
 */
@Component
public class UserValidator implements Validator {
    private final UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationForm form = (RegistrationForm) o;
        if (userService.findByUsername(form.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
        if (!Objects.equals(form.getPasswordConfirm(), form.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
