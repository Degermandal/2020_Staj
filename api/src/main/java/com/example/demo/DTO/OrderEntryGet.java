package com.example.demo.DTO;

public class OrderEntryGet {
    private Long id;
    private String hisseAdi;
    private double miktar;
    private String alisSatis;
    private double fiyat;
    private String durum;
    private  double degisenMiktar;

    public double getDegisenMiktar() {
        return degisenMiktar;
    }

    public void setDegisenMiktar(double degisenMiktar) {
        this.degisenMiktar = degisenMiktar;
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

    public double getMiktar() {
        return miktar;
    }

    public void setMiktar(double miktar) {
        this.miktar = miktar;
    }

    public String getAlisSatis() {
        return alisSatis;
    }

    public void setAlisSatis(String alisSatis) {
        this.alisSatis = alisSatis;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }
}
