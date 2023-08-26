package com.udemy.spring.security.service.session10.filter;

import com.udemy.spring.security.service.session10.constants.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;


@Slf4j
@Component
public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    log.info("JWTGenerator Filter starting");
    // get current authentication
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    //
    if (null != authentication) {
      var key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
      // issuer means, Person Or Organization who is issuing this JWT Token
      var jwt = Jwts.builder().setIssuer("KcyCloak").setSubject("JWT Token")
              .claim("username", authentication.getName())
              .claim("authorities", populateAuthorities(authentication.getAuthorities()))
              .setIssuedAt(new Date())
              .setExpiration(new Date((new Date()).getTime() + 30_000))
              .signWith(key).compact();
      //
      response.setHeader(SecurityConstants.JWT_HEADER, jwt);
    }
    //
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return !request.getServletPath().equals("/user");
  }

  private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
    var authoritiesSet = new HashSet<String>();
    for (GrantedAuthority authority : collection) {
      authoritiesSet.add(authority.getAuthority());
    }
    return String.join(",", authoritiesSet);
  }
}
