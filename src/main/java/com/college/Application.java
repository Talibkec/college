package com.college;

import com.college.core.config.BeanConfig;
import com.college.core.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
@Configuration
@ComponentScan({"com.college.*"})
@Import({BeanConfig.class, WebConfig.class})
//@EnableWebMvc
@EnableScheduling
@EnableCaching
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
public class Application extends SpringBootServletInitializer {

    private static Class<Application> applicationClass = Application.class;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
        //test if works
        //testing it again
    }

}