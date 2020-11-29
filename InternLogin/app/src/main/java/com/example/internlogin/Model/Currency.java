package com.example.internlogin.Model;

public class Currency {

    private String name;
    private String buying_price;
    private String selling_price;
    private String buying_color;
    private String selling_color;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Currency(String name, String buying_price, String selling_price, String buying_color, String selling_color, String type) {
        this.name = name;
        this.buying_price = buying_price;
        this.selling_price = selling_price;
        this.buying_color = buying_color;
        this.selling_color = selling_color;
        this.type = type;
    }

    public String getBuying_color() {
        return buying_color;
    }

    public void setBuying_color(String buying_color) {
        this.buying_color = buying_color;
    }

    public String getSelling_color() {
        return selling_color;
    }

    public void setSelling_color(String selling_color) {
        this.selling_color = selling_color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuying_price() {
        return buying_price;
    }

    public void setBuying_price(String buying_price) {
        this.buying_price = buying_price;
    }

    public String getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }
}
