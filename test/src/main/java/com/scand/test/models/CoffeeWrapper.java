package com.scand.test.models;

import com.scand.test.services.annotations.NullArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс-обёртка для получения данных с формы HTML.
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
    private ArrayList<CoffeeType> selectedCoffeeTypes;
    /**
     * Коллекция целочисленных значений, содержащая количество заказанных кофе.
     */
    @NullArray(message = "Нужно указать количество кофейка!")
    private ArrayList<Integer> selectedCoffeeCounts;
}
