
package com.example.internlogin.modelOfResponse.GetAccountInformation;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAccountInformation implements Serializable
{

    @SerializedName("iban")
    @Expose
    private String iban;
    @SerializedName("adi")
    @Expose
    private String adi;
    @SerializedName("soyadi")
    @Expose
    private String soyadi;
    @SerializedName("kimlikNo")
    @Expose
    private String kimlikNo;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public String getKimlikNo() {
        return kimlikNo;
    }

    public void setKimlikNo(String kimlikNo) {
        this.kimlikNo = kimlikNo;
    }
}
