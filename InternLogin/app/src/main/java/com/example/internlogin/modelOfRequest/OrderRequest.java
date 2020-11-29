package com.example.internlogin.modelOfRequest;

public class OrderRequest {
    private String hisseAdi;
    private String emirTipi; // limit piyasa ..
    private String kullaniciIdentity;
    private int miktar;
    private Double fiyat;
    private String periyot; // OO - OS - GB
    private String satisAlis; // s - a

    public OrderRequest(String hisseAdi, String emirTipi, String kullaniciIdentity, int miktar, Double fiyat, String periyot, String satisAlis) {
        this.hisseAdi = hisseAdi;
        this.emirTipi = emirTipi;
        this.kullaniciIdentity = kullaniciIdentity;
        this.miktar = miktar;
        this.fiyat = fiyat;
        this.periyot = periyot;
        this.satisAlis = satisAlis;
    }

    public String getHisseAdi() {
        return hisseAdi;
    }

    public void setHisseAdi(String hisseAdi) {
        this.hisseAdi = hisseAdi;
    }

    public String getEmirTipi() {
        return emirTipi;
    }

    public void setEmirTipi(String emirTipi) {
        this.emirTipi = emirTipi;
    }

    public String getKullaniciIdentity() {
        return kullaniciIdentity;
    }

    public void setKullaniciIdentity(String kullaniciIdentity) {
        this.kullaniciIdentity = kullaniciIdentity;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public Double getFiyat() {
        return fiyat;
    }

    public void setFiyat(Double fiyat) {
        this.fiyat = fiyat;
    }

    public String getPeriyot() {
        return periyot;
    }

    public void setPeriyot(String periyot) {
        this.periyot = periyot;
    }

    public String getSatisAlis() {
        return satisAlis;
    }

    public void setSatisAlis(String satisAlis) {
        this.satisAlis = satisAlis;
    }
}
