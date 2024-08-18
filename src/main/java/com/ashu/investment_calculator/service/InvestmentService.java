package com.ashu.investment_calculator.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ashu.investment_calculator.model.InterestMode;
import com.ashu.investment_calculator.model.InvestmentDetails;
import com.ashu.investment_calculator.model.InvestmentRequest;
import com.ashu.investment_calculator.model.InvestmentResponse;

@Service
public class InvestmentService {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public InvestmentResponse calculate(InvestmentRequest request) {
        var interestMode = request.getInterestMode();
        var details = interestMode == InterestMode.SIMPLE 
            ? simpleCalculation(request) 
            : compoundCalculation(request);

        var response = new InvestmentResponse();
        response.setInterestMode(interestMode);
        response.setDetails(details);

        var latestDetails = details.get(details.size() - 1);
        response.setTotalInvestment(Double.parseDouble(df.format(latestDetails.getTotalInvestment())));
        response.setTotalReturn(Double.parseDouble(df.format(latestDetails.getEndingBalance())));

        return response;
    }

    private List<InvestmentDetails> compoundCalculation(InvestmentRequest request) {
        var startingYear = request.getStartingYear();
        var initialInvestment = request.getInitialInvestment();
        var yearlyInvestment = request.getYearlyInvestment();
        var investmentTerm = request.getInvestmentTerm();
        var returnRate = request.getReturnRate();

        var endingBalance = initialInvestment;
        var totalInvestment = initialInvestment;
        var totalInterest = 0;

        var details = new ArrayList<InvestmentDetails>();
        for (int i = 1; i <= investmentTerm; i++) {
            var investmentDetails = new InvestmentDetails();
            investmentDetails.setYear(startingYear);
            investmentDetails.setInitialInvestment(Double.parseDouble(df.format(initialInvestment)));
            investmentDetails.setYearlyInvestment(Double.parseDouble(df.format(yearlyInvestment)));

            totalInvestment += yearlyInvestment;
            investmentDetails.setTotalInvestment(Double.parseDouble(df.format(totalInvestment)));

            double interestEarn = (endingBalance + yearlyInvestment) * returnRate / 100.0;
            investmentDetails.setInterestEarn(Double.parseDouble(df.format(interestEarn)));

            totalInterest += interestEarn;
            investmentDetails.setTotalInterest(Double.parseDouble(df.format(totalInterest)));

            endingBalance += yearlyInvestment + interestEarn;
            investmentDetails.setEndingBalance(Double.parseDouble(df.format(endingBalance)));

            details.add(investmentDetails);
            startingYear++;
            initialInvestment = endingBalance;
        }

        return details;
    }

    private List<InvestmentDetails> simpleCalculation(InvestmentRequest request) {
        var startingYear = request.getStartingYear();
        var initialInvestment = request.getInitialInvestment();
        var yearlyInvestment = request.getYearlyInvestment();
        var investmentTerm = request.getInvestmentTerm();
        var returnRate = request.getReturnRate();

        var endingBalance = initialInvestment;
        var totalInvestment = initialInvestment;
        var totalInterest = 0;

        var details = new ArrayList<InvestmentDetails>();
        for (int i = 1; i <= investmentTerm; i++) {
            var investmentDetails = new InvestmentDetails();
            investmentDetails.setYear(startingYear);
            investmentDetails.setInitialInvestment(Double.parseDouble(df.format(initialInvestment)));
            investmentDetails.setYearlyInvestment(Double.parseDouble(df.format(yearlyInvestment)));

            totalInvestment += yearlyInvestment;
            investmentDetails.setTotalInvestment(Double.parseDouble(df.format(totalInvestment)));

            double interestEarn = totalInvestment * returnRate / 100.0;
            investmentDetails.setInterestEarn(Double.parseDouble(df.format(interestEarn)));

            totalInterest += interestEarn;
            investmentDetails.setTotalInterest(Double.parseDouble(df.format(totalInterest)));

            endingBalance += yearlyInvestment + interestEarn;
            investmentDetails.setEndingBalance(Double.parseDouble(df.format(endingBalance)));

            details.add(investmentDetails);
            startingYear++;
            initialInvestment = endingBalance;
        }

        return details;
    }
}
