package com.scand.test.controllers;

import com.scand.test.controllers.attributes.ControllersAttributes;
import com.scand.test.models.CoffeeOrder;
import com.scand.test.models.CoffeeOrderItem;
import com.scand.test.services.CoffeeOrderItemService;
import com.scand.test.services.CoffeeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Класс-контроллер отвечающий за оформление заказа.
 */
@Controller
@RequestMapping("/orderlist")
public class OrderController extends ControllersAttributes
{
    private final CoffeeOrderService coffeeOrderService;
    private final CoffeeOrderItemService coffeeOrderItemService;

    @Autowired
    public OrderController(CoffeeOrderService coffeeOrderService, CoffeeOrderItemService coffeeOrderItemService)
    {
        super();
        this.coffeeOrderService = coffeeOrderService;
        this.coffeeOrderItemService = coffeeOrderItemService;
    }

    // TODO: 30.08.2021 Исправить связи между сущностями для сохранения заказа.
    @PostMapping("/order")
    public String saveOrder(@ModelAttribute("order") CoffeeOrder order, HttpSession session) {
        CoffeeOrder orderFromDataBase = coffeeOrderService.saveEntity(coffeeOrderService.generateNewOrder(order, (ArrayList<CoffeeOrderItem>) session.getAttribute("orderItems")));
        ((ArrayList<CoffeeOrderItem>) session.getAttribute("orderItems")).forEach(coffeeOrderItem -> coffeeOrderItem.setCoffeeOrder(orderFromDataBase));
        coffeeOrderItemService.saveAllCoffeeOrderItems((ArrayList<CoffeeOrderItem>)session.getAttribute("orderItems"));
        session.removeAttribute("orderItems");
        return "order";
    }
}
