package com.scand.test.services.implementations;

import com.scand.test.models.CoffeeOrderItem;
import com.scand.test.models.CoffeeType;
import com.scand.test.repositories.CoffeeOrderItemRepository;
import com.scand.test.services.CoffeeOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public CoffeeOrderItem findById(Integer id)
    {
        return coffeeOrderItemRepository.findById(id).get();
    }

    @Override
    public List<CoffeeOrderItem> findAll()
    {
        return coffeeOrderItemRepository.findAll();
    }

    @Override
    public CoffeeOrderItem saveEntity(CoffeeOrderItem coffee)
    {
       return coffeeOrderItemRepository.save(coffee);
    }

    @Override
    public void delete(Integer id)
    {
        coffeeOrderItemRepository.deleteById(id);
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

    @Override
    public List<CoffeeOrderItem> saveAllCoffeeOrderItems(List<CoffeeOrderItem> coffeeOrderItems)
    {
        return coffeeOrderItemRepository.saveAll(coffeeOrderItems);
    }
}
