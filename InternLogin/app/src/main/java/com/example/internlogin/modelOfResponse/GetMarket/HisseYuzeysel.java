
package com.example.internlogin.modelOfResponse.GetMarket;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HisseYuzeysel implements Serializable
{

    @SerializedName("yuzdedegisim")
    @Expose
    private Double yuzdedegisim;

    @SerializedName("sembolid")
    @Expose
    private Integer sembolid;

    @SerializedName("sembol")
    @Expose
    private String sembol;

    @SerializedName("tarih")
    @Expose
    private String tarih;

    @SerializedName("sektorid")
    @Expose
    private Integer sektorid;

    @SerializedName("alis")
    @Expose
    private Double alis;

    @SerializedName("satis")
    @Expose
    private Double satis;

    @SerializedName("acilis")
    @Expose
    private Double acilis;

    @SerializedName("yuksek")
    @Expose
    private Double yuksek;

    @SerializedName("dusuk")
    @Expose
    private Double dusuk;

    @SerializedName("kapanis")
    @Expose
    private Double kapanis;

    /////////////////////////////////////////////
 /*
    @SerializedName("dusuK1")
    @Expose
    private Double dusuK1;
    @SerializedName("dusuK2")
    @Expose
    private Double dusuK2;

    @SerializedName("kapaniS1")
    @Expose
    private Double kapaniS1;
    @SerializedName("kapaniS2")
    @Expose
    private Double kapaniS2;
    @SerializedName("hacimlot")
    @Expose
    private Double hacimlot;
    @SerializedName("hacimloT1")
    @Expose
    private Double hacimloT1;


    @SerializedName("yukseK1")
    @Expose
    private Double yukseK1;
    @SerializedName("yukseK2")
    @Expose


    private Double yukseK2;
    @SerializedName("hacimloT2")
    @Expose
    private Double hacimloT2;
    @SerializedName("aort")
    @Expose
    private Double aort;
    @SerializedName("aorT1")
    @Expose
    private Double aorT1;
    @SerializedName("aorT2")
    @Expose
    private Double aorT2;
    @SerializedName("hacimtldun")
    @Expose

    private Double hacimtldun;
    @SerializedName("hacimyuzdedegisim")
    @Expose
    private Double hacimyuzdedegisim;
    @SerializedName("hacimtl")
    @Expose
    private Double hacimtl;
    @SerializedName("hacimtL1")
    @Expose
    private Double hacimtL1;
    @SerializedName("hacimtL2")
    @Expose
    private Double hacimtL2;
    @SerializedName("dunkukapanis")
    @Expose
    private Double dunkukapanis;
    @SerializedName("oncekikapanis")
    @Expose
    private Double oncekikapanis;
    @SerializedName("izafikapanis")
    @Expose
    private Double izafikapanis;
    @SerializedName("tavan")
    @Expose
    private Double tavan;
    @SerializedName("taban")
    @Expose
    private Double taban;
    @SerializedName("yilyuksek")
    @Expose
    private Double yilyuksek;
    @SerializedName("yildusuk")
    @Expose
    private Double yildusuk;
    @SerializedName("ayyuksek")
    @Expose
    private Double ayyuksek;
    @SerializedName("aydusuk")
    @Expose
    private Double aydusuk;
    @SerializedName("haftayuksek")
    @Expose
    private Double haftayuksek;
    @SerializedName("haftadusuk")
    @Expose
    private Double haftadusuk;
    @SerializedName("oncekiyilkapanis")
    @Expose
    private Double oncekiyilkapanis;
    @SerializedName("oncekiaykapanis")
    @Expose
    private Double oncekiaykapanis;
    @SerializedName("oncekihaftakapanis")
    @Expose
    private Double oncekihaftakapanis;
    @SerializedName("yilortalama")
    @Expose
    private Double yilortalama;
    @SerializedName("ayortalama")
    @Expose
    private Double ayortalama;
    @SerializedName("haftaortalama")
    @Expose
    private Double haftaortalama;
    @SerializedName("yuzdedegisimS1")
    @Expose
    private Double yuzdedegisimS1;
    @SerializedName("yuzdedegisimS2")
    @Expose
    private Double yuzdedegisimS2;

    @SerializedName("fiyatadimi")
    @Expose
    private Double fiyatadimi;
    @SerializedName("kaykar")
    @Expose
    private Double kaykar;
    @SerializedName("sermaye")
    @Expose
    private Double sermaye;
    @SerializedName("saklamaor")
    @Expose
    private Double saklamaor;
    @SerializedName("netkar")
    @Expose
    private Double netkar;
    @SerializedName("net")
    @Expose
    private Double net;
    @SerializedName("fiyatkaz")
    @Expose
    private Double fiyatkaz;
    @SerializedName("piydeg")
    @Expose
    private Double piydeg;
    @SerializedName("donem")
    @Expose
    private String donem;
    @SerializedName("ozsermaye")
    @Expose
    private Double ozsermaye;
    @SerializedName("beta")
    @Expose
    private Double beta;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    private final static long serialVersionUID = 3384277620807783315L;
*/

    public Double getYuzdedegisim() {
        return yuzdedegisim;
    }

    public void setYuzdedegisim(Double yuzdedegisim) {
        this.yuzdedegisim = yuzdedegisim;
    }

    public Integer getSembolid() {
        return sembolid;
    }

    public void setSembolid(Integer sembolid) {
        this.sembolid = sembolid;
    }

    public String getSembol() {
        return sembol;
    }

    public void setSembol(String sembol) {
        this.sembol = sembol;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public Integer getSektorid() {
        return sektorid;
    }

    public void setSektorid(Integer sektorid) {
        this.sektorid = sektorid;
    }

    public Double getAlis() {
        return alis;
    }

    public void setAlis(Double alis) {
        this.alis = alis;
    }

    public Double getSatis() {
        return satis;
    }

    public void setSatis(Double satis) {
        this.satis = satis;
    }

    public Double getAcilis() {
        return acilis;
    }

    public void setAcilis(Double acilis) {
        this.acilis = acilis;
    }

    public Double getYuksek() {
        return yuksek;
    }

    public void setYuksek(Double yuksek) {
        this.yuksek = yuksek;
    }

    public Double getDusuk() {
        return dusuk;
    }

    public void setDusuk(Double dusuk) {
        this.dusuk = dusuk;
    }

    public Double getKapanis() {
        return kapanis;
    }

    public void setKapanis(Double kapanis) {
        this.kapanis = kapanis;
    }
}
