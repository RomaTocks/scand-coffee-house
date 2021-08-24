package com.scand.test.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
    private ArrayList<CoffeeType> selectedCoffeeTypes;
    /**
     * Коллекция целочисленных значений, содержащая количество заказанных кофе.
     */
    private ArrayList<Integer> selectedCoffeeCounts;
}
