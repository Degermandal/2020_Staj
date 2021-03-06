
package com.example.internlogin.modelOfResponse.GetCustomerPortfolio;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hisse implements Serializable
{

    @SerializedName("tur")
    @Expose
    private String tur;
    @SerializedName("miktar")
    @Expose
    private Double miktar;
    @SerializedName("deger")
    @Expose
    private Double deger;
    @SerializedName("kar")
    @Expose
    private Double kar;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Hisse() {
    }

    public Hisse(String tur, Double miktar, Double deger, Double kar) {
        this.tur = tur;
        this.miktar = miktar;
        this.deger = deger;
        this.kar = kar;
    }

    /**
     * 
     * @param tur
     * @param deger
     * @param miktar
     * @param kar
     */

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public Double getMiktar() {
        return miktar;
    }

    public void setMiktar(Double miktar) {
        this.miktar = miktar;
    }

    public Double getDeger() {
        return deger;
    }

    public void setDeger(Double deger) {
        this.deger = deger;
    }

    public Double getKar() {
        return kar;
    }

    public void setKar(Double kar) {
        this.kar = kar;
    }
}
