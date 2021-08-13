package com.scand.test.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coffeeorder")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoffeeOrder extends Coffee
{
  @Column
  private java.sql.Timestamp orderDate;
  @Column
  private String name;
  @Column
  private String deliveryAddress;
  @Column
  private double cost;
  @OneToMany
  private List<CoffeeOrderItem> orderItems;
}

