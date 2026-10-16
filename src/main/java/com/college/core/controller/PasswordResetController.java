package com.college.core.controller;

import com.college.GenericResponse;
import com.college.core.entity.User;
import com.college.core.model.FacultyDTO;
import com.college.service.PasswordResetService;
import com.college.service.FacultyService;
import com.college.service.UserService;
import com.college.core.config.KecProperties;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

@Controller
public class PasswordResetController {

    private final FacultyService facultyService;
    private final UserService userService;
    private final JavaMailSender mailSender;
    private final KecProperties kecProperties;
    private final MessageSource messages;
    private final Environment env;
    private final PasswordResetService passwordResetService;

    public PasswordResetController(FacultyService facultyService,
            UserService userService,
            JavaMailSender mailSender,
            KecProperties kecProperties,
            MessageSource messages,
            Environment env,
            PasswordResetService passwordResetService) {
        this.facultyService = facultyService;
        this.userService = userService;
        this.mailSender = mailSender;
        this.kecProperties = kecProperties;
        this.messages = messages;
        this.env = env;
        this.passwordResetService = passwordResetService;
    }


    @RequestMapping(value = "/user/resetPassword",
            method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse resetPassword(HttpServletRequest request,
                                         @RequestParam("email") String userEmail) throws Exception {
        FacultyDTO facultyDTO = facultyService.findUserByEmail(userEmail);
        User user = null;
        if (facultyDTO != null)
            user = userService.findByUsername(facultyDTO.getUser().getUsername());

        if (user == null) {
            throw new Exception();
        }
        String token = UUID.randomUUID().toString();
        facultyService.createPasswordResetTokenForUser(user, token);
        mailSender.send(constructResetTokenEmail(getAppUrl(request),
                request.getLocale(), token, user, facultyDTO.getFacultyOfficialEmail()));
        return new GenericResponse(
                messages.getMessage("message.resetPasswordEmail", null,
                        request.getLocale()));
    }

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user, final String sendMail) {
        final String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, sendMail);

    }

    private SimpleMailMessage constructEmail(String subject, String body, String sendMail) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(sendMail);
        email.setFrom(kecProperties.mail().from());
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
    public String showChangePasswordPage(Locale locale, Model model,
                                         @RequestParam("id") long id, @RequestParam("token") String token) {
        String result = validatePasswordResetToken(id, token);
        if (result != null) {
            model.addAttribute("message",
                    messages.getMessage("auth.message." + result, null, locale));
            return "redirect:/login?lang=" + locale.getLanguage();
        }
        return "redirect:/jsp/updatePassword.jsp?lang=" + locale.getLanguage();
    }

    private String validatePasswordResetToken(long id, String token) {
        PasswordResetService.TokenCheck check = passwordResetService.checkToken(id, token);
        if (check.error() != null) {
            return check.error();
        }
        // temporary login that only allows saving a new password (see WebSecurityConfig)
        Authentication auth = new UsernamePasswordAuthenticationToken(
                check.user(), null, Arrays.asList(
                new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return null;
    }

    @RequestMapping(value = "/jsp/user/savePassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse savePassword(Locale locale, @RequestParam("newPassword") String newPassword) {

        if (newPassword == null || (newPassword.length() < 8 || newPassword.length() > 32)) {
            return new GenericResponse("Password must be between 8 to 32 characters.", "Invalid Password");
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setPassword(newPassword);
        userService.save(user);
        return new GenericResponse(
                messages.getMessage("message.resetPasswordSuc", null, locale));
    }
}

