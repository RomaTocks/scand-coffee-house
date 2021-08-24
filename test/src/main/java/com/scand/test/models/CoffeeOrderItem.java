package com.scand.test.models;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "coffeeorderitem")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CoffeeOrderItem extends Coffee
{
  @Column(name = "quantity")
  private Integer quantity;
  @ManyToOne
  @JoinColumn(name = "coffeetype_id")
  private CoffeeType coffeeType;
  @ManyToOne
  @JoinColumn(name = "coffeeorder_id")
  private CoffeeOrder coffeeOrder;

  public CoffeeOrderItem( CoffeeType coffeeType, Integer quantity)
  {
    this.quantity = quantity;
    this.coffeeType = coffeeType;
  }
}
