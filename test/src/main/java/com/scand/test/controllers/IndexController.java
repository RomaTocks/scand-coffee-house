package com.scand.test.controllers;

import com.scand.test.controllers.attributes.ControllersAttributes;
import com.scand.test.models.CoffeeOrderItem;
import com.scand.test.models.wrappers.CoffeeWrapper;
import com.scand.test.models.wrappers.OrderWrapper;
import com.scand.test.services.CoffeeOrderItemService;
import com.scand.test.services.CoffeeOrderService;
import com.scand.test.services.CoffeeTypeService;
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
        return "index";
    }

    @PostMapping("/orderlist")
    public String toOrderList(@Valid @ModelAttribute("wrapper") CoffeeWrapper wrapper, BindingResult bindingResult, Model model, HttpSession session) {
        if(!coffeeTypeService.checkCoffeeAndCounts(wrapper)) model.addAttribute("customException","Количество выбранного кофейка не может быть пустым, отрицательным или равно 0.");
        if(bindingResult.hasErrors() || !coffeeTypeService.checkCoffeeAndCounts(wrapper)) {
            return "index";
        }
        else {
            List<CoffeeOrderItem> coffeeOrderItemList = coffeeOrderItemService.coffeeToOrderItem(wrapper.getSelectedCoffeeTypes(), coffeeTypeService.createCoffeeAndCountsLinkedHashMap(wrapper));
            double sumOfOrder = coffeeOrderService.calculateCostOfOrder(coffeeOrderItemList, configuration());
            session.setAttribute("orderItems",coffeeOrderItemList);
            model.addAttribute("orderWrapper", new OrderWrapper(coffeeOrderService.generateNewOrderItemsAndPriceMap(coffeeOrderItemList,configuration())));
            model.addAttribute("cost", sumOfOrder);
            model.addAttribute("allCost", coffeeOrderService.calculateAllCostOfOrder(coffeeOrderItemList,configuration()));
            model.addAttribute("delivery", coffeeOrderService.costOfDelivery(sumOfOrder,configuration()));
            return "orderlist";
        }
    }
}
