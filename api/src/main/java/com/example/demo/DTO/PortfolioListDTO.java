package com.example.demo.DTO;

import java.util.ArrayList;
import java.util.List;

public class PortfolioListDTO {
    private double toplamBakiye;

    private List<PortfolioBankDTO> portfolioBankDTOS = new ArrayList<>();

    public double getToplamBakiye() {
        return toplamBakiye;
    }

    public void setToplamBakiye(double toplamBakiye) {
        this.toplamBakiye = toplamBakiye;
    }

    public List<PortfolioBankDTO> getPortfolioBankDTOS() {
        return portfolioBankDTOS;
    }

    public void setPortfolioBankDTOS(List<PortfolioBankDTO> portfolioBankDTOS) {
        this.portfolioBankDTOS = portfolioBankDTOS;
    }
}
