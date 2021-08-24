package com.scand.test.services.converter;

import com.scand.test.models.CoffeeType;
import com.scand.test.services.CoffeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Класс для конвертации полученных данных из формы HTML.
 */
@Component
public class StringToCoffeeConverter implements Converter<String, CoffeeType>
{
    private final CoffeeTypeService service;

    @Autowired
    public StringToCoffeeConverter(CoffeeTypeService service)
    {
        this.service = service;
    }

    /**
     * Метод конвертирует String id в Integer, делает запрос к БД и возвращает сущность CoffeeType.
     * @param id Принимает id типа String для конвертации.
     * @return Возвращает обьект CoffeeType из БД по id.
     */
    @Override
    public CoffeeType convert(String id)
    {
        return service.findById(Integer.valueOf(id));
    }
}

