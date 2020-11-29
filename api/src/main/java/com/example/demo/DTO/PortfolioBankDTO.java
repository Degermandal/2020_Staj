package com.example.demo.DTO;

import java.util.ArrayList;
import java.util.List;

public class PortfolioBankDTO {
    private double toplamBakiye;
    private String bankName;
    private List<PortfolioKayitDetayDTO> degerliMadenler = new ArrayList<>();
    private List<PortfolioKayitDetayDTO> nakit = new ArrayList<>();
    private List<PortfolioKayitDetayDTO> doviz = new ArrayList<>();
    private List<PortfolioKayitDetayDTO> hisse = new ArrayList<>();

    public double getToplamBakiye() {
        return toplamBakiye;
    }

    public void setToplamBakiye(double toplamBakiye) {
        this.toplamBakiye = toplamBakiye;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<PortfolioKayitDetayDTO> getDegerliMadenler() {
        return degerliMadenler;
    }

    public void setDegerliMadenler(List<PortfolioKayitDetayDTO> degerliMadenler) {
        this.degerliMadenler = degerliMadenler;
    }

    public List<PortfolioKayitDetayDTO> getNakit() {
        return nakit;
    }

    public void setNakit(List<PortfolioKayitDetayDTO> nakit) {
        this.nakit = nakit;
    }

    public List<PortfolioKayitDetayDTO> getDoviz() {
        return doviz;
    }

    public void setDoviz(List<PortfolioKayitDetayDTO> doviz) {
        this.doviz = doviz;
    }

    public List<PortfolioKayitDetayDTO> getHisse() {
        return hisse;
    }

    public void setHisse(List<PortfolioKayitDetayDTO> hisse) {
        this.hisse = hisse;
    }
}
