package com.example.demo.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OrderEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_type_id",referencedColumnName = "id")
    private OrderType orderType;

    private Boolean sellOrBuy;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "asset_id",referencedColumnName = "id")
    private Asset asset;

    private int orderQuantity; // bu değişmicek. alttaki ile islem yaptığımız için değişicek.
    private int quantity;

    private Double price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderPeriot_id",referencedColumnName = "id")
    private OrderPeriot orderPeriot; // öğleden önce - sonra , gün boyu

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderStatus_id",referencedColumnName = "id")
    private OrderStatus orderStatus; // bekliyor - iptal edildi - başarılı

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "AccountInformation_id",referencedColumnName = "id")
    private AccountInformation accountInformation;

    private LocalDateTime localDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Boolean getSellOrBuy() {
        return sellOrBuy;
    }

    public void setSellOrBuy(Boolean sellOrBuy) {
        this.sellOrBuy = sellOrBuy;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderPeriot getOrderPeriot() {
        return orderPeriot;
    }

    public void setOrderPeriot(OrderPeriot orderPeriot) {
        this.orderPeriot = orderPeriot;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public AccountInformation getAccountInformation() {
        return accountInformation;
    }

    public void setAccountInformation(AccountInformation accountInformation) {
        this.accountInformation = accountInformation;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
