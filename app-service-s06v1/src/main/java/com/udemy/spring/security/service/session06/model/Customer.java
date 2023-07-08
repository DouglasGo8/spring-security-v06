package com.udemy.spring.security.service.session06.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "tblCustomer")
public class Customer {

  @Id
  @GenericGenerator(name = "native", strategy = "native")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  private Long customerId;
  private String name;
  private String email;
  private String mobileNumber;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String pwd;
  private String role;
  private String createdDt;
}
