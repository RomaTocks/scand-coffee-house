package com.scand.test.services;

import com.scand.test.models.CoffeeOrder;
import com.scand.test.models.CoffeeOrderItem;

import java.util.List;

public interface CoffeeOrderService extends CrudService<CoffeeOrder>
{
    /**
     * Метод генерирует новый заказ на основе входных данных.
     * @param order Обьект типа CoffeeOrder для получения значений полей с формы HTML.
     * @param list Коллекция обьектов содержащая позиции заказа.
     * @return Возвращает сформированный заказ.
     */
    CoffeeOrder generateNewOrder(CoffeeOrder order, List<CoffeeOrderItem> list);

    /**
     * Метод рассчитывает стоимость заказа, учитывая возможные скидки.
     * @param coffeeOrderItems Коллекция заказанных позиций для расчёта.
     * @return Возвращает стоимость заказа.
     */
    double calculateCostOfOrder(List<CoffeeOrderItem> coffeeOrderItems);
}
