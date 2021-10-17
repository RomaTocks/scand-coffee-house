package com.scand.test.model.wrapper;

import com.scand.test.model.CoffeeOrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.Map;

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
    private Map<CoffeeOrderItem, Double> coffeeOrderAndPrice;

    public OrderWrapper(Map<CoffeeOrderItem, Double> coffeeOrderAndPrice)
    {
        this.coffeeOrderAndPrice = coffeeOrderAndPrice;
    }
}
