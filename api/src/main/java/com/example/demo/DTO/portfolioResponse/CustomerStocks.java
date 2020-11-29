package com.example.demo.DTO.portfolioResponse;

public class CustomerStocks {
    String name;
    String type;
    String potentialBenefit;
    Double amount;
    Double cost;
    Double price;
    Double code;
    String potentialBenefitRate;
    String salableLot;
    String stockItem;

    public CustomerStocks(String name, Double amount, Double cost, Double price) {
        this.name = name;
        this.amount = amount;
        this.cost = cost;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPotentialBenefit() {
        return potentialBenefit;
    }

    public void setPotentialBenefit(String potentialBenefit) {
        this.potentialBenefit = potentialBenefit;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCode() {
        return code;
    }

    public void setCode(Double code) {
        this.code = code;
    }

    public String getPotentialBenefitRate() {
        return potentialBenefitRate;
    }

    public void setPotentialBenefitRate(String potentialBenefitRate) {
        this.potentialBenefitRate = potentialBenefitRate;
    }

    public String getSalableLot() {
        return salableLot;
    }

    public void setSalableLot(String salableLot) {
        this.salableLot = salableLot;
    }

    public String getStockItem() {
        return stockItem;
    }

    public void setStockItem(String stockItem) {
        this.stockItem = stockItem;
    }
}
