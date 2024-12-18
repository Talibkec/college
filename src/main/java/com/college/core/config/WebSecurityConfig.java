package com.college.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler kecAuthenticationSuccessHandler() {
        return new KECUrlAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().authorizeRequests()
                .antMatchers("/user/updatePassword*", "/user/savePassword*", "/updatePassword*")

                .hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                .antMatchers("/user/resetPassword","/user/changePassword*").permitAll()
                .antMatchers("/fstore/**").hasAuthority("Faculty")
                .antMatchers("/store/**").hasAuthority("SM")
                .antMatchers("/hod/**").hasAuthority("HOD")
                .antMatchers("/sk/**").hasAnyAuthority("SK", "SM")
                .antMatchers("/common/**").hasAnyAuthority("SM", "Faculty")
                .antMatchers("/uploadfile/**").hasAnyAuthority("Admin", "Faculty", "SM", "SK", "HOD")
                .antMatchers("/auth/uploadLabDoc/**").hasAnyAuthority("Admin","HOD")
                .antMatchers("/auth/api/uploadLabDoc/**").hasAnyAuthority("Admin","HOD")
                .antMatchers("/auth/**").hasAnyAuthority("Admin")
                .antMatchers("/user/**").hasAnyAuthority("Faculty")
                .and()
                .formLogin()
                .loginPage("/login").successHandler(kecAuthenticationSuccessHandler())
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
