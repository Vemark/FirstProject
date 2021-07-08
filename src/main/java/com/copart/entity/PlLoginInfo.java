package com.copart.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PL_LOGIN_INFO")
public class PlLoginInfo {
  @Id
  @Column(name = "ID")
  private Long id;

  @Column(name = "LOGIN_NAME")
  private String loginName;

  @Column(name = "PASSWORD")
  private String password;
}
