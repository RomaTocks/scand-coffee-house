package com.scand.test.controller;

import com.scand.test.controller.attribute.ControllersAttributes;
import com.scand.test.model.CoffeeOrderItem;
import com.scand.test.model.wrapper.CoffeeWrapper;
import com.scand.test.model.wrapper.OrderWrapper;
import com.scand.test.service.CoffeeOrderItemService;
import com.scand.test.service.CoffeeOrderService;
import com.scand.test.service.CoffeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Класс-контроллер отвечающий за страницу выбора кофе для заказа.
 */
@Controller
@RequestMapping("/")
public class IndexController extends ControllersAttributes
{
    private final static String INDEX = "index";
    private final static String ORDER_LIST = "orderlist";
    private final CoffeeOrderItemService coffeeOrderItemService;
    private final CoffeeOrderService coffeeOrderService;
    private final CoffeeTypeService coffeeTypeService;

    @Autowired
    public IndexController(CoffeeOrderItemService coffeeOrderItemService, CoffeeOrderService coffeeOrderService, CoffeeTypeService coffeeTypeService)
    {
        this.coffeeOrderItemService = coffeeOrderItemService;
        this.coffeeOrderService = coffeeOrderService;
        this.coffeeTypeService = coffeeTypeService;
    }

    @GetMapping("/")
    public String index() {
        return INDEX;
    }

    @PostMapping("/orderlist")
    public String toOrderList(@Valid @ModelAttribute("wrapper") CoffeeWrapper wrapper, BindingResult bindingResult, Model model, HttpSession session) {
        List<CoffeeOrderItem> coffeeOrderItemList = coffeeOrderItemService.coffeeToOrderItem(wrapper.getSelectedCoffeeTypes(), coffeeTypeService.createCoffeeAndCountsLinkedHashMap(wrapper));
        Double sumOfOrder = coffeeOrderService.calculateCostOfOrder(coffeeOrderItemList, configuration());
        session.setAttribute("orderItems",coffeeOrderItemList);
        model.addAttribute("orderWrapper", new OrderWrapper(coffeeOrderService.generateNewOrderItemsAndPriceMap(coffeeOrderItemList,configuration())));
        model.addAttribute("cost", sumOfOrder);
        model.addAttribute("allCost", coffeeOrderService.calculateAllCostOfOrder(coffeeOrderItemList,configuration()));
        model.addAttribute("delivery", coffeeOrderService.costOfDelivery(sumOfOrder,configuration()));
        return ORDER_LIST;
    }
}
