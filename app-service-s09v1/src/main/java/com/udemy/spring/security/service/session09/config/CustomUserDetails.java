package com.udemy.spring.security.service.session09.config;


import com.udemy.spring.security.service.session09.repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

//@Service
@AllArgsConstructor
public class CustomUserDetails implements UserDetailsService {

  final CustomerRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    var listOfCustomers = this.repository.findByEmail(username);

    if (listOfCustomers.size() == 0)
      throw new UsernameNotFoundException("User details not found for the user: " + username);

    var customer = listOfCustomers.stream().findFirst().get();
    //
    var userName = customer.getEmail();
    var password = customer.getPwd();
    var authority = customer.getRole();
    var auths = List.of(new SimpleGrantedAuthority(authority));
    //
    return new User(userName, password, auths);
  }
}
