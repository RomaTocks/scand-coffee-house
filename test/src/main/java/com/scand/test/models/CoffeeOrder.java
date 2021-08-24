package com.scand.test.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
public class CoffeeOrder extends Coffee implements Cloneable
{
  @Column
  private java.sql.Timestamp orderDate;
  @Column
  private String name;
  @Column
  private String deliveryAddress;
  @Column
  private double cost;
  @OneToMany(mappedBy = "coffeeOrder")
  private List<CoffeeOrderItem> orderItems;

  public CoffeeOrder(Timestamp orderDate, String name, String deliveryAddress, double cost)
  {
    this.orderDate = orderDate;
    this.name = name;
    this.deliveryAddress = deliveryAddress;
    this.cost = cost;
  }

  @Override
  public String toString()
  {
    return "CoffeeOrder{" +
            "orderDate=" + orderDate +
            ", name='" + name + '\'' +
            ", deliveryAddress='" + deliveryAddress + '\'' +
            ", cost=" + cost +
            '}';
  }

  @Override
  public CoffeeOrder clone() throws CloneNotSupportedException
  {
    return (CoffeeOrder)super.clone();
  }
}

