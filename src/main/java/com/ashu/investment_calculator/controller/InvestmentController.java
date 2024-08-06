package com.ashu.investment_calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ashu.investment_calculator.model.InvestmentRequest;

@Controller
public class InvestmentController {

    @GetMapping
    public String home() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@ModelAttribute InvestmentRequest request) {
        System.out.println(request);
        return "index";
    }

}
