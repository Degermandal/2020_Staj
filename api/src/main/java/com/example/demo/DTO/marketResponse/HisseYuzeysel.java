package com.example.demo.DTO.marketResponse;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sembolid",
        "sembol",
        "tarih",
        "sektorid",
        "alis",
        "satis",
        "acilis",
        "yuksek",
        "dusuk",
        "yuzdedegisim",
        "aciklama"

        /* "yukseK1",
        "yukseK2",
        "dusuK1",
        "dusuK2",
        "kapanis",
        "kapaniS1",
        "kapaniS2",
        "hacimlot",
        "hacimloT1",
        "hacimloT2",
        "aort",
        "aorT1",
        "aorT2",
        "hacimtldun",
        "hacimyuzdedegisim",
        "hacimtl",
        "hacimtL1",
        "hacimtL2",
        "dunkukapanis",
        "oncekikapanis",
        "izafikapanis",
        "tavan",
        "taban",
        "yilyuksek",
        "yildusuk",
        "ayyuksek",
        "aydusuk",
        "haftayuksek",
        "haftadusuk",
        "oncekiyilkapanis",
        "oncekiaykapanis",
        "oncekihaftakapanis",
        "yilortalama",
        "ayortalama",
        "haftaortalama",
        "yuzdedegisimS1",
        "yuzdedegisimS2",
        "fiyatadimi",
        "kaykar",
        "sermaye",
        "saklamaor",
        "netkar",
        "net",
        "fiyatkaz",
        "piydeg",
        "kapanisfark",
        "donem",
        "ozsermaye",
        "beta",
        "xU100AG", */
})
public class HisseYuzeysel {

    @JsonProperty("sembolid")
    private Integer sembolid;
    @JsonProperty("sembol")
    private String sembol;
    @JsonProperty("tarih")
    private String tarih;
    @JsonProperty("sektorid")
    private Integer sektorid;
    @JsonProperty("alis")
    private Double alis;
    @JsonProperty("satis")
    private Double satis;
    @JsonProperty("acilis")
    private Double acilis;
    @JsonProperty("yuksek")
    private Double yuksek;
    @JsonProperty("dusuk")
    private Double dusuk;
    @JsonProperty("kapanis")
    private Double kapanis;
    @JsonProperty("yuzdedegisim")
    private Double yuzdedegisim;
    @JsonProperty("aciklama")
    private String aciklama;
   ////////////

/*
    @JsonProperty("yukseK1")
    private Double yukseK1;
    @JsonProperty("yukseK2")
    private Double yukseK2;
    @JsonProperty("dusuK1")
    private Double dusuK1;
    @JsonProperty("dusuK2")
    private Double dusuK2;
    @JsonProperty("kapaniS1")
    private Double kapaniS1;
    @JsonProperty("kapaniS2")
    private Double kapaniS2;
    @JsonProperty("hacimlot")
    private Double hacimlot;
    @JsonProperty("hacimloT1")
    private Double hacimloT1;
    @JsonProperty("hacimloT2")
    private Double hacimloT2;
    @JsonProperty("aort")
    private Double aort;
    @JsonProperty("aorT1")
    private Double aorT1;
    @JsonProperty("aorT2")
    private Double aorT2;
    @JsonProperty("hacimtldun")
    private Double hacimtldun;
    @JsonProperty("hacimyuzdedegisim")
    private Double hacimyuzdedegisim;
    @JsonProperty("hacimtl")
    private Double hacimtl;
    @JsonProperty("hacimtL1")
    private Double hacimtL1;
    @JsonProperty("hacimtL2")
    private Double hacimtL2;
    @JsonProperty("dunkukapanis")
    private Double dunkukapanis;
    @JsonProperty("oncekikapanis")
    private Double oncekikapanis;
    @JsonProperty("izafikapanis")
    private Double izafikapanis;
    @JsonProperty("tavan")
    private Double tavan;
    @JsonProperty("taban")
    private Double taban;
    @JsonProperty("yilyuksek")
    private Double yilyuksek;
    @JsonProperty("yildusuk")
    private Double yildusuk;
    @JsonProperty("ayyuksek")
    private Double ayyuksek;
    @JsonProperty("aydusuk")
    private Double aydusuk;
    @JsonProperty("haftayuksek")
    private Double haftayuksek;
    @JsonProperty("haftadusuk")
    private Double haftadusuk;
    @JsonProperty("oncekiyilkapanis")
    private Double oncekiyilkapanis;
    @JsonProperty("oncekiaykapanis")
    private Double oncekiaykapanis;
    @JsonProperty("oncekihaftakapanis")
    private Double oncekihaftakapanis;
    @JsonProperty("yilortalama")
    private Double yilortalama;
    @JsonProperty("ayortalama")
    private Double ayortalama;
    @JsonProperty("haftaortalama")
    private Double haftaortalama;
    @JsonProperty("yuzdedegisimS1")
    private Double yuzdedegisimS1;
    @JsonProperty("yuzdedegisimS2")
    private Double yuzdedegisimS2;

    @JsonProperty("fiyatadimi")
    private Double fiyatadimi;
    @JsonProperty("kaykar")
    private Double kaykar;
    @JsonProperty("sermaye")
    private Double sermaye;
    @JsonProperty("saklamaor")
    private Double saklamaor;
    @JsonProperty("netkar")
    private Double netkar;
    @JsonProperty("net")
    private Double net;
    @JsonProperty("fiyatkaz")
    private Double fiyatkaz;
    @JsonProperty("piydeg")
    private Double piydeg;
    @JsonProperty("kapanisfark")
    private Object kapanisfark;
    @JsonProperty("donem")
    private String donem;
    @JsonProperty("ozsermaye")
    private Double ozsermaye;
    @JsonProperty("beta")
    private Double beta;
    @JsonProperty("xU100AG")
    private Double xU100AG;
*/
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("sembolid")
    public Integer getSembolid() {
        return sembolid;
    }

