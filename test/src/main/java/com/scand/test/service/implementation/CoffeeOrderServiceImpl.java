package com.scand.test.service.implementation;

import com.scand.test.model.CoffeeOrder;
import com.scand.test.model.CoffeeOrderItem;
import com.scand.test.model.Configuration;
import com.scand.test.model.wrapper.OrderWrapper;
import com.scand.test.repository.CoffeeOrderRepository;
import com.scand.test.service.CoffeeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Сервис для CoffeeOrder сущностей.
 */
@Service
public class CoffeeOrderServiceImpl implements CoffeeOrderService
{
    private final CoffeeOrderRepository coffeeOrderRepository;

    @Autowired
    public CoffeeOrderServiceImpl(CoffeeOrderRepository coffeeOrderRepository)
    {
        this.coffeeOrderRepository = coffeeOrderRepository;
    }

    @Override
    public CoffeeOrder saveEntity(CoffeeOrder coffee)
    {
        return coffeeOrderRepository.save(coffee);
    }

    /**
     * Метод генерирует новый заказ на основе входных данных.
     * @param wrapper Обьект типа CoffeeOrder для получения значений полей с формы HTML.
     * @param coffeeOrderItems Коллекция обьектов содержащая позиции заказа.
     * @return Возвращает сформированный заказ.
     */
    @Override
    public CoffeeOrder generateNewOrder(OrderWrapper wrapper, List<CoffeeOrderItem> coffeeOrderItems, Configuration configuration)
    {
        CoffeeOrder newOrder = new CoffeeOrder();
        newOrder.setName(wrapper.getName());
        newOrder.setDeliveryAddress(wrapper.getDeliveryAddress());
        newOrder.setOrderItems(coffeeOrderItems);
        newOrder.setCost(calculateCostOfOrder(coffeeOrderItems,configuration));
        newOrder.setOrderDate(new Timestamp(new Date().getTime()));
        return newOrder;
    }

    @Override
    public Double calculateCostOfOrder(List<CoffeeOrderItem> coffeeOrderItems, Configuration configuration) {
        final double[] cost = {0};
        coffeeOrderItems.forEach(coffeeOrderItem -> cost[0] += calculateCostOfOrderItem(coffeeOrderItem, configuration));
        return cost[0];
    }

    @Override
    public Double calculateCostOfOrderItem(CoffeeOrderItem coffeeOrderItem, Configuration configuration)
    {
        return coffeeOrderItem.getCoffeeType().getPrice()*countOfCoffeeCupsWithPromotion(coffeeOrderItem.getQuantity(),configuration);
    }

    @Override
    public Integer countOfCoffeeCupsWithPromotion(Integer quantity, Configuration configuration) {
        return quantity - (quantity / configuration.getCup());
    }

    @Override
    public Double costOfDelivery(double sum, Configuration configuration)
    {
        if (sum >= configuration.getFreeDelivery()) {
            return Double.valueOf(0);
        }
        else return configuration.getDeliveryCost();
    }

    @Override
    public Double calculateAllCostOfOrder(List<CoffeeOrderItem> coffeeOrderItems, Configuration configuration)
    {
        double sum = calculateCostOfOrder(coffeeOrderItems, configuration);
        sum+=costOfDelivery(sum,configuration);
        return sum;
    }

    /**
     * Метод формирует LinkedHashMap<CoffeeOrderItem, Double> с позициями заказа и стоимости с учётом скидок.
     * @param list - Список позиций заказа.
     * @param configuration - Обьект конфигурации.
     * @return map - Коллекция позиций заказа и цена с учётом скидок.
     */
    @Override
    public LinkedHashMap<CoffeeOrderItem, Double> generateNewOrderItemsAndPriceMap(List<CoffeeOrderItem> list, Configuration configuration)
    {
        LinkedHashMap<CoffeeOrderItem, Double> map = new LinkedHashMap<>();
        list.forEach(coffeeOrderItem -> map.put(coffeeOrderItem,calculateCostOfOrderItem(coffeeOrderItem,configuration)));
        return map;
    }

}
