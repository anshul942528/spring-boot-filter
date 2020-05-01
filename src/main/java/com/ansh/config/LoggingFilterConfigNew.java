package com.ansh.config;

import com.ansh.filter.RequestResponseLoggingFilterNew;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class LoggingFilterConfigNew {

    @Value("${logging.filter.new.include.urls}")
    private String[] includeUrls;

    @Value("${logging.filter.new.exclude.urls}")
    private String[] excludeUrls;

    @Bean
    public FilterRegistrationBean loggingFilterNew() {
        return createFilterRegistration(new RequestResponseLoggingFilterNew(excludeUrls), 2);
    }

    public FilterRegistrationBean createFilterRegistration(Filter filter, int order) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setAsyncSupported(true);
        registration.setOrder(order);
        registration.addUrlPatterns(includeUrls);
        return registration;
    }
}
