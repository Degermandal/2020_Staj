package com.example.demo.DTO;

import org.springframework.web.bind.annotation.RequestMapping;

public class UserLoginDTO {
    private String kullaniciKimlikNumarasi;
    private String kullaniciAdi;
    private String kullaniciSifre;

    public String getKullaniciKimlikNumarasi() {
        return kullaniciKimlikNumarasi;
    }

    public void setKullaniciKimlikNumarasi(String kullaniciKimlikNumarasi) {
        this.kullaniciKimlikNumarasi = kullaniciKimlikNumarasi;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getKullaniciSifre() {
        return kullaniciSifre;
    }

    public void setKullaniciSifre(String kullaniciSifre) {
        this.kullaniciSifre = kullaniciSifre;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "kullaniciKimlikNumarasi='" + kullaniciKimlikNumarasi + '\'' +
                ", kullaniciAdi='" + kullaniciAdi + '\'' +
                ", kullaniciSifre='" + kullaniciSifre + '\'' +
                '}';
    }
}
