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
@Table(name = "tblContactMessages")
public class ContactMessage {
  @Id
  private String contactId;
  private String contactName;
  private String contactEmail;
  private String subject;
  private String message;
  private Date createDt;
}
