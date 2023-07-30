package com.udemy.spring.security.service.session08.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@Slf4j
public class AuthoritiesLoggingAfterFilter implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest,
                       ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //
    if (null != authentication) {
      log.info("User {} is successfully authenticated ad has the following authorities {}",
              authentication.getName(), authentication.getAuthorities().toString());
      //
      filterChain.doFilter(servletRequest, servletResponse);
    }
  }
}
