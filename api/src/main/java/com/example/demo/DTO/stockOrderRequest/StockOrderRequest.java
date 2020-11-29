package com.example.demo.DTO.stockOrderRequest;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockOrderRequest {
    private String stockSymbol;
    private String orderType;
    private String spanType;
}
