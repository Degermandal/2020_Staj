package com.example.internlogin.Model;

public class Asset {
    private String name;
    private Double amount;
    private Double profit;
    private Double value;

    public Asset(String name, Double amount, Double value) {
        this.name = name;
        this.amount = amount;
        this.value = value;
        profit = 0.0;
    }

    public Asset(String name, Double amount,  Double value, Double profit) {
        this.name = name;
        this.amount = amount;
        this.profit = profit;
        this.value = value;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
