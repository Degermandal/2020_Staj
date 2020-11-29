
package com.example.internlogin.modelOfResponse.GetStockForOrder;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
{

    @SerializedName("Symbol")
    @Expose
    private String symbol;
    @SerializedName("IndexList")
    @Expose
    private List<IndexList> indexList = null;
    private final static long serialVersionUID = -1596674626763974783L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param symbol
     * @param indexList
     */
    public Data(String symbol, List<IndexList> indexList) {
        super();
        this.symbol = symbol;
        this.indexList = indexList;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<IndexList> getIndexList() {
        return indexList;
    }

    public void setIndexList(List<IndexList> indexList) {
        this.indexList = indexList;
    }

}
