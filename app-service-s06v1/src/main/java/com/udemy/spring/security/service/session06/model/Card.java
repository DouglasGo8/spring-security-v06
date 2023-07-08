package com.udemy.spring.security.service.session06.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "tblCards")
public class Card {
  @Id
  @GenericGenerator(name = "native", strategy = "native")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  private int cardId;
  private String cardNumber;
  private int customerId;
  private String cardType;
  private int totalLimit;
  private int amountUsed;
  private int availableAmount;
  private Date createDt;
}
