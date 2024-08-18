package com.ashu.investment_calculator.model;

import java.util.List;

import lombok.Data;

@Data
public class InvestmentResponse {
    private InterestMode interestMode;
    private double totalReturn;
    private double totalInvestment;
    private List<InvestmentDetails> details;
}
