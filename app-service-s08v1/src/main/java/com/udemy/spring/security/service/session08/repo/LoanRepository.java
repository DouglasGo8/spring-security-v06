package com.udemy.spring.security.service.session08.repo;

import com.udemy.spring.security.service.session08.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
  List<Loan> findByCustomerIdOrderByStartDtDesc(int customerId);
}
