package com.udemy.spring.security.service.session11.controller;


import com.udemy.spring.security.service.session11.model.Loan;
import com.udemy.spring.security.service.session11.repo.CustomerRepository;
import com.udemy.spring.security.service.session11.repo.LoanRepository;
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
  final CustomerRepository customerRepository;


  @GetMapping("/myLoans")
  @PostAuthorize("hasRole('ROOT')") // change the role
  public List<Loan> getLoanDetails(@RequestParam String email) {
    var customers = customerRepository.findByEmail(email);
    if (customers != null && !customers.isEmpty()) {
      var loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customers.get(0).getCustomerId());
      if (loans != null ) {
        return loans;
      }
    }
    return null;
  }
}
