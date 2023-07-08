package com.udemy.spring.security.service.session06.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "tblLoans")
public class Loan {
  @Id
  @GenericGenerator(name = "native", strategy = "native")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  private int loanNumber;
  private int customerId;
  private Date startDt;
  private String loanType;
  private String totalLoan;
  private int amountPaid;
  private int outstandingAmount;
  private Date createDt;
}
