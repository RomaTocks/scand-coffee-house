package com.scand.test.services.implementations;

import com.scand.test.models.CoffeeOrder;
import com.scand.test.models.CoffeeOrderItem;
import com.scand.test.repositories.CoffeeOrderRepository;
import com.scand.test.services.CoffeeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
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
     * @param order Обьект типа CoffeeOrder для получения значений полей с формы HTML.
     * @param coffeeOrderItems Коллекция обьектов содержащая позиции заказа.
     * @return Возвращает сформированный заказ.
     */
    @Override
    public CoffeeOrder generateNewOrder(CoffeeOrder order, List<CoffeeOrderItem> coffeeOrderItems)
    {
        CoffeeOrder newOrder = new CoffeeOrder();
        newOrder.setName(order.getName());
        newOrder.setDeliveryAddress(order.getDeliveryAddress());
        newOrder.setOrderItems(coffeeOrderItems);
        newOrder.setCost(calculateCostOfOrder(coffeeOrderItems));
        newOrder.setOrderDate(new Timestamp(new Date().getTime()));
        return newOrder;
    }

    // TODO: 30.08.2021 Внести в расчёт n-ую бесплатную кружку кофе и расчёт бесплатной доставки. 
    @Override
    public double calculateCostOfOrder(List<CoffeeOrderItem> coffeeOrderItems) {
        final double[] cost = {0};
        coffeeOrderItems.forEach(coffeeOrderItem -> cost[0] += coffeeOrderItem.getQuantity()*coffeeOrderItem.getCoffeeType().getPrice());
        return cost[0];
    }

}
