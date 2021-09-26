package com.scand.test.service.implementation;

import com.scand.test.model.CoffeeOrderItem;
import com.scand.test.model.CoffeeType;
import com.scand.test.repository.CoffeeOrderItemRepository;
import com.scand.test.service.CoffeeOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class CoffeeOrderItemServiceImpl implements CoffeeOrderItemService
{
    private final CoffeeOrderItemRepository coffeeOrderItemRepository;

    @Autowired
    public CoffeeOrderItemServiceImpl(CoffeeOrderItemRepository coffeeOrderItemRepository)
    {
        this.coffeeOrderItemRepository = coffeeOrderItemRepository;
    }

    @Override
    public List<CoffeeOrderItem> coffeeToOrderItem(List<CoffeeType> selectedCoffeeTypesList, LinkedHashMap<CoffeeType,Integer> coffeeTypeIntegerLinkedHashMap)
    {
        List<CoffeeOrderItem> coffeeOrderItems = new ArrayList<>();
        for (CoffeeType coffeeType : selectedCoffeeTypesList)
        {
            coffeeOrderItems.add(new CoffeeOrderItem(coffeeType,coffeeTypeIntegerLinkedHashMap.get(coffeeType)));
        }
        return coffeeOrderItems;
    }
}
