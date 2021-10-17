package com.scand.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
  private Timestamp orderDate;
  @Column
  private String name;
  @Column
  private String deliveryAddress;
  @Column
  private Double cost;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "coffeeorder_id", referencedColumnName = "id", nullable = false)
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

