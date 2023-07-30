package com.udemy.spring.security.service.session09.repo;

import com.udemy.spring.security.service.session09.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
  Account findByCustomerId(int customerId);
}
