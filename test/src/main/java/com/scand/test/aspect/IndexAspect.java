package com.scand.test.aspect;

import com.scand.test.model.wrapper.CoffeeWrapper;
import com.scand.test.service.CoffeeTypeService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class IndexAspect
{
    @Autowired
    private CoffeeTypeService coffeeTypeService;

    @Pointcut("execution(* com.scand.test.controller.IndexController.toOrderList(..))")
    private void orderListPointcut(){}

    @Around("orderListPointcut() && args(wrapper, bindingResult, model, session)")
    private Object beforeReturningOrderListAdvice(ProceedingJoinPoint proceedingJoinPoint, CoffeeWrapper wrapper, BindingResult bindingResult, Model model, HttpSession session) throws Throwable
    {
        if (!coffeeTypeService.checkCoffeeAndCounts(wrapper)) {
            model.addAttribute("customException", "Количество выбранного кофейка не может быть пустым, отрицательным или равно 0.");
        }
        if (bindingResult.hasErrors() || !coffeeTypeService.checkCoffeeAndCounts(wrapper)) {
            return "index";
        }
        else {
            return proceedingJoinPoint.proceed();
        }
    }
}
