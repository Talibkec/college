package com.college.core.config;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher.pathPattern;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() { // static: breaks the bean cycle via the success handler (Boot 2.6+)
        return new BCryptPasswordEncoder();
    }

    // Boot 1.x exposed the AuthenticationManager as a bean; Boot 2+ does not (used by SecurityServiceImpl).
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationSuccessHandler kecAuthenticationSuccessHandler() {
        return new KECUrlAuthenticationSuccessHandler();
    }

    // Same rules and order as the former WebSecurityConfigurerAdapter (path patterns: same meaning as the old ant patterns);
    // forwards/includes (JSP views) and error dispatches were never authorized before, so they stay permitted.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // PasswordResetController and SecurityServiceImpl set the SecurityContext by hand and rely on it
                // being stored in the session automatically (pre-Security-6 behaviour).
                .securityContext(sc -> sc.requireExplicitSave(false))
                // pages embed the site's own PDFs with <object>/<embed>; DENY (the default) would block them
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .authorizeHttpRequests(auth -> auth
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR).permitAll()
                        .requestMatchers(pathPattern("/user/updatePassword*"), pathPattern("/user/savePassword*"), pathPattern("/updatePassword*"))
                        .hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                        .requestMatchers(pathPattern("/user/resetPassword"), pathPattern("/user/changePassword*")).permitAll()
                        .requestMatchers(pathPattern("/fstore/**")).hasAuthority("Faculty")
                        .requestMatchers(pathPattern("/store/**")).hasAuthority("SM")
                        .requestMatchers(pathPattern("/hod/**")).hasAuthority("HOD")
                        .requestMatchers(pathPattern("/sk/**")).hasAnyAuthority("SK", "SM")
                        .requestMatchers(pathPattern("/common/**")).hasAnyAuthority("SM", "Faculty")
                        .requestMatchers(pathPattern("/uploadfile/**")).hasAnyAuthority("Admin", "Faculty", "SM", "SK", "HOD")
                        .requestMatchers(pathPattern("/auth/uploadLabDoc/**")).hasAnyAuthority("Admin", "HOD")
                        .requestMatchers(pathPattern("/auth/api/uploadLabDoc/**")).hasAnyAuthority("Admin", "HOD")
                        .requestMatchers(pathPattern("/auth/**")).hasAnyAuthority("Admin")
                        .requestMatchers(pathPattern("/user/**")).hasAnyAuthority("Faculty")
                        .anyRequest().permitAll())
                .formLogin(form -> form
                        .loginPage("/login").successHandler(kecAuthenticationSuccessHandler()))
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll());
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
