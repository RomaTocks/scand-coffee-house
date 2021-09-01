package com.scand.test.services;

import com.scand.test.models.CoffeeOrder;
import com.scand.test.models.CoffeeOrderItem;
import com.scand.test.models.Configuration;
import com.scand.test.models.wrappers.OrderWrapper;

import java.util.LinkedHashMap;
import java.util.List;

// TODO: 30.08.2021 Создать методы: расчёта n-ой бесплатной кружки кофе, расчёт доставки, общей суммы.  
public interface CoffeeOrderService extends CrudService<CoffeeOrder>
{
    /**
     * Метод генерирует новый заказ на основе входных данных.
     * @param order Обьект типа CoffeeOrder для получения значений полей с формы HTML.
     * @param list Коллекция обьектов содержащая позиции заказа.
     * @return Возвращает сформированный заказ.
     */
    CoffeeOrder generateNewOrder(OrderWrapper orderWrapper, List<CoffeeOrderItem> list, Configuration configuration);

    /**
     * Метод рассчитывает стоимость заказа, учитывая возможные скидки и стоимость доставки.
     * @param coffeeOrderItems Коллекция заказанных позиций для расчёта.
     * @return Возвращает стоимость заказа.
     */
    double calculateCostOfOrder(List<CoffeeOrderItem> coffeeOrderItems, Configuration configuration);
    double calculateCostOfOrderItem(CoffeeOrderItem coffeeOrderItem, Configuration configuration);
    Integer countOfCoffeeCupsWithPromotion(Integer quantity, Configuration configuration);
    double costOfDelivery(double sum, Configuration configuration);
    double calculateAllCostOfOrder(List<CoffeeOrderItem> coffeeOrderItems, Configuration configuration);
    LinkedHashMap<CoffeeOrderItem, Double> generateNewOrderItemsAndPriceMap(List<CoffeeOrderItem> list, Configuration configuration);
}
