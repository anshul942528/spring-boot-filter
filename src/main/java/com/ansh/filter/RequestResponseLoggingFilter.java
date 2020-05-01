package com.ansh.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    private String[] excludeUrls;

    public RequestResponseLoggingFilter(String[] excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Request : " + request.getRequestURI());
        filterChain.doFilter(request, response);
        log.info("Response : " + response.getStatus());
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for(int i=0; i < excludeUrls.length; i++){
            if(pathMatcher.match(excludeUrls[i], request.getServletPath()))
                return true;
        }
        return false;
    }
}
