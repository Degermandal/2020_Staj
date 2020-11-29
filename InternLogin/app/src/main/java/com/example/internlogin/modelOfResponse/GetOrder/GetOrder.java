
package com.example.internlogin.modelOfResponse.GetOrder;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrder implements Serializable
{

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("hisseAdi")
    @Expose
    private String hisseAdi;
    @SerializedName("miktar")
    @Expose
    private Double miktar;
    @SerializedName("alisSatis")
    @Expose
    private String alisSatis;
    @SerializedName("fiyat")
    @Expose
    private Double fiyat;
    @SerializedName("durum")
    @Expose
    private String durum;
    @SerializedName("degisenMiktar")
    @Expose
    private Double degisenMiktar;

    public Double getDegisenMiktar() {
        return degisenMiktar;
    }

    public void setDegisenMiktar(Double degisenMiktar) {
        this.degisenMiktar = degisenMiktar;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private final static long serialVersionUID = 6881967967685886514L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetOrder() {
    }

    /**
     * 
     * @param durum
     * @param alisSatis
     * @param fiyat
     * @param id
     * @param hisseAdi
     * @param miktar
     */
    public GetOrder(Long id, String hisseAdi, Double miktar, String alisSatis, Double fiyat, String durum) {
        super();
        this.id = id;
        this.hisseAdi = hisseAdi;
        this.miktar = miktar;
        this.alisSatis = alisSatis;
        this.fiyat = fiyat;
        this.durum = durum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHisseAdi() {
        return hisseAdi;
    }

    public void setHisseAdi(String hisseAdi) {
        this.hisseAdi = hisseAdi;
    }

    public Double getMiktar() {
        return miktar;
    }

    public void setMiktar(Double miktar) {
        this.miktar = miktar;
    }

    public String getAlisSatis() {
        return alisSatis;
    }

    public void setAlisSatis(String alisSatis) {
        this.alisSatis = alisSatis;
    }

    public Double getFiyat() {
        return fiyat;
    }

    public void setFiyat(Double fiyat) {
        this.fiyat = fiyat;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

}
