package com.udemy.spring.security.basic.concept.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingApi {
  //
  @GetMapping("/greet")
  public String sayHi() {
    return "hello, Spring Security";
  }
}
