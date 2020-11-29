package com.example.internlogin.modelOfRequest;

public class GiveNewOrder {
    private String hisseAdi;
    private String emirTipi; // limit piyasa ..
    private String kullaniciIdentity;
    private int miktar;
    private Double fiyat;
    private String periyot; // OO - OS - GB
    private String satisAlis; // s - a

    public GiveNewOrder(String emirTipi, Double fiyat, String hisseAdi, String kullaniciIdentity, int miktar, String periyot, String satisAlis) {
        this.emirTipi = emirTipi;
        this.fiyat = fiyat;
        this.hisseAdi = hisseAdi;
        this.kullaniciIdentity = kullaniciIdentity;
        this.miktar = miktar;
        this.periyot = periyot;
        this.satisAlis = satisAlis;
    }

// Getter Methods

    public String getEmirTipi() {
        return emirTipi;
    }

    public Double getFiyat() {
        return fiyat;
    }

    public String getHisseAdi() {
        return hisseAdi;
    }

    public String getKullaniciIdentity() {
        return kullaniciIdentity;
    }

    public int getMiktar() {
        return miktar;
    }

    public String getPeriyot() {
        return periyot;
    }

    public String getSatisAlis() {
        return satisAlis;
    }

    // Setter Methods

    public void setEmirTipi(String emirTipi) {
        this.emirTipi = emirTipi;
    }

    public void setFiyat(Double fiyat) {
        this.fiyat = fiyat;
    }

    public void setHisseAdi(String hisseAdi) {
        this.hisseAdi = hisseAdi;
    }

    public void setKullaniciIdentity(String kullaniciIdentity) {
        this.kullaniciIdentity = kullaniciIdentity;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public void setPeriyot(String periyot) {
        this.periyot = periyot;
    }

    public void setSatisAlis(String satisAlis) {
        this.satisAlis = satisAlis;
    }
}
