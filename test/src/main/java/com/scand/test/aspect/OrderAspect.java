package com.scand.test.aspect;

import com.scand.test.model.CoffeeOrderItem;
import com.scand.test.model.Configuration;
import com.scand.test.model.wrapper.OrderWrapper;
import com.scand.test.service.CoffeeOrderService;
import com.scand.test.service.ConfigurationService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Component
@Aspect
public class OrderAspect
{
    private final CoffeeOrderService coffeeOrderService;
    private final ConfigurationService configurationService;
    @Autowired
    public OrderAspect(CoffeeOrderService coffeeOrderService, ConfigurationService configurationService)
    {
        this.coffeeOrderService = coffeeOrderService;
        this.configurationService = configurationService;
    }

    @Pointcut("execution(* com.scand.test.controller.OrderController.saveOrder(..))")
    private void savePointcut(){}

    @Around("savePointcut() && args(wrapper, bindingResult, session, model)")
    private Object beforeReturningSaveOrderAdvice(ProceedingJoinPoint proceedingJoinPoint, OrderWrapper wrapper, BindingResult bindingResult, HttpSession session, Model model) throws Throwable
    {
        ArrayList<CoffeeOrderItem> orderItems = (ArrayList<CoffeeOrderItem>) session.getAttribute("orderItems");
        if(bindingResult.hasErrors()) {
            Configuration configuration = configurationService.findById("free");
            double sumOfOrder = coffeeOrderService.calculateCostOfOrder(orderItems, configuration);
            model.addAttribute("cost", sumOfOrder);
            wrapper.setCoffeeOrderAndPrice(coffeeOrderService.generateNewOrderItemsAndPriceMap(orderItems,configuration));
            model.addAttribute("allCost", coffeeOrderService.calculateAllCostOfOrder(orderItems,configuration));
            model.addAttribute("delivery", coffeeOrderService.costOfDelivery(sumOfOrder,configuration));
            return "orderlist";
        }
        else {
            if (orderItems == null) {
                return "redirect:/";
            }
            else {
                return proceedingJoinPoint.proceed();
            }
        }
    }
}
