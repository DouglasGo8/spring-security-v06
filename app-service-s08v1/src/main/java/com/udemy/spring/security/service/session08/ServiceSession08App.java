package com.udemy.spring.security.service.session08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Service Module Session 03
 */
//@EnableWebSecurity optional annotation
@SpringBootApplication
@EnableWebSecurity(debug = true)
public class ServiceSession08App {
  public static void main(String[] args) {
    SpringApplication.run(ServiceSession08App.class, args);
  }

}
