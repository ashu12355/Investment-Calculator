package com.ashu.investment_calculator.model;

import lombok.Data;

@Data
public class InvestmentDetails {
    private int year;
    private double initialInvestment;
    private double yearlyInvestment;
    private double totalInvestment;
    private double interestEarn;
    private double totalInterest;
    private double endingBalance;

}
