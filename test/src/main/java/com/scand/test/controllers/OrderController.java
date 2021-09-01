package com.scand.test.controllers;

import com.scand.test.controllers.attributes.ControllersAttributes;
import com.scand.test.models.CoffeeOrder;
import com.scand.test.models.CoffeeOrderItem;
import com.scand.test.models.wrappers.OrderWrapper;
import com.scand.test.services.CoffeeOrderItemService;
import com.scand.test.services.CoffeeOrderService;
import com.scand.test.services.CoffeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
    private final CoffeeTypeService coffeeTypeService;

    @Autowired
    public OrderController(CoffeeOrderService coffeeOrderService, CoffeeOrderItemService coffeeOrderItemService, CoffeeTypeService coffeeTypeService)
    {
        super();
        this.coffeeOrderService = coffeeOrderService;
        this.coffeeOrderItemService = coffeeOrderItemService;
        this.coffeeTypeService = coffeeTypeService;
    }

    // TODO: 30.08.2021 Исправить связи между сущностями для сохранения заказа.
    @PostMapping("/order")
    public String saveOrder(@Valid @ModelAttribute("orderWrapper") OrderWrapper wrapper, BindingResult bindingResult, HttpSession session, Model model) {
        ArrayList<CoffeeOrderItem> orderItems = (ArrayList<CoffeeOrderItem>) session.getAttribute("orderItems");
        if(bindingResult.hasErrors()) {
            double sumOfOrder = coffeeOrderService.calculateCostOfOrder(orderItems, configuration());
            model.addAttribute("cost", sumOfOrder);
            wrapper.setCoffeeOrderAndPrice(coffeeOrderService.generateNewOrderItemsAndPriceMap(orderItems,configuration()));
            model.addAttribute("allCost", coffeeOrderService.calculateAllCostOfOrder(orderItems,configuration()));
            model.addAttribute("delivery", coffeeOrderService.costOfDelivery(sumOfOrder,configuration()));
            return "orderlist";
        } else {
            CoffeeOrder orderFromDataBase = coffeeOrderService.saveEntity(coffeeOrderService.generateNewOrder(wrapper, orderItems, configuration()));
            orderItems.forEach(coffeeOrderItem -> coffeeOrderItem.setCoffeeOrder(orderFromDataBase));
            coffeeOrderItemService.saveAllCoffeeOrderItems(orderItems);
            session.removeAttribute("orderItems");
            return "order";
        }
    }
}
