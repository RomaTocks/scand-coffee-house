package com.scand.test.controller;

import com.scand.test.controller.attribute.ControllersAttributes;
import com.scand.test.model.CoffeeOrder;
import com.scand.test.model.CoffeeOrderItem;
import com.scand.test.model.wrapper.OrderWrapper;
import com.scand.test.service.CoffeeOrderItemService;
import com.scand.test.service.CoffeeOrderService;
import com.scand.test.service.CoffeeTypeService;
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
    private final static String ORDER = "order";
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

    @PostMapping("/order")
    public String saveOrder(@Valid @ModelAttribute("orderWrapper") OrderWrapper wrapper, BindingResult bindingResult, HttpSession session, Model model) {
        ArrayList<CoffeeOrderItem> orderItems = (ArrayList<CoffeeOrderItem>) session.getAttribute("orderItems");
        CoffeeOrder order = coffeeOrderService.generateNewOrder(wrapper, orderItems, configuration());
        coffeeOrderService.saveEntity(order);
        session.removeAttribute("orderItems");
        return ORDER;
    }
}
