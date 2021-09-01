package com.scand.test.services.implementations;

import com.scand.test.models.CoffeeOrder;
import com.scand.test.models.CoffeeOrderItem;
import com.scand.test.models.Configuration;
import com.scand.test.models.wrappers.OrderWrapper;
import com.scand.test.repositories.CoffeeOrderRepository;
import com.scand.test.services.CoffeeOrderService;
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
    public CoffeeOrder findById(Integer id)
    {
        if(coffeeOrderRepository.findById(id).isPresent()) return coffeeOrderRepository.findById(id).get();
        else return null;
    }

    @Override
    public List<CoffeeOrder> findAll()
    {
        return coffeeOrderRepository.findAll();
    }

    @Override
    public CoffeeOrder saveEntity(CoffeeOrder coffee)
    {
        return coffeeOrderRepository.save(coffee);
    }

    @Override
    public void delete(Integer id)
    {
        coffeeOrderRepository.deleteById(id);
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
    public double calculateCostOfOrder(List<CoffeeOrderItem> coffeeOrderItems, Configuration configuration) {
        final double[] cost = {0};
        coffeeOrderItems.forEach(coffeeOrderItem -> cost[0] += calculateCostOfOrderItem(coffeeOrderItem, configuration));
        return cost[0];
    }

    @Override
    public double calculateCostOfOrderItem(CoffeeOrderItem coffeeOrderItem, Configuration configuration)
    {
        return coffeeOrderItem.getCoffeeType().getPrice()*countOfCoffeeCupsWithPromotion(coffeeOrderItem.getQuantity(),configuration);
    }

    @Override
    public Integer countOfCoffeeCupsWithPromotion(Integer quantity, Configuration configuration) {
        return quantity - (quantity / configuration.getCup());
    }

    @Override
    public double costOfDelivery(double sum, Configuration configuration)
    {
        if (sum >= configuration.getFreeDelivery()) return 0;
        else return configuration.getDeliveryCost();
    }

    @Override
    public double calculateAllCostOfOrder(List<CoffeeOrderItem> coffeeOrderItems, Configuration configuration)
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
