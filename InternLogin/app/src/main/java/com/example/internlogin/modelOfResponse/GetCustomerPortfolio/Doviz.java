
package com.example.internlogin.modelOfResponse.GetCustomerPortfolio;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doviz implements Serializable
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
    private final static long serialVersionUID = -938888052446247462L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Doviz() {
    }

    public Doviz(String tur, Double miktar, Double deger, Double kar) {
        this.tur = tur;
        this.miktar = miktar;
        this.deger = deger;
        this.kar = kar;
    }

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
