package com.udemy.spring.security.service.session10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Service Module Session 03
 */
//@EnableWebSecurity optional annotation
@SpringBootApplication
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ServiceSession10App {
  public static void main(String[] args) {
    SpringApplication.run(ServiceSession10App.class, args);
  }

}
