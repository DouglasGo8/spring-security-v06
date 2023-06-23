package com.udemy.spring.security.service.session04.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "customer4auth")
public class Customer {

  @Id
  @GenericGenerator(name = "native", strategy = "native")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  Long id;
  @Column(name = "email")
  String email;
  @Column(name = "pwd")
  String password;
  @Column(name = "role")
  String role;
}
