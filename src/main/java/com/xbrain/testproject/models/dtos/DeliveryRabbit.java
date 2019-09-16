package com.xbrain.testproject.models.dtos;

import java.io.Serializable;

public class DeliveryRabbit implements Serializable {
    private Long orderId;
    private String address;

    public DeliveryRabbit() {
    }

    public DeliveryRabbit(Long orderId, String address) {
        this.orderId = orderId;
        this.address = address;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
