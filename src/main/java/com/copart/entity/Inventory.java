package com.copart.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "PL_INVENTORY")
public class Inventory implements Serializable {

  @Id private Long id;

  @Column(name = "YEAR")
  private String year;

  @Column(name = "MODEL")
  private String model;

  @Column(name = "COLOR")
  private String color;

  @Column(name = "BODY_TYPE")
  private String bodyType;

  @Column(name = "ENGINE")
  private String engine;

  @Column(name = "TRANSMISSION")
  private String transmission;
}
