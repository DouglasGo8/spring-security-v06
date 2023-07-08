package com.udemy.spring.security.service.session06.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "tblNoticeDetails")
public class Notice {
  @Id
  @GenericGenerator(name = "native", strategy = "native")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  private int noticeId;
  private String noticeSummary;
  private String noticeDetails;
  private Date noticeBegDt;
  private Date noticeEndDt;
  private Date createDt;
  private Date updateDt;
}
