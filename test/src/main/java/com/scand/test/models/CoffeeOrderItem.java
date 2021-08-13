package com.scand.test.models;


import javax.persistence.*;

@Entity
@Table(name = "coffeeorderitem")
public class CoffeeOrderItem extends Coffee
{
  @Column(name = "quantity")
  private long quantity;
}
