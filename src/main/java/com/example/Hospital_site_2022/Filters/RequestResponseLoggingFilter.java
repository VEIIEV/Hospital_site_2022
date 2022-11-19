package com.example.Hospital_site_2022.Filters;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//
//@Slf4j
//@WebFilter(urlPatterns = "/api/*", filterName = "RRLogFilter")
//public class RequestResponseLoggingFilter implements Filter {
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException
//    {
//        log.info("########## Initiating CustomURLFilter filter ##########");
//
//    }
//
//
//    @Override
//    public void doFilter(ServletRequest request,
//                         ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse= (HttpServletResponse) response;
//
//        log.info("Logging Request  {} : {}", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
//        log.info("Logging Response :{}", httpServletResponse.getStatus());
//
//        chain.doFilter(httpServletRequest, httpServletResponse);
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