    @JsonProperty("sembolid")
    public void setSembolid(Integer sembolid) {
        this.sembolid = sembolid;
    }

    @JsonProperty("sembol")
    public String getSembol() {
        return sembol;
    }

    @JsonProperty("sembol")
    public void setSembol(String sembol) {
        this.sembol = sembol;
    }

    @JsonProperty("tarih")
    public String getTarih() {
        return tarih;
    }

    @JsonProperty("tarih")
    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    @JsonProperty("sektorid")
    public Integer getSektorid() {
        return sektorid;
    }

    @JsonProperty("sektorid")
    public void setSektorid(Integer sektorid) {
        this.sektorid = sektorid;
    }

    @JsonProperty("alis")
    public Double getAlis() {
        return alis;
    }

    @JsonProperty("alis")
    public void setAlis(Double alis) {
        this.alis = alis;
    }

    @JsonProperty("satis")
    public Double getSatis() {
        return satis;
    }

    @JsonProperty("satis")
    public void setSatis(Double satis) {
        this.satis = satis;
    }

    @JsonProperty("acilis")
    public Double getAcilis() {
        return acilis;
    }

    @JsonProperty("acilis")
    public void setAcilis(Double acilis) {
        this.acilis = acilis;
    }

    @JsonProperty("yuksek")
    public Double getYuksek() {
        return yuksek;
    }

    @JsonProperty("yuksek")
    public void setYuksek(Double yuksek) {
        this.yuksek = yuksek;
    }


    @JsonProperty("dusuk")
    public Double getDusuk() {
        return dusuk;
    }

    @JsonProperty("dusuk")
    public void setDusuk(Double dusuk) {
        this.dusuk = dusuk;
    }
    @JsonProperty("kapanis")
    public Double getKapanis() {
        return kapanis;
    }

    @JsonProperty("kapanis")
    public void setKapanis(Double kapanis) {
        this.kapanis = kapanis;
    }


    @JsonProperty("yuzdedegisim")
    public Double getYuzdedegisim() {
        return yuzdedegisim;
    }

    @JsonProperty("yuzdedegisim")
    public void setYuzdedegisim(Double yuzdedegisim) {
        this.yuzdedegisim = yuzdedegisim;
    }

    @JsonProperty("aciklama")
    public String getAciklama() {
        return aciklama;
    }

