
package com.example.internlogin.modelOfResponse.GetMarket;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
{

    @SerializedName("hisseYuzeysel")
    @Expose
    private HisseYuzeysel hisseYuzeysel;
    private final static long serialVersionUID = 4503726049903360808L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param hisseYuzeysel
     */
    public Data(HisseYuzeysel hisseYuzeysel) {
        super();
        this.hisseYuzeysel = hisseYuzeysel;
    }

    public HisseYuzeysel getHisseYuzeysel() {
        return hisseYuzeysel;
    }

    public void setHisseYuzeysel(HisseYuzeysel hisseYuzeysel) {
        this.hisseYuzeysel = hisseYuzeysel;
    }

}
