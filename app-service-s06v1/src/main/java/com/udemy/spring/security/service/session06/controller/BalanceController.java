package com.udemy.spring.security.service.session06.controller;


import com.udemy.spring.security.service.session06.model.AccountTransaction;
import com.udemy.spring.security.service.session06.repo.AccountTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BalanceController {


  final AccountTransactionRepository accountTransactionRepository;

  @GetMapping("/myBalance")
  public List<AccountTransaction> getBalanceDetails(@RequestParam int id) {
    return this.accountTransactionRepository.
            findByCustomerIdOrderByTransactionDtDesc(id);
  }
}
