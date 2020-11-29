package com.example.internlogin.modelOfResponse.GetOrder;

import java.util.ArrayList;

public class CancelOrderRequest {
    ArrayList< Object > orderIds = new ArrayList < Object > ();
    private String userIdentityId;

    // Getter Methods


    public ArrayList<Object> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(ArrayList<Object> orderIds) {
        this.orderIds = orderIds;
    }

    public String getUserIdentityId() {
        return userIdentityId;
    }

    // Setter Methods

    public void setUserIdentityId(String userIdentityId) {
        this.userIdentityId = userIdentityId;
    }
}