package com.college.core.config;

import com.college.FacultyHelper;
import firebase.bean.FirebaseSetup;
import com.college.NoticeBoardHelper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class BeanConfig {

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver() {
            // Many controllers return "/x.jsp" on top of the "/jsp/" prefix. Tomcat 8.0 collapsed the resulting "//",
            // Tomcat 8.5+ answers 404, so collapse it here (upgrade fix, no behaviour change).
            @Override
            protected AbstractUrlBasedView buildView(String viewName) throws Exception {
                AbstractUrlBasedView view = super.buildView(viewName);
                view.setUrl(view.getUrl().replaceAll("/{2,}", "/"));
                return view;
            }
        };
        resolver.setPrefix("/jsp/");
        //resolver.setSuffix (".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Bean(name = "facultyHelper")
    public FacultyHelper facultyHelper() {
        FacultyHelper facultyHelper = new FacultyHelper();
        return facultyHelper;
    }

    @Bean(name = "noticeBoardHelper")
    public NoticeBoardHelper noticeBoardHelper() {
        NoticeBoardHelper noticeBoardHelper = new NoticeBoardHelper();
        return noticeBoardHelper;
    }

    @Bean(name = "FirebaseAPI")
    public FirebaseSetup setupFireBase() {
        FirebaseSetup firebaseSetup = new FirebaseSetup();
        return firebaseSetup;
    }

    @Bean(name = "modelMapper")
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

}
