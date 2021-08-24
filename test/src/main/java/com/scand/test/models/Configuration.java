package com.scand.test.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Класс отвечающий за правила подсчёта цены заказа.
 */
@Entity
@Table(name = "configuration")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Configuration extends Coffee {
  @Column(name = "value")
  private String value;
}
