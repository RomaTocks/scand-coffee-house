package com.scand.test.services;

import com.scand.test.models.CoffeeType;
import com.scand.test.models.wrappers.CoffeeWrapper;

import java.util.LinkedHashMap;

public interface CoffeeTypeService extends CrudService<CoffeeType>
{
    boolean checkCoffeeAndCounts(CoffeeWrapper wrapper);
    LinkedHashMap<CoffeeType, Integer> createCoffeeAndCountsLinkedHashMap(CoffeeWrapper wrapper);
}
