package com.udemy.spring.security.service.session10.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "tblAccountTransactions")
public class AccountTransaction {
  private int customerId;
  private long accountNumber;
  private Date transactionDt;
  private int transactionAmt;
  private int closingBalance;
  @Id
  private String transactionId;
  private String transactionType;
  private String transactionSummary;
}
