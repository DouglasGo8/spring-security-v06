package com.udemy.spring.security.service.session08.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "tblauthorities")
public class Authority {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "customerid")
  private Customer customer;

}
