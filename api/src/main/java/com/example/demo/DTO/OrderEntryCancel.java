package com.example.demo.DTO;

import java.util.List;
import java.util.stream.Collectors;

public class OrderEntryCancel {
    private List<String> orderIds;
    private String userIdentityId;

    public List<Long> getOrderIds() {
        List<Long> longIds = orderIds.stream().map(Long::parseLong).collect(Collectors.toList());
        return longIds;
    }

    public void setOrderIds(List<String> orderIds) {
        this.orderIds = orderIds;
    }

    public String getUserIdentityId() {
        return userIdentityId;
    }

    public void setUserIdentityId(String userIdentityId) {
        this.userIdentityId = userIdentityId;
    }
}
