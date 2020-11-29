package com.example.demo.DTO;

public class PortfolioUser {
    private String kullaniciKimligi;

    public String getUserIdentity() {
        return kullaniciKimligi;
    }

    public void setUserIdentity(String userIdentity) {
        this.kullaniciKimligi = userIdentity;
    }
}
