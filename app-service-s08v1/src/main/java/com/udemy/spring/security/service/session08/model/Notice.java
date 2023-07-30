package com.udemy.spring.security.service.session08.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "tblnoticedetails")
public class Notice {
  @Id
  @Column(name = "noticeid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int noticeId;
  @Column(name = "noticesummary")
  private String noticeSummary;
  @Column(name = "noticedetails")
  private String noticeDetails;
  @Column(name = "noticebegdt")
  private Date noticeBegDt;
  @Column(name = "noticeenddt")
  private Date noticeEndDt;
  @Column(name = "createdt")
  private Date createDt;
  @Column(name = "updatedt")
  private Date updateDt;
}
