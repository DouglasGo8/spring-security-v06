package com.udemy.spring.security.service.session11.repo;

import com.udemy.spring.security.service.session11.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
  Account findByCustomerId(long customerId);
}
