package com.udemy.spring.security.service.session11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/**
 * Service Module Session 03
 */
//@EnableWebSecurity optional annotation
@SpringBootApplication
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class ServiceSession11App {
  public static void main(String[] args) {
    SpringApplication.run(ServiceSession11App.class, args);
  }

}
