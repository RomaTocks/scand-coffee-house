package com.scand.test.service;

import com.scand.test.model.CoffeeOrderItem;
import com.scand.test.model.CoffeeType;

import java.util.LinkedHashMap;
import java.util.List;

public interface CoffeeOrderItemService
{
    List<CoffeeOrderItem> coffeeToOrderItem(List<CoffeeType> coffeeTypesList, LinkedHashMap<CoffeeType,Integer> coffeeTypeIntegerLinkedHashMap);
}
