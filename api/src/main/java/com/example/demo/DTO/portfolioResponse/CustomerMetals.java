package com.example.demo.DTO.portfolioResponse;

public class CustomerMetals {

      String name;
      Double income;

    public CustomerMetals(String name, Double income, Double outcome, Double amount) {
        this.name = name;
        this.income = income;
        this.outcome = outcome;
        this.amount = amount;
    }

    Double outcome;
      String profit;
      Boolean profitType;
      Double amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getOutcome() {
        return outcome;
    }

    public void setOutcome(Double outcome) {
        this.outcome = outcome;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public Boolean getProfitType() {
        return profitType;
    }

    public void setProfitType(Boolean profitType) {
        this.profitType = profitType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
