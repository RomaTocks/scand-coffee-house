package com.scand.test.models.wrappers;

import com.scand.test.models.CoffeeOrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.LinkedHashMap;

/**
 * Класс-обёртка СoffeeOrder для получения полей с формы HTML,
 * также содержит коллекцию позиций заказа с учётом скидок.
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderWrapper
{
    @NotBlank(message = "Имя не должно быть пустым или содержать пробелы!")
    private String name;
    @NotBlank(message = "Адрес не должно быть пустым или содержать пробелы!")
    private String deliveryAddress;
    private LinkedHashMap<CoffeeOrderItem, Double> coffeeOrderAndPrice;

    public OrderWrapper(LinkedHashMap<CoffeeOrderItem, Double> coffeeOrderAndPrice)
    {
        this.coffeeOrderAndPrice = coffeeOrderAndPrice;
    }
}
