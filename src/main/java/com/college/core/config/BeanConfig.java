package com.college.core.config;

import com.college.FacultyHelper;
import firebase.bean.FirebaseSetup;
import com.college.NoticeBoardHelper;
import com.college.service.FacultyDocumentsService;
import com.college.service.FacultyService;
import com.college.service.NoticeBoardService;
import com.college.service.RequestService;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ResourceLoader;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableConfigurationProperties(KecProperties.class)
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
    public FacultyHelper facultyHelper(FacultyService facultyService, FacultyDocumentsService facultyDocumentsService,
                                       RequestService requestService) {
        return new FacultyHelper(facultyService, facultyDocumentsService, requestService);
    }

    @Bean(name = "noticeBoardHelper")
    public NoticeBoardHelper noticeBoardHelper(NoticeBoardService noticeBoardService) {
        return new NoticeBoardHelper(noticeBoardService);
    }

    @Bean(name = "FirebaseAPI")
    public FirebaseSetup setupFireBase(ResourceLoader resourceLoader, KecProperties kecProperties) {
        return new FirebaseSetup(resourceLoader, kecProperties);
    }

    @Bean(name = "modelMapper")
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

}
