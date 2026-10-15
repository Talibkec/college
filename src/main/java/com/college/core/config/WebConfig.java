package com.college.core.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverters;
import org.springframework.web.filter.UrlHandlerFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    }

    // Spring 7: builder API. The old List-based override replaced the default converters (406 for String/JSON
    // responses); appending to the fully built list keeps the pre-upgrade result (defaults + this converter last).
    @Override
    public void configureMessageConverters(HttpMessageConverters.ServerBuilder builder) {
        final ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        final List<MediaType> list = new ArrayList<>();
        list.add(MediaType.IMAGE_JPEG);
        list.add(MediaType.IMAGE_PNG);
        list.add(MediaType.IMAGE_GIF);
        list.add(MediaType.parseMediaType("application/pdf"));
        arrayHttpMessageConverter.setSupportedMediaTypes(list);
        builder.configureMessageConvertersList(converters -> converters.add(arrayHttpMessageConverter));
    }

    /**
     * Spring MVC 6 no longer matches "/about/history/" to "/about/history". Keep the old behaviour for links and
     * bookmarks with a trailing slash by stripping it before Security and MVC see the request.
     */
    @Bean
    public FilterRegistrationBean<UrlHandlerFilter> trailingSlashFilter() {
        FilterRegistrationBean<UrlHandlerFilter> bean = new FilterRegistrationBean<>(
                UrlHandlerFilter.trailingSlashHandler("/**").wrapRequest().build());
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
