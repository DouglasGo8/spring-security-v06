package com.udemy.spring.security.service.session08.controller;


import com.udemy.spring.security.service.session08.model.Account;
import com.udemy.spring.security.service.session08.repo.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AccountController {

  final AccountRepository accountRepository;

  @GetMapping("/myAccount")
  public Account getAccountDetails(@RequestParam int id) {
    return this.accountRepository.findByCustomerId(id);
  }
}
