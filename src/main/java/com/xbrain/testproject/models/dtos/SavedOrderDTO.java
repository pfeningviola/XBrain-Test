package com.xbrain.testproject.models.dtos;

import java.util.ArrayList;

public class SavedOrderDTO {
    private Long orderId;
    private Long clientId;
    private ArrayList<Long> orderedProductCodes;
    private int totalPrice;
    private String address;

    public SavedOrderDTO() {
    }

    public SavedOrderDTO(Long orderId, Long clientId,
                         ArrayList<Long> orderedProductCodes, int totalPrice,
                         String address) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderedProductCodes = orderedProductCodes;
        this.totalPrice = totalPrice;
        this.address = address;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public ArrayList<Long> getOrderedProductCodes() {
        return orderedProductCodes;
    }

    public void setOrderedProductCodes(ArrayList<Long> orderedProductCodes) {
        this.orderedProductCodes = orderedProductCodes;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
