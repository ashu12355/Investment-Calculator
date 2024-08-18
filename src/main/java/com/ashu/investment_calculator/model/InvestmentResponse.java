package com.ashu.investment_calculator.model;

import java.util.List;

import lombok.Data;

@Data
public class InvestmentResponse {
    private InterestMode interestMode;
    private int totalReturn;
    private int totalInvestment;
    private List<InvestmentDetails> details;
}
