package com.example.demo.DTO;

public class PortfolioKayitDetayDTO {
    private String tur;
    private double miktar;
    private double deger;
    private double kar;

    public PortfolioKayitDetayDTO(String tur, double miktar, double deger, double kar) {
        this.tur = tur;
        this.miktar = miktar;
        this.deger = deger;
        this.kar = kar;
    }

    public PortfolioKayitDetayDTO() {
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public double getMiktar() {
        return miktar;
    }

    public void setMiktar(double miktar) {
        this.miktar = miktar;
    }

    public double getDeger() {
        return deger;
    }

    public void setDeger(double deger) {
        this.deger = deger;
    }

    public double getKar() {
        return kar;
    }

    public void setKar(double kar) {
        this.kar = kar;
    }
}
