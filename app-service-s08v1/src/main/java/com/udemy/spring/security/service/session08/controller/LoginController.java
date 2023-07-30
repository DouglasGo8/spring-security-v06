package com.udemy.spring.security.service.session08.controller;


import com.udemy.spring.security.service.session08.model.Customer;
import com.udemy.spring.security.service.session08.repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class LoginController {

  final PasswordEncoder passwordEncoder;
  final CustomerRepository customerRepository;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
    Customer savedCustomer = null;
    ResponseEntity<String> response = null;
    try {
      String hashPwd = passwordEncoder.encode(customer.getPwd());
      customer.setPwd(hashPwd);
      customer.setCreatedDt(new Date(System.currentTimeMillis()));
      savedCustomer = this.customerRepository.save(customer);
      if (savedCustomer.getCustomerId() > 0) {
        response = ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Given user details are successfully registered");
      }
    } catch (Exception ex) {
      response = ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred due to " + ex.getMessage());
    }
    return response;
  }

  @RequestMapping("/user")
  public Customer getUserDetailsAfterLogin(Authentication authentication) {
    List<Customer> customers = customerRepository.findByEmail(authentication.getName());
    if (customers.size() > 0) {
      return customers.get(0);
    } else {
      return null;
    }

  }
}