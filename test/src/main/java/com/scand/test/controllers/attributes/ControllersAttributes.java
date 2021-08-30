package com.scand.test.controllers.attributes;

import com.scand.test.models.CoffeeOrder;
import com.scand.test.models.CoffeeType;
import com.scand.test.models.CoffeeWrapper;
import com.scand.test.services.CoffeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;

@Component
public class ControllersAttributes
{
    @Autowired
    private CoffeeTypeService coffeeTypeService;

    @ModelAttribute("wrapper")
    public CoffeeWrapper wrapper() {
        return new CoffeeWrapper();
    }

    /**
     * Атрибут для вставки списка кофе из БД в форму HTML.
     * @return Возвращает коллекцию с видами кофе и их количеством.
     */
    @ModelAttribute("coffeeTypes")
    public LinkedHashMap<CoffeeType, Integer> coffeeTypeIntegerHashMap() {
        LinkedHashMap<CoffeeType, Integer> coffeeTypeAndCount = new LinkedHashMap<>();
        List<CoffeeType> coffeeTypes = coffeeTypeService.findAll();
        Collections.sort(coffeeTypes);
        for (CoffeeType coffeeType : coffeeTypes)
        {
            coffeeTypeAndCount.put(coffeeType,0);
        }
        return coffeeTypeAndCount;
    }
    @ModelAttribute("order")
    public CoffeeOrder order() {
        return new CoffeeOrder();
    }
}
