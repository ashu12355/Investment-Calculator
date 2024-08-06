package com.ashu.investment_calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvestmentController {

    @GetMapping
    public String home() {
        return "index";
    }

}
