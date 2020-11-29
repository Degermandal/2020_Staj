
package com.example.internlogin.modelOfResponse.GetStockForOrder;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IndexList implements Serializable
{

    @SerializedName("Symbol")
    @Expose
    private String symbol;
    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("Difference")
    @Expose
    private String difference;
    @SerializedName("BuyPrice")
    @Expose
    private String buyPrice;
    @SerializedName("SellPrice")
    @Expose
    private String sellPrice;
    private final static long serialVersionUID = -3488803269904027618L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public IndexList() {
    }

    /**
     * 
     * @param buyPrice
     * @param symbol
     * @param price
     * @param difference
     * @param sellPrice
     */
    public IndexList(String symbol, String price, String difference, String buyPrice, String sellPrice) {
        super();
        this.symbol = symbol;
        this.price = price;
        this.difference = difference;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

}
