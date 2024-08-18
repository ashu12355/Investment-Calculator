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

    var response = new InvestmentResponse();
    response.setInterestMode(interestMode);
    response.setDetails(details);

    var latestDetails = details.get(details.size() - 1);
    response.setTotalInvestment(latestDetails.getTotalInvestment());
    response.setTotalReturn(latestDetails.getEndingBalance());

        return response;
    }

    private List<InvestmentDetails> simpleCalculation(InvestmentRequest request) {
        var startingYear = request.getStartingYear();
        var initialInvestment = request.getInitialInvestment();
        var yearlyInvestment = request.getYearlyInvestment();
        var investmentTerm = request.getInvestmentTerm();
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
            investmentDetails.setTotalInvestment(totalInvestment);

            double interestEarn = totalInvestment * returnRate / 100 ;
            investmentDetails.setInterestEarn(interestEarn);

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

        var startingYear = request.getStartingYear();
        var initialInvestment = request.getInitialInvestment();
        var yearlyInvestment = request.getYearlyInvestment();
        var investmentTerm = request.getInvestmentTerm();
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
            investmentDetails.setTotalInvestment(totalInvestment);

            double interestEarn = (endingBalance + yearlyInvestment) * returnRate / 100 ;
            investmentDetails.setTotalInvestment(interestEarn);

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

    
}
