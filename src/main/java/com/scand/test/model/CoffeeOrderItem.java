package com.scand.test.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "coffeeorderitem")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeOrderItem extends Coffee
{
  @Column(name = "quantity")
  private Integer quantity;
  @ManyToOne
  @JoinColumn(name = "coffeetype_id")
  private CoffeeType coffeeType;

  public CoffeeOrderItem( CoffeeType coffeeType, Integer quantity)
  {
    this.quantity = quantity;
    this.coffeeType = coffeeType;
  }
}
