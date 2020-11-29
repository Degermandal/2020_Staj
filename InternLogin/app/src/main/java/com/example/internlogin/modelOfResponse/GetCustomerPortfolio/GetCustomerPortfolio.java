
package com.example.internlogin.modelOfResponse.GetCustomerPortfolio;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCustomerPortfolio implements Serializable
{

    @SerializedName("toplamBakiye")
    @Expose
    private Double toplamBakiye;
    @SerializedName("portfolioBankDTOS")
    @Expose
    private List<PortfolioBankDTO> portfolioBankDTOS = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetCustomerPortfolio() {
    }

    public GetCustomerPortfolio(Double toplamBakiye) {
        this.toplamBakiye = toplamBakiye;
    }

    public Double getToplamBakiye() {
        return toplamBakiye;
    }

    public void setToplamBakiye(Double toplamBakiye) {
        this.toplamBakiye = toplamBakiye;
    }



    public List<PortfolioBankDTO> getPortfolioBankDTOS() {
        return portfolioBankDTOS;
    }

    public void setPortfolioBankDTOS(List<PortfolioBankDTO> portfolioBankDTOS) {
        this.portfolioBankDTOS = portfolioBankDTOS;
    }

}
