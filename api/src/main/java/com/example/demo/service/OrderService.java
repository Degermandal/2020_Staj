package com.example.demo.service;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.OrderEntryCancel;
import com.example.demo.DTO.OrderEntryGet;

import java.util.List;

public interface OrderService {

    Boolean createOrder(OrderDTO order);

    List<OrderEntryGet> getOrderEntry(String identityId);

    List<OrderEntryGet> cancelOrderEntries(OrderEntryCancel orderEntryCancel);
}
