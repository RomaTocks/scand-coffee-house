package com.scand.test.services.implementations;

import com.scand.test.models.CoffeeType;
import com.scand.test.repositories.CoffeeTypeRepository;
import com.scand.test.services.CoffeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeTypeServiceImpl implements CoffeeTypeService
{

    private final CoffeeTypeRepository coffeeTypeRepository;

    @Autowired
    public CoffeeTypeServiceImpl(CoffeeTypeRepository coffeeTypeRepository)
    {
        this.coffeeTypeRepository = coffeeTypeRepository;
    }

    @Override
    public CoffeeType findById(Integer id)
    {
        if(coffeeTypeRepository.findById(id).isPresent()) return coffeeTypeRepository.findById(id).get();
        else return null;
    }

    @Override
    public List<CoffeeType> findAll()
    {
        return coffeeTypeRepository.findAll();
    }

    @Override
    public CoffeeType saveEntity(CoffeeType coffee)
    {
        return coffeeTypeRepository.save(coffee);
    }

    @Override
    public void delete(Integer id)
    {
        coffeeTypeRepository.deleteById(id);
    }
}
