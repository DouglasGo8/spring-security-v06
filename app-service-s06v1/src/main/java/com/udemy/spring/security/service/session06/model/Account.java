package com.udemy.spring.security.service.session06.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tblAccounts")
public class Account {

  private int customerId;
  private String createdDt;
  @Id
  private long accountNumber;
  private String accountType;
  private String branchAddress;
}
