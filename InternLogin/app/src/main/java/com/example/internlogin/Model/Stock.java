package com.example.internlogin.Model;

public class Stock {
    private String name;
    private Double amount;
    private Double value;
    private String type;//false = satış , true = alış
    private  Long id; //iptal etmek için lazım..
    private int status;//0=pending , 1=executed , 2=cancelled

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stock(String name, Double amount, Double value, String type, Long id) {
        this.name = name;
        this.amount = amount;
        this.value = value;
        this.type = type;
        this.id = id;
    }

    public Stock(String name, Double amount, Double value, String type, int status) {
        this.name = name;
        this.amount = amount;
        this.value = value;
        this.type = type;
        this.status = status;
    }

    public String isType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getType() {
        return type;
    }
}
