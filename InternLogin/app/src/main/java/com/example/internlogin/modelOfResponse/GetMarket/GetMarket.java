
package com.example.internlogin.modelOfResponse.GetMarket;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMarket implements Serializable
{

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private Data data;
    private final static long serialVersionUID = 2137328540572190539L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetMarket() {
    }

    /**
     * 
     * @param code
     * @param data
     */
    public GetMarket(String code, Data data) {
        super();
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
