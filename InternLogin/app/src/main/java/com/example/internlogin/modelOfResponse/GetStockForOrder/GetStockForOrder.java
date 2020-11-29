
package com.example.internlogin.modelOfResponse.GetStockForOrder;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStockForOrder implements Serializable
{

    @SerializedName("Data")
    @Expose
    private Data data;
    private final static long serialVersionUID = -7485212264380360646L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetStockForOrder() {
    }

    /**
     * 
     * @param data
     */
    public GetStockForOrder(Data data) {
        super();
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
