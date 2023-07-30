package com.udemy.spring.security.service.session08.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "tblCards")
public class Card {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int cardId;
  private String cardNumber;
  private int customerId;
  private String cardType;
  private int totalLimit;
  private int amountUsed;
  private int availableAmount;
  private Date createDt;
}
