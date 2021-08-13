package com.scand.test.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController
{
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("string","string");
        return "index";
    }
    @GetMapping("/orderlist")
    public String orderList(){
        return"orderlist";
    }
    @GetMapping("/order")
    public String thanks(){
        return "order";
    }
}
