package com.scand.test.model.wrapper;

import com.scand.test.model.CoffeeType;
import com.scand.test.service.annotation.NullArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Класс-обёртка CoffeeType для получения данных с формы HTML.
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeWrapper
{
    /**
     * Коллекция обьектов CoffeeType, содержащая выбранные кофе для заказа.
     */
    @NotEmpty(message = "Нужно выбрать кофеёк, чтобы продолжить :(")
    private List<CoffeeType> selectedCoffeeTypes;
    /**
     * Коллекция целочисленных значений, содержащая количество заказанных кофе.
     */
    @NullArray(message = "Нужно указать количество кофейка!")
    private List<Integer> selectedCoffeeCounts;
}
