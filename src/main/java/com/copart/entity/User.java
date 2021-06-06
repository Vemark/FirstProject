package com.copart.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "USERS")
public class User implements Serializable {
  @Id
  @Column(name = "USERID")
  private Long id;

  @Column(name = "USERNAME")
  private String username;

  @Column(name = "PASSWORD")
  private String password;
}
