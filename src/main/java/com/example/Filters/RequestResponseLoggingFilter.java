package com.example.Filters;

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
