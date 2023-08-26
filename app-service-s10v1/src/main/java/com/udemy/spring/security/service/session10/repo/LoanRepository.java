package com.udemy.spring.security.service.session10.repo;

import com.udemy.spring.security.service.session10.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

  @PreAuthorize(value = "hasRole('ROOT')") // make senses the annotation here?
  List<Loan> findByCustomerIdOrderByStartDtDesc(int customerId);
}
