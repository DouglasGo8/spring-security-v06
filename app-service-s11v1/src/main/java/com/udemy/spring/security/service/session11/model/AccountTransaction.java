package com.udemy.spring.security.service.session11.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "tblaccounttransactions")
public class AccountTransaction {

  @Column(name = "customerid")
  private int customerId;
  @Column(name = "accountnumber")
  private long accountNumber;

  @Column(name = "transactiondt")
  private Date transactionDt;
  @Column(name = "transactionamt")
  private int transactionAmt;
  @Column(name = "closingbalance")
  private int closingBalance;

  @Id
  @Column(name = "transactionid")
  private String transactionId;
  @Column(name = "transactiontype")
  private String transactionType;
  @Column(name = "transactionsummary")
  private String transactionSummary;

  @Column(name = "createdt")
  private Date createDt;
}
