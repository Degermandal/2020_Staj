package com.example.internlogin.Model;

public class Market {
    String symbol;
    Double buying;
    Double selling;
    Double last;
    Double rate;

    public Market(String symbol, Double buying, Double selling, Double last, Double rate) {
        this.symbol = symbol;
        this.buying = buying;
        this.selling = selling;
        this.last = last;
        this.rate = rate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getBuying() {
        return buying;
    }

    public void setBuying(Double buying) {
        this.buying = buying;
    }

    public Double getSelling() {
        return selling;
    }

    public void setSelling(Double selling) {
        this.selling = selling;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
