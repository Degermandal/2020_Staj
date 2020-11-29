package com.example.demo.service;

import com.example.demo.DTO.marketResponse.MarketDataDTO;

import java.util.List;

public interface ApiService {
    List<MarketDataDTO> getMarket();
}
