package com.scand.test.controllers;

import com.scand.test.models.CoffeeOrder;
import com.scand.test.models.CoffeeOrderItem;
import com.scand.test.models.CoffeeWrapper;
import com.scand.test.services.CoffeeOrderItemService;
import com.scand.test.services.CoffeeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс-контроллер отвечающий за оформление заказа.
 */
@Controller
@RequestMapping("/orderlist")
public class OrderController
{
    private final CoffeeOrderService coffeeOrderService;
    private final CoffeeOrderItemService coffeeOrderItemService;

    @Autowired
    public OrderController(CoffeeOrderService coffeeOrderService, CoffeeOrderItemService coffeeOrderItemService)
    {
        this.coffeeOrderService = coffeeOrderService;
        this.coffeeOrderItemService = coffeeOrderItemService;
    }

    @PostMapping
    public String toOrder(Model model, @ModelAttribute("wrapper") CoffeeWrapper wrapper, HttpSession session) {
        List<CoffeeOrderItem> coffeeOrderItemList = coffeeOrderItemService.coffeeToOrderItem(wrapper.getSelectedCoffeeTypes(),wrapper.getSelectedCoffeeCounts());
        session.setAttribute("orderItems",coffeeOrderItemList);
        model.addAttribute("orderItems", coffeeOrderItemList);
        model.addAttribute("cost", coffeeOrderService.calculateCostOfOrder(coffeeOrderItemList));
        return "orderlist";
    }
    @PostMapping("/order")
    public String saveOrder(@ModelAttribute("order") CoffeeOrder order, HttpSession session) {
        CoffeeOrder orderFromDataBase = coffeeOrderService.saveEntity(coffeeOrderService.generateNewOrder(order, (ArrayList<CoffeeOrderItem>) session.getAttribute("orderItems")));
        ((ArrayList<CoffeeOrderItem>) session.getAttribute("orderItems")).forEach(coffeeOrderItem -> coffeeOrderItem.setCoffeeOrder(orderFromDataBase));
        coffeeOrderItemService.saveAllCoffeeOrderItems((ArrayList<CoffeeOrderItem>)session.getAttribute("orderItems"));
        session.removeAttribute("orderItems");
        return "order";
    }
    @ModelAttribute("order")
    public CoffeeOrder order() {
        CoffeeOrder order = new CoffeeOrder();
        return new CoffeeOrder();
    }
}
