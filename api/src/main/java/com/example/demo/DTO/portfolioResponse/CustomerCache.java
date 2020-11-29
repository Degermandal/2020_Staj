package com.example.demo.DTO.portfolioResponse;

public class CustomerCache {

    Double amount;
    String type;

    public CustomerCache(Double amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
