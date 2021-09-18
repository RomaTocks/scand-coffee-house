package com.scand.test.service;

import com.scand.test.model.CoffeeType;
import com.scand.test.model.wrapper.CoffeeWrapper;

import java.util.LinkedHashMap;
import java.util.List;

public interface CoffeeTypeService
{
    CoffeeType findById(Long id);
    List<CoffeeType> findAllByOrderByPriceAsc();
    boolean checkCoffeeAndCounts(CoffeeWrapper wrapper);
    LinkedHashMap<CoffeeType, Integer> createCoffeeAndCountsLinkedHashMap(CoffeeWrapper wrapper);
}
