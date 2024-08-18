package com.ashu.investment_calculator.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InvestmentRequest {
    private int startingYear;
    private double initialInvestment;
    private double yearlyInvestment;
    private double investmentTerm;
    private double returnRate;
    private InterestMode interestMode;

}
