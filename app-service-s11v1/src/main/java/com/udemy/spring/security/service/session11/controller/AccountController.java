package com.udemy.spring.security.service.session11.controller;


import com.udemy.spring.security.service.session11.model.Account;
import com.udemy.spring.security.service.session11.repo.AccountRepository;
import com.udemy.spring.security.service.session11.repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AccountController {

  final AccountRepository accountRepository;
  final CustomerRepository customerRepository;

  @GetMapping("/myAccount")
  public Account getAccountDetails(@RequestParam String email) {

    var customers = customerRepository.findByEmail(email);
    if (customers != null && !customers.isEmpty()) {
      var accounts = accountRepository.findByCustomerId(customers.get(0).getCustomerId());
      if (accounts != null) {
        return accounts;
      }
    }
    return null;

  }
}
