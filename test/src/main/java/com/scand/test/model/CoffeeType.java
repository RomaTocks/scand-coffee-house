package com.scand.test.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "coffeetype")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoffeeType extends Coffee implements Comparable
{
  @Column(name = "type_name")
  private String typeName;
  @Column(name = "price")
  private Double price;
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

  @Override
  public int compareTo(Object o)
  {
    return this.price.compareTo(((CoffeeType)o).getPrice());
  }
}

