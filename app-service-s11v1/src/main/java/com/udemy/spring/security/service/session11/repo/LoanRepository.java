package com.udemy.spring.security.service.session11.repo;

import com.udemy.spring.security.service.session11.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

  //@PreAuthorize(value = "hasRole('USER')")
  List<Loan> findByCustomerIdOrderByStartDtDesc(long customerId);
}
