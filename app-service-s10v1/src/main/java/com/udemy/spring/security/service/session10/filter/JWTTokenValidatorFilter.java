package com.udemy.spring.security.service.session10.filter;

import com.udemy.spring.security.service.session10.constants.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Slf4j
@Component
public class JWTTokenValidatorFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    log.info("JWTValidatorFilter Filter starting");
    var jwt = request.getHeader(SecurityConstants.JWT_HEADER);
    if (null != jwt) {
      var key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
      var claims = Jwts.parserBuilder().setSigningKey(key)
              .build().parseClaimsJwt(jwt)
              .getBody();
      //
      var userName = String.valueOf(claims.get("username"));
      var authorities = String.valueOf(claims.get("authorities"));
      var auth = new UsernamePasswordAuthenticationToken(userName, null,
              AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
      //
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    //
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    return !request.getServletPath().equals("/user");
  }
}
