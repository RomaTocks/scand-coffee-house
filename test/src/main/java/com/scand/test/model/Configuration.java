package com.scand.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Класс отвечающий за правила подсчёта цены заказа.
 */
@Entity
@Table(name = "configuration")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Configuration {
  @Id
  private String id;
  @Column(name = "cup")
  private Integer cup;
  @Column(name = "freedelivery")
  private Integer freeDelivery;
  @Column(name = "deliverycost")
  private Double deliveryCost;
}
