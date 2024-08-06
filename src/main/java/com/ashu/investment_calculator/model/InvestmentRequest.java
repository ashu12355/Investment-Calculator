package com.ashu.investment_calculator.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InvestmentRequest {
    private int startingYear;
    private int initialInvestment;
    private int yearlyInvestment;
    private int investmentTerm;
    private int returnRate;
    private InterestMode interestMode;

}
