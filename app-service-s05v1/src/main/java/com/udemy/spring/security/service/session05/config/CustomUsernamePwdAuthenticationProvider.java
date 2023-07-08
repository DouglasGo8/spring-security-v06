package com.udemy.spring.security.service.session05.config;

import com.udemy.spring.security.service.session05.repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
@AllArgsConstructor
public class CustomUsernamePwdAuthenticationProvider implements AuthenticationProvider {

  final CustomerRepository repo;
  final PasswordEncoder passwordEncoder;


  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {


    var username = authentication.getName();
    var password = authentication.getCredentials().toString();

    var customers = repo.findByEmail(username);

    // Customer found
    if (customers.size() > 0) {
      if (passwordEncoder.matches(password, customers.get(0).getPassword())) {
        var auths = new ArrayList<GrantedAuthority>();
        auths.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
        return new UsernamePasswordAuthenticationToken(username, password, auths);
      } else {
        throw new BadCredentialsException("Invalid Password");
      }
    }

    throw new BadCredentialsException("Not user registered with this details");
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }
}
