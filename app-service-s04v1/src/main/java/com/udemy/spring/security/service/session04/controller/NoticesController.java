package com.udemy.spring.security.service.session04.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {
  @GetMapping("/notices")
  public String getLoansDetails() {
    return "Here are the notice details from the DB";
  }
}
