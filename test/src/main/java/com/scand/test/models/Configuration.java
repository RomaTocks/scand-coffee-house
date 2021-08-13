package com.scand.test.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "configuration")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Configuration extends Coffee {
  @Column(name = "value")
  private String value;
}
