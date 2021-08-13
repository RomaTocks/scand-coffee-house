package com.scand.test.services;

import com.scand.test.models.Coffee;
import com.scand.test.models.CoffeeType;

import java.util.List;
import java.util.Optional;

public interface CrudService
{
    Coffee findById(int id);
    List<Coffee> findAll();
    void saveEntity(Coffee coffee);
    Coffee put(Coffee coffee);
    void delete(int id);
}