    @JsonProperty("aciklama")
    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    ////////////////////////////77
    /*
    @JsonProperty("yukseK1")
    public Double getYukseK1() {
        return yukseK1;
    }

    @JsonProperty("yukseK1")
    public void setYukseK1(Double yukseK1) {
        this.yukseK1 = yukseK1;
    }

    @JsonProperty("yukseK2")
    public Double getYukseK2() {
        return yukseK2;
    }

    @JsonProperty("yukseK2")
    public void setYukseK2(Double yukseK2) {
        this.yukseK2 = yukseK2;
    }


    @JsonProperty("dusuK1")
    public Double getDusuK1() {
        return dusuK1;
    }

    @JsonProperty("dusuK1")
    public void setDusuK1(Double dusuK1) {
        this.dusuK1 = dusuK1;
    }

    @JsonProperty("dusuK2")
    public Double getDusuK2() {
        return dusuK2;
    }

    @JsonProperty("dusuK2")
    public void setDusuK2(Double dusuK2) {
        this.dusuK2 = dusuK2;
    }



    @JsonProperty("kapaniS1")
    public Double getKapaniS1() {
        return kapaniS1;
    }

    @JsonProperty("kapaniS1")
    public void setKapaniS1(Double kapaniS1) {
        this.kapaniS1 = kapaniS1;
    }

    @JsonProperty("kapaniS2")
    public Double getKapaniS2() {
        return kapaniS2;
    }

    @JsonProperty("kapaniS2")
    public void setKapaniS2(Double kapaniS2) {
        this.kapaniS2 = kapaniS2;
    }

    @JsonProperty("hacimlot")
    public Double getHacimlot() {
        return hacimlot;
    }

    @JsonProperty("hacimlot")
    public void setHacimlot(Double hacimlot) {
        this.hacimlot = hacimlot;
    }

    @JsonProperty("hacimloT1")
    public Double getHacimloT1() {
        return hacimloT1;
    }

    @JsonProperty("hacimloT1")
    public void setHacimloT1(Double hacimloT1) {
        this.hacimloT1 = hacimloT1;
    }

    @JsonProperty("hacimloT2")
    public Double getHacimloT2() {
        return hacimloT2;
    }

    @JsonProperty("hacimloT2")
    public void setHacimloT2(Double hacimloT2) {
        this.hacimloT2 = hacimloT2;
    }

    @JsonProperty("aort")
    public Double getAort() {
        return aort;
    }

    @JsonProperty("aort")
    public void setAort(Double aort) {
        this.aort = aort;
    }

    @JsonProperty("aorT1")
    public Double getAorT1() {
        return aorT1;
    }

    @JsonProperty("aorT1")
    public void setAorT1(Double aorT1) {
        this.aorT1 = aorT1;
    }

    @JsonProperty("aorT2")
    public Double getAorT2() {
        return aorT2;
    }

    @JsonProperty("aorT2")
    public void setAorT2(Double aorT2) {
        this.aorT2 = aorT2;
    }

    @JsonProperty("hacimtldun")
    public Double getHacimtldun() {
        return hacimtldun;
    }

    @JsonProperty("hacimtldun")
    public void setHacimtldun(Double hacimtldun) {
        this.hacimtldun = hacimtldun;
    }

    @JsonProperty("hacimyuzdedegisim")
    public Double getHacimyuzdedegisim() {
        return hacimyuzdedegisim;
    }

    @JsonProperty("hacimyuzdedegisim")
    public void setHacimyuzdedegisim(Double hacimyuzdedegisim) {
        this.hacimyuzdedegisim = hacimyuzdedegisim;
    }

    @JsonProperty("hacimtl")
    public Double getHacimtl() {
        return hacimtl;
    }

    @JsonProperty("hacimtl")
    public void setHacimtl(Double hacimtl) {
        this.hacimtl = hacimtl;
    }

    @JsonProperty("hacimtL1")
    public Double getHacimtL1() {
        return hacimtL1;
    }

    @JsonProperty("hacimtL1")
    public void setHacimtL1(Double hacimtL1) {
        this.hacimtL1 = hacimtL1;
    }

    @JsonProperty("hacimtL2")
    public Double getHacimtL2() {
        return hacimtL2;
    }

    @JsonProperty("hacimtL2")
    public void setHacimtL2(Double hacimtL2) {
        this.hacimtL2 = hacimtL2;
    }

    @JsonProperty("dunkukapanis")
    public Double getDunkukapanis() {
        return dunkukapanis;
    }

    @JsonProperty("dunkukapanis")
    public void setDunkukapanis(Double dunkukapanis) {
        this.dunkukapanis = dunkukapanis;
    }

    @JsonProperty("oncekikapanis")
    public Double getOncekikapanis() {
        return oncekikapanis;
    }

    @JsonProperty("oncekikapanis")
    public void setOncekikapanis(Double oncekikapanis) {
        this.oncekikapanis = oncekikapanis;
    }

    @JsonProperty("izafikapanis")
    public Double getIzafikapanis() {
        return izafikapanis;
    }

    @JsonProperty("izafikapanis")
    public void setIzafikapanis(Double izafikapanis) {
        this.izafikapanis = izafikapanis;
    }

    @JsonProperty("tavan")
    public Double getTavan() {
        return tavan;
    }

    @JsonProperty("tavan")
    public void setTavan(Double tavan) {
        this.tavan = tavan;
    }

    @JsonProperty("taban")
    public Double getTaban() {
        return taban;
    }

    @JsonProperty("taban")
    public void setTaban(Double taban) {
        this.taban = taban;
    }

    @JsonProperty("yilyuksek")
    public Double getYilyuksek() {
        return yilyuksek;
    }

    @JsonProperty("yilyuksek")
    public void setYilyuksek(Double yilyuksek) {
        this.yilyuksek = yilyuksek;
    }

    @JsonProperty("yildusuk")
    public Double getYildusuk() {
        return yildusuk;
    }

    @JsonProperty("yildusuk")
    public void setYildusuk(Double yildusuk) {
        this.yildusuk = yildusuk;
    }

    @JsonProperty("ayyuksek")
    public Double getAyyuksek() {
        return ayyuksek;
    }

    @JsonProperty("ayyuksek")
    public void setAyyuksek(Double ayyuksek) {
        this.ayyuksek = ayyuksek;
    }

    @JsonProperty("aydusuk")
    public Double getAydusuk() {
        return aydusuk;
    }

    @JsonProperty("aydusuk")
    public void setAydusuk(Double aydusuk) {
        this.aydusuk = aydusuk;
    }

    @JsonProperty("haftayuksek")
    public Double getHaftayuksek() {
        return haftayuksek;
    }

    @JsonProperty("haftayuksek")
    public void setHaftayuksek(Double haftayuksek) {
        this.haftayuksek = haftayuksek;
    }

    @JsonProperty("haftadusuk")
    public Double getHaftadusuk() {
        return haftadusuk;
    }

    @JsonProperty("haftadusuk")
    public void setHaftadusuk(Double haftadusuk) {
        this.haftadusuk = haftadusuk;
    }

    @JsonProperty("oncekiyilkapanis")
    public Double getOncekiyilkapanis() {
        return oncekiyilkapanis;
    }

    @JsonProperty("oncekiyilkapanis")
    public void setOncekiyilkapanis(Double oncekiyilkapanis) {
        this.oncekiyilkapanis = oncekiyilkapanis;
    }

    @JsonProperty("oncekiaykapanis")
    public Double getOncekiaykapanis() {
        return oncekiaykapanis;
    }

    @JsonProperty("oncekiaykapanis")
    public void setOncekiaykapanis(Double oncekiaykapanis) {
        this.oncekiaykapanis = oncekiaykapanis;
    }

    @JsonProperty("oncekihaftakapanis")
    public Double getOncekihaftakapanis() {
        return oncekihaftakapanis;
    }

    @JsonProperty("oncekihaftakapanis")
    public void setOncekihaftakapanis(Double oncekihaftakapanis) {
        this.oncekihaftakapanis = oncekihaftakapanis;
    }

    @JsonProperty("yilortalama")
    public Double getYilortalama() {
        return yilortalama;
    }

    @JsonProperty("yilortalama")
    public void setYilortalama(Double yilortalama) {
        this.yilortalama = yilortalama;
    }

    @JsonProperty("ayortalama")
    public Double getAyortalama() {
        return ayortalama;
    }

    @JsonProperty("ayortalama")
    public void setAyortalama(Double ayortalama) {
        this.ayortalama = ayortalama;
    }

    @JsonProperty("haftaortalama")
    public Double getHaftaortalama() {
        return haftaortalama;
    }

    @JsonProperty("haftaortalama")
    public void setHaftaortalama(Double haftaortalama) {
        this.haftaortalama = haftaortalama;
    }

    @JsonProperty("yuzdedegisimS1")
    public Double getYuzdedegisimS1() {
        return yuzdedegisimS1;
    }

    @JsonProperty("yuzdedegisimS1")
    public void setYuzdedegisimS1(Double yuzdedegisimS1) {
        this.yuzdedegisimS1 = yuzdedegisimS1;
    }

    @JsonProperty("yuzdedegisimS2")
    public Double getYuzdedegisimS2() {
        return yuzdedegisimS2;
    }

    @JsonProperty("yuzdedegisimS2")
    public void setYuzdedegisimS2(Double yuzdedegisimS2) {
        this.yuzdedegisimS2 = yuzdedegisimS2;
    }


    @JsonProperty("fiyatadimi")
    public Double getFiyatadimi() {
        return fiyatadimi;
    }

    @JsonProperty("fiyatadimi")
    public void setFiyatadimi(Double fiyatadimi) {
        this.fiyatadimi = fiyatadimi;
    }

    @JsonProperty("kaykar")
    public Double getKaykar() {
        return kaykar;
    }

    @JsonProperty("kaykar")
    public void setKaykar(Double kaykar) {
        this.kaykar = kaykar;
    }

    @JsonProperty("sermaye")
    public Double getSermaye() {
        return sermaye;
    }

    @JsonProperty("sermaye")
    public void setSermaye(Double sermaye) {
        this.sermaye = sermaye;
    }

    @JsonProperty("saklamaor")
    public Double getSaklamaor() {
        return saklamaor;
    }

    @JsonProperty("saklamaor")
    public void setSaklamaor(Double saklamaor) {
        this.saklamaor = saklamaor;
    }

    @JsonProperty("netkar")
    public Double getNetkar() {
        return netkar;
    }

    @JsonProperty("netkar")
    public void setNetkar(Double netkar) {
        this.netkar = netkar;
    }

    @JsonProperty("net")
    public Double getNet() {
        return net;
    }

    @JsonProperty("net")
    public void setNet(Double net) {
        this.net = net;
    }

    @JsonProperty("fiyatkaz")
    public Double getFiyatkaz() {
        return fiyatkaz;
    }

    @JsonProperty("fiyatkaz")
    public void setFiyatkaz(Double fiyatkaz) {
        this.fiyatkaz = fiyatkaz;
    }

    @JsonProperty("piydeg")
    public Double getPiydeg() {
        return piydeg;
    }

    @JsonProperty("piydeg")
    public void setPiydeg(Double piydeg) {
        this.piydeg = piydeg;
    }

    @JsonProperty("kapanisfark")
    public Object getKapanisfark() {
        return kapanisfark;
    }

    @JsonProperty("kapanisfark")
    public void setKapanisfark(Object kapanisfark) {
        this.kapanisfark = kapanisfark;
    }

    @JsonProperty("donem")
    public String getDonem() {
        return donem;
    }

    @JsonProperty("donem")
    public void setDonem(String donem) {
        this.donem = donem;
    }

    @JsonProperty("ozsermaye")
    public Double getOzsermaye() {
        return ozsermaye;
    }

    @JsonProperty("ozsermaye")
    public void setOzsermaye(Double ozsermaye) {
        this.ozsermaye = ozsermaye;
    }

    @JsonProperty("beta")
    public Double getBeta() {
        return beta;
    }

    @JsonProperty("beta")
    public void setBeta(Double beta) {
        this.beta = beta;
    }

    @JsonProperty("xU100AG")
    public Double getXU100AG() {
        return xU100AG;
    }

    @JsonProperty("xU100AG")
    public void setXU100AG(Double xU100AG) {
        this.xU100AG = xU100AG;
    }

   */

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
