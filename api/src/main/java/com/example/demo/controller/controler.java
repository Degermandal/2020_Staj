package com.example.demo.controller;

import com.example.demo.DTO.*;
import com.example.demo.DTO.marketResponse.MarketDataDTO;
import com.example.demo.service.ApiService;
import com.example.demo.service.BankService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/BankingApiV01")
@CrossOrigin
@Slf4j
public class controler {

    @Autowired
    private UserService userService;

    @Autowired
    private ApiService apiService;

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/login")
    public UserDTO login(@RequestBody UserLoginDTO userLoginDTO){
        System.out.println("userLoginDTO = " + userLoginDTO);
        return userService.getUser(userLoginDTO.getKullaniciKimlikNumarasi(), userLoginDTO.getKullaniciSifre());
    }

    @GetMapping("/GetStockMarket")
    public List<MarketDataDTO> getApiFromHurriyet(){
        System.out.println("stock market ");
        return apiService.getMarket();
    }

    @GetMapping("/example")
    public String qwe(){
        return "example";
    }

    @PostMapping("/InsertOrder")
    public Boolean newOrder(@RequestBody OrderDTO orderDTO) {
        System.out.println("Insert order girdi");
        return orderService.createOrder(orderDTO);
    }

    @PostMapping("/GetOrder")
    public List<OrderEntryGet> getOrders(@RequestBody OrderEntryRequest orderEntryRequest){
        System.out.println("\t GET ORDEERR");
        return orderService.getOrderEntry(orderEntryRequest.getIdentityId());
    }

    @PostMapping("/CancelOrder")
    public List<OrderEntryGet> cancelOrder(@RequestBody OrderEntryCancel orderEntryCancel){
        return orderService.cancelOrderEntries(orderEntryCancel);
    }

    // +++
    @PostMapping("/GetUserInformation")
    public AccountInformationGetDTO getAccountInformation(@RequestBody AccountInformationByBank accountInformationByBank){
        return userService.getUserAccountInformation(accountInformationByBank);
    }
}
