package com.copart.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "APP_USERS")
public class AppUsers {

  @Id private Long id;

  @Column(name = "USERNAME")
  private String username;

  @Column(name = "LOGINDOMAIN")
  private String loginDomain;

  @Column(name = "PASSWORD_HASH")
  private Long passwordHash;
}
