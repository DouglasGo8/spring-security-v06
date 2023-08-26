package com.udemy.spring.security.service.session10.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "tblLoans")
public class Loan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int loanNumber;
  private int customerId;
  private Date startDt;
  private String loanType;
  private String totalLoan;
  private int amountPaid;
  private int outstandingAmount;
  private Date createDt;
}
