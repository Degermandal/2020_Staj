package com.example.demo.controller;

import com.example.demo.DTO.PortfolioListDTO;
import com.example.demo.DTO.PortfolioUser;
import com.example.demo.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/BankingApiV01/Portfolio")
@CrossOrigin
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping(path = "/GetCustomerPortfolio")
    public PortfolioListDTO getPortfolio(@RequestBody PortfolioUser portfolioUser){
        System.out.println("portfolio a girdi");
        return portfolioService.getPortfolioByUserIdentity(portfolioUser.getUserIdentity());
    }


}
