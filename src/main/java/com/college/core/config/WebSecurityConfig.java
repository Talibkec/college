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

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

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

    // Same rules and order as the former WebSecurityConfigurerAdapter. Ant matchers keep the old matching semantics;
    // forwards/includes (JSP views) and error dispatches were never authorized before, so they stay permitted.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // PasswordResetController and SecurityServiceImpl set the SecurityContext by hand and rely on it
                // being stored in the session automatically (pre-Security-6 behaviour).
                .securityContext(sc -> sc.requireExplicitSave(false))
                .authorizeHttpRequests(auth -> auth
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR).permitAll()
                        .requestMatchers(antMatcher("/user/updatePassword*"), antMatcher("/user/savePassword*"), antMatcher("/updatePassword*"))
                        .hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                        .requestMatchers(antMatcher("/user/resetPassword"), antMatcher("/user/changePassword*")).permitAll()
                        .requestMatchers(antMatcher("/fstore/**")).hasAuthority("Faculty")
                        .requestMatchers(antMatcher("/store/**")).hasAuthority("SM")
                        .requestMatchers(antMatcher("/hod/**")).hasAuthority("HOD")
                        .requestMatchers(antMatcher("/sk/**")).hasAnyAuthority("SK", "SM")
                        .requestMatchers(antMatcher("/common/**")).hasAnyAuthority("SM", "Faculty")
                        .requestMatchers(antMatcher("/uploadfile/**")).hasAnyAuthority("Admin", "Faculty", "SM", "SK", "HOD")
                        .requestMatchers(antMatcher("/auth/uploadLabDoc/**")).hasAnyAuthority("Admin", "HOD")
                        .requestMatchers(antMatcher("/auth/api/uploadLabDoc/**")).hasAnyAuthority("Admin", "HOD")
                        .requestMatchers(antMatcher("/auth/**")).hasAnyAuthority("Admin")
                        .requestMatchers(antMatcher("/user/**")).hasAnyAuthority("Faculty")
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
