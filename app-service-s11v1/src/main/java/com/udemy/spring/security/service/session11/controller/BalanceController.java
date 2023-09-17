package com.udemy.spring.security.service.session11.controller;


import com.udemy.spring.security.service.session11.model.AccountTransaction;
import com.udemy.spring.security.service.session11.repo.AccountTransactionRepository;
import com.udemy.spring.security.service.session11.repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BalanceController {


  final CustomerRepository customerRepository;
  final AccountTransactionRepository accountTransactionRepository;

  @GetMapping("/myBalance")
  public List<AccountTransaction> getBalanceDetails(@RequestParam String email) {
    var customers = customerRepository.findByEmail(email);
    if (customers != null && !customers.isEmpty()) {
      var accountTransactions = accountTransactionRepository.
              findByCustomerIdOrderByTransactionDtDesc(customers.get(0).getCustomerId());
      if (accountTransactions != null) {
        return accountTransactions;
      }
    }
    return null;
  }

}
