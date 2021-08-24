package com.scand.test.services.implementations;

import com.scand.test.models.CoffeeOrderItem;
import com.scand.test.models.CoffeeType;
import com.scand.test.repositories.CoffeeOrderItemRepository;
import com.scand.test.services.CoffeeOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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
    public List<CoffeeOrderItem> coffeeToOrderItem(List<CoffeeType> coffeeTypesList, List<Integer> coffeeCounts)
    {
        List<CoffeeOrderItem> coffeeOrderItems = new ArrayList<>();
        coffeeCounts.removeIf(Objects::isNull);
        Iterator<CoffeeType> coffeeTypeIterator = coffeeTypesList.iterator();
        Iterator<Integer> coffeeCountsIterator = coffeeCounts.iterator();
        while (coffeeTypeIterator.hasNext() && coffeeCountsIterator.hasNext()) {
            coffeeOrderItems.add(new CoffeeOrderItem(coffeeTypeIterator.next(),coffeeCountsIterator.next()));
        }
        return coffeeOrderItems;
    }

    @Override
    public List<CoffeeOrderItem> saveAllCoffeeOrderItems(List<CoffeeOrderItem> coffeeOrderItems)
    {
        return coffeeOrderItemRepository.saveAll(coffeeOrderItems);
    }
}
