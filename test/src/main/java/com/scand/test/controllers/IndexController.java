package com.scand.test.controllers;

import com.scand.test.models.CoffeeType;
import com.scand.test.models.CoffeeWrapper;
import com.scand.test.services.CoffeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * Класс-контроллер отвечающий за страницу выбора кофе для заказа.
 */
@Controller
@RequestMapping("/")
public class IndexController
{
    private final CoffeeTypeService coffeeTypeService;

    @Autowired
    public IndexController(CoffeeTypeService coffeeTypeService)
    {
        this.coffeeTypeService = coffeeTypeService;
    }
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @ModelAttribute("wrapper")
    public CoffeeWrapper wrapper() {
        return new CoffeeWrapper();
    }
    /**
     * Атрибут для вставки списка кофе из БД в форму HTML.
     * @return Возвращает коллекцию с видами кофе и их количеством.
     */
    @ModelAttribute("coffeeTypes")
    public HashMap<CoffeeType, Integer> coffeeTypeIntegerHashMap() {
        HashMap<CoffeeType, Integer> coffeeTypeAndCount = new HashMap<>();
        coffeeTypeService.findAll().forEach(coffeeType -> coffeeTypeAndCount.put(coffeeType,0));
        return coffeeTypeAndCount;
    }
}
