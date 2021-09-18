package com.scand.test.service;

import com.scand.test.model.CoffeeOrder;
import com.scand.test.model.CoffeeOrderItem;
import com.scand.test.model.Configuration;
import com.scand.test.model.wrapper.OrderWrapper;

import java.util.LinkedHashMap;
import java.util.List;

// TODO: 30.08.2021 Создать методы: расчёта n-ой бесплатной кружки кофе, расчёт доставки, общей суммы.  
public interface CoffeeOrderService
{
    CoffeeOrder saveEntity(CoffeeOrder coffee);
    /**
     * Метод генерирует новый заказ на основе входных данных.
     * @param orderWrapper Обьект типа CoffeeOrder для получения значений полей с формы HTML.
     * @param list Коллекция обьектов содержащая позиции заказа.
     * @return Возвращает сформированный заказ.
     */
    CoffeeOrder generateNewOrder(OrderWrapper orderWrapper, List<CoffeeOrderItem> list, Configuration configuration);

    /**
     * Метод рассчитывает стоимость заказа, учитывая возможные скидки и стоимость доставки.
     * @param coffeeOrderItems Коллекция заказанных позиций для расчёта.
     * @return Возвращает стоимость заказа.
     */
    Double calculateCostOfOrder(List<CoffeeOrderItem> coffeeOrderItems, Configuration configuration);
    Double calculateCostOfOrderItem(CoffeeOrderItem coffeeOrderItem, Configuration configuration);
    Integer countOfCoffeeCupsWithPromotion(Integer quantity, Configuration configuration);
    Double costOfDelivery(double sum, Configuration configuration);
    Double calculateAllCostOfOrder(List<CoffeeOrderItem> coffeeOrderItems, Configuration configuration);
    LinkedHashMap<CoffeeOrderItem, Double> generateNewOrderItemsAndPriceMap(List<CoffeeOrderItem> list, Configuration configuration);
}
