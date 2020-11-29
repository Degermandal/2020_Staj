package com.example.demo.DTO.stockOrderResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StockOrderResponseDTO {
    private String transactionResult;

    private String transactionMessage;

    private String accountBalance;
}
