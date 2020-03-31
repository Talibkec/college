package com.college.core.config;

import com.college.FacultyHelper;
import com.college.NoticeBoardHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class BeanConfig {

    @Bean
    public InternalResourceViewResolver setupViewResolver()  {
        InternalResourceViewResolver resolver =  new InternalResourceViewResolver();
        resolver.setPrefix ("/jsp/");
        //resolver.setSuffix (".jsp");
        resolver.setViewClass (JstlView.class);
        return resolver;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver
                = new CommonsMultipartResolver();
        return multipartResolver;
    }

    @Bean(name = "facultyHelper")
    public FacultyHelper facultyHelper(){
        FacultyHelper facultyHelper = new FacultyHelper();
        return facultyHelper;
    }

    @Bean(name = "noticeBoardHelper")
    public NoticeBoardHelper noticeBoardHelper(){
        NoticeBoardHelper noticeBoardHelper = new NoticeBoardHelper();
        return noticeBoardHelper;
    }

    @Bean(name = "FirebaseAPI")
    public  FirebaseSetup setupFireBase(){
        FirebaseSetup firebaseSetup = new FirebaseSetup();
        return firebaseSetup;
    }

}
