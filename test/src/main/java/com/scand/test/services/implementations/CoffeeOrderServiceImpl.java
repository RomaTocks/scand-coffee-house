package com.scand.test.services.implementations;

import com.scand.test.models.Coffee;
import com.scand.test.services.CoffeeOrderService;
import com.scand.test.services.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeOrderServiceImpl implements CoffeeOrderService, CrudService
{
    @Override
    public Coffee findById(int id)
    {
        return null;
    }

    @Override
    public List<Coffee> findAll()
    {
        return null;
    }

    @Override
    public void saveEntity(Coffee coffee)
    {

    }

    @Override
    public Coffee put(Coffee coffee)
    {
        return null;
    }

    @Override
    public void delete(int id)
    {

    }
}
