package com.learn.valpack.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.core.config.Order;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
//@Order(1) or Order(2) etc based on how many filters we want
public class MsgLogFilter implements Filter 
{
	@Override
    public void init(FilterConfig filterConfig) throws ServletException 
	{
        log.info("Creating instance of MsgLogFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException 
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("Logging Request  {} : {}", request.getMethod(), request.getRequestURI());
        //call next filter in the filter chain
        filterChain.doFilter(request, response);
        log.info("Logging Response :{}", response.getContentType());
    }

    @Override
    public void destroy() 
    {
    	log.info("Destroying instance of MsgLogFilter");
    }	
}
