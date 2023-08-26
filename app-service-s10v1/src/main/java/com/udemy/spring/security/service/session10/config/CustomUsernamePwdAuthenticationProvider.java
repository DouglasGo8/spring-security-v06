package com.udemy.spring.security.service.session10.config;


import com.udemy.spring.security.service.session10.model.Authority;
import com.udemy.spring.security.service.session10.repo.CustomerRepository;
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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
@AllArgsConstructor
public class CustomUsernamePwdAuthenticationProvider implements AuthenticationProvider {

  final CustomerRepository repo;
  final PasswordEncoder passwordEncoder;


  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {


    var username = authentication.getName();
    var password = authentication.getCredentials().toString();
    //
    var customers = repo.findByEmail(username);

    // Customer found
    if (customers.size() > 0) {
      if (passwordEncoder.matches(password, customers.get(0).getPwd())) {
        //var auths = new ArrayList<GrantedAuthority>();
        //auths.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
        return new UsernamePasswordAuthenticationToken(username, password,
                this.getGrantedAuthorities(customers.get(0).getAuthorities()));
      } else {
        throw new BadCredentialsException("Invalid Password");
      }
    }



    throw new BadCredentialsException("Not user registered with this details");
  }

  private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
    return authorities.stream()
            .flatMap(auth -> Stream.of(new SimpleGrantedAuthority(auth.getName())))
            .collect(Collectors.toList());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }
}
