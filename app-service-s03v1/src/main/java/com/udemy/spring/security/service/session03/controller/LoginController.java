package com.udemy.spring.security.service.session03.controller;


import com.udemy.spring.security.service.session03.model.Customer;
import com.udemy.spring.security.service.session03.repo.CustomerRepository;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {
  final CustomerRepository repository;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
    try {
      var savedCustomer = this.repository.save(customer);
      return savedCustomer.getId() > 0 ?
              ResponseEntity.status(HttpStatus.CREATED)
                      .body("Given user details are successfully registered") :
              ResponseEntity.status(HttpStatus.CONFLICT)
                      .body("Given user details are error when registered");
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurs due to " + ex.getMessage());
    }
  }
}