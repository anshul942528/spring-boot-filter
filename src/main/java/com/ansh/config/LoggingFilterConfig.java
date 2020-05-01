package com.ansh.config;

import com.ansh.filter.RequestResponseLoggingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;

@Configuration
public class LoggingFilterConfig {

    @Value("${logging.filter.include.urls}")
    private String[] includeUrls;

    @Value("${logging.filter.exclude.urls}")
    private String[] excludeUrls;

    @Bean
    public FilterRegistrationBean loggingFilter() {
        return createFilterRegistration(new RequestResponseLoggingFilter(excludeUrls), 1);
    }

    public FilterRegistrationBean createFilterRegistration(Filter filter, int order) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setAsyncSupported(true);
        registration.setOrder(order);
        registration.addUrlPatterns(includeUrls);
        return registration;
    }
}
