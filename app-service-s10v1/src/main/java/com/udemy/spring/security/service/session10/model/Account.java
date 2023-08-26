package com.udemy.spring.security.service.session10.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tblaccounts")
public class Account {

  @Column(name = "customerid")
  private int customerId;
  @Column(name = "createddt")
  private String createdDt;
  @Id
  @Column(name = "accountnumber")
  private long accountNumber;
  @Column(name = "accounttype")
  private String accountType;
  @Column(name = "branchaddress")
  private String branchAddress;
}
