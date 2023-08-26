package com.udemy.spring.security.service.session10.repo;

import com.udemy.spring.security.service.session10.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
  List<Card> findByCustomerId(int customerId);
}
