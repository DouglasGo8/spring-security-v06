package com.udemy.spring.security.service.session10.repo;

import com.udemy.spring.security.service.session10.model.AccountTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTransactionRepository extends CrudRepository<AccountTransaction, Long> {
  List<AccountTransaction> findByCustomerIdOrderByTransactionDtDesc(int customerId);
}
