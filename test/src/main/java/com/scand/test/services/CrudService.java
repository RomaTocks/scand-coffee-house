package com.scand.test.services;

import java.util.List;

public interface CrudService<T>
{
    T findById(Integer id);
    List<T> findAll();
    T saveEntity(T coffee);
    void delete(Integer id);
}
