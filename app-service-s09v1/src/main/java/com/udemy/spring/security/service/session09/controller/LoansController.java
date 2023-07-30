package com.udemy.spring.security.service.session09.controller;


import com.udemy.spring.security.service.session09.model.Loan;
import com.udemy.spring.security.service.session09.repo.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class LoansController {


  final LoanRepository loanRepository;

  @GetMapping("/myLoans")
  public List<Loan> getLoanDetails(@RequestParam int id) {
    return this.loanRepository.findByCustomerIdOrderByStartDtDesc(id);
  }
}
