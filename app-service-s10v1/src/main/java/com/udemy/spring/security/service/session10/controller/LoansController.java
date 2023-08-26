package com.udemy.spring.security.service.session10.controller;


import com.udemy.spring.security.service.session10.model.Loan;
import com.udemy.spring.security.service.session10.repo.LoanRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class LoansController {


  final LoanRepository loanRepository;

  @GetMapping("/myLoans")
  @PostAuthorize("hasRole('ROOT')") // change the role
  public List<Loan> getLoanDetails(@RequestParam int id) {
    return this.loanRepository.findByCustomerIdOrderByStartDtDesc(id);
  }
}
