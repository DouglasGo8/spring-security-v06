package com.udemy.spring.security.service.session08.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AuthoritiesLoggingFilter implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest,
                       ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    log.info("Authentication Validation is in progress");
    //
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
