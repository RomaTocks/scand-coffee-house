package com.scand.test.controller.attribute;

import com.scand.test.model.CoffeeOrder;
import com.scand.test.model.CoffeeType;
import com.scand.test.model.Configuration;
import com.scand.test.model.wrapper.CoffeeWrapper;
import com.scand.test.repository.CoffeeTypeRepository;
import com.scand.test.service.CoffeeTypeService;
import com.scand.test.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.LinkedHashMap;
import java.util.List;

@Component
public class ControllersAttributes
{
    @Autowired
    private CoffeeTypeService coffeeTypeService;
    @Autowired
    private ConfigurationService configurationService;

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
        List<CoffeeType> coffeeTypeList = coffeeTypeService.findAllByOrderByPriceAsc();
        coffeeTypeList.forEach(coffeeType -> coffeeTypeAndCount.put(coffeeType,0));
        return coffeeTypeAndCount;
    }
    @ModelAttribute("order")
    public CoffeeOrder order() {
        return new CoffeeOrder();
    }

    /**
     * Атрибут конфигурации содержащий информацию о бесплатной кружке кофе,
     * стоимости доставки, и суммы бесплатной доставки.
     * @return configuration - обьект конфигурации.
     */
    @ModelAttribute("config")
    public Configuration configuration() {
      return configurationService.findById("free");
    }
}
