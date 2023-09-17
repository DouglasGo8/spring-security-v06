package com.udemy.spring.security.service.session11.controller;

import com.udemy.spring.security.service.session11.model.Card;
import com.udemy.spring.security.service.session11.repo.CardRepository;
import com.udemy.spring.security.service.session11.repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CardsController {


  final CardRepository cardRepository;
  final CustomerRepository customerRepository;

  @GetMapping("/myCards")
  public List<Card> getCardDetails(@RequestParam String email) {
    var customers = customerRepository.findByEmail(email);
    if (customers != null && !customers.isEmpty()) {
      var cards = cardRepository.findByCustomerId(customers.get(0).getCustomerId());
      if (cards != null) {
        return cards;
      }
    }
    return null;
  }

}
