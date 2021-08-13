package com.scand.test.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coffeetype")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoffeeType extends Coffee
{
  @Column(name = "type_name")
  private String typeName;
  @Column(name = "price")
  private double price;
  @Column(name = "disabled")
  private String disabled;
  @OneToMany
  private List<CoffeeOrderItem> orderItems;

  public CoffeeType(String typeName)
  {
    this.typeName = typeName;
  }
}

