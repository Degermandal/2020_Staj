package com.example.demo.service;

import com.example.demo.DTO.PortfolioListDTO;
import com.example.demo.domain.Portfolio;

import java.util.List;

public interface PortfolioService {

    PortfolioListDTO getPortfolioByUserIdentity(String identityId);
}
