package com.scand.test.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "coffeetype")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoffeeType extends Coffee
{
  @Column(name = "type_name")
  private String typeName;
  @Column(name = "price")
  private double price;
  @Column(name = "disabled")
  private String disabled;
  @OneToMany(mappedBy = "coffeeType")
  private List<CoffeeOrderItem> orderItems;

  @Override
  public String toString()
  {
    return "CoffeeType{" +
            "typeName='" + typeName + '\'' +
            ", price=" + price +
            ", disabled='" + disabled + '\'' +
            '}';
  }
}

