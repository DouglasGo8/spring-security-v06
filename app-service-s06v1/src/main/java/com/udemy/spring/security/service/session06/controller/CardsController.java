package com.udemy.spring.security.service.session06.controller;

import com.udemy.spring.security.service.session06.model.Card;
import com.udemy.spring.security.service.session06.repo.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CardsController {


  final CardRepository cardRepository;

  @GetMapping("/myCards")
  public List<Card> getCardDetails(@RequestParam int id) {
    return this.cardRepository.findByCustomerId(id);
  }

}
