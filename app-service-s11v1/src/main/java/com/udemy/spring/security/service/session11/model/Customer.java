package com.udemy.spring.security.service.session11.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "tblcustomer")
public class Customer {

  @Id
  @Column(name = "customerid")
  //@GenericGenerator(name = "native", strategy = "native")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long customerId;

  private String name;

  private String email;

  @Column(name = "mobilenumber")
  private String mobileNumber;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

  private String pwd;

  private String role;

  @Column(name = "createddt")
  private Date createdDt;

  @JsonIgnore
  @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
  private Set<Authority> authorities;
}
