package com.ashu.investment_calculator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ashu.investment_calculator.model.InterestMode;
import com.ashu.investment_calculator.model.InvestmentDetails;
import com.ashu.investment_calculator.model.InvestmentRequest;
import com.ashu.investment_calculator.model.InvestmentResponse;

@Service
public class InvestmentService {
    public InvestmentResponse calculate(InvestmentRequest request) {
        var interestMode = request.getInterestMode();
       var details = interestMode == InterestMode.SIMPLE 
       ? simpleCalculation(request) 
       : compoundCalculation(request);
        return null;
    }

    private List<InvestmentDetails> simpleCalculation(InvestmentRequest request) {
        var startingYear = request.getStartingYear();
        var initialInvestment = request.getInitialInvestment();
        var yearlyInvestment = request.getYearlyInvestment();
        var investmentTerm = request.getYearlyInvestment();
        var returnRate = request.getReturnRate();

        //
        var endingBalance = initialInvestment;
        var totalInvestment = initialInvestment;
        var totalInterest = 0;

        var details = new ArrayList<InvestmentDetails>();
        for(int i =1 ; i<= investmentTerm;i++){
            var investmentDetails = new InvestmentDetails();
            investmentDetails.setYear(startingYear);
            investmentDetails.setInitialInvestment(initialInvestment);
            investmentDetails.setYearlyInvestment(yearlyInvestment);

            totalInvestment += yearlyInvestment;
            investmentDetails.setTotalInvestment(totalInterest);

            int interestEarn = totalInterest * returnRate / 100 ;
            investmentDetails.setTotalInvestment(totalInvestment);

            totalInterest += interestEarn;
            investmentDetails.setTotalInterest(totalInterest);

            endingBalance += yearlyInvestment + interestEarn;
            investmentDetails.setEndingBalance(endingBalance);

            details.add(investmentDetails);
            startingYear++;
            initialInvestment = endingBalance;

        }

        return details;
     }

    private List<InvestmentDetails> compoundCalculation(InvestmentRequest request) {
        return null;
    }

    
}
