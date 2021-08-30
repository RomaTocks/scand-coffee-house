package com.scand.test.services;

import com.scand.test.models.CoffeeOrderItem;
import com.scand.test.models.CoffeeType;

import java.util.LinkedHashMap;
import java.util.List;

public interface CoffeeOrderItemService extends CrudService<CoffeeOrderItem>
{
    List<CoffeeOrderItem> coffeeToOrderItem(List<CoffeeType> coffeeTypesList, LinkedHashMap<CoffeeType,Integer> coffeeTypeIntegerLinkedHashMap);
    List<CoffeeOrderItem> saveAllCoffeeOrderItems(List<CoffeeOrderItem> coffeeOrderItems);
}
