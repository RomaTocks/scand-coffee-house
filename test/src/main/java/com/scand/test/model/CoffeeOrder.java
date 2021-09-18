package com.scand.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "coffeeorder")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoffeeOrder extends Coffee implements Cloneable
{
  @Column
  private java.sql.Timestamp orderDate;
  @Column
  private String name;
  @Column
  private String deliveryAddress;
  @Column
  private Double cost;
  @OneToMany(mappedBy = "coffeeOrder")
  private List<CoffeeOrderItem> orderItems;

  public CoffeeOrder(Timestamp orderDate, String name, String deliveryAddress, Double cost)
  {
    this.orderDate = orderDate;
    this.name = name;
    this.deliveryAddress = deliveryAddress;
    this.cost = cost;
  }

  @Override
  public CoffeeOrder clone() throws CloneNotSupportedException
  {
    return (CoffeeOrder)super.clone();
  }
}

