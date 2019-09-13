package com.xbrain.testproject.models.dtos;

import java.util.ArrayList;

public class OrderRequestDTO {
    private Long clientId;
    private ArrayList<Long> orderedProductCodes;
    private int totalPrice;
    private String address;

    public OrderRequestDTO(){}

    public OrderRequestDTO(Long clientId, ArrayList<Long> orderedProductCodes,
                           int totalPrice, String address) {
        this.clientId = clientId;
        this.orderedProductCodes = orderedProductCodes;
        this.totalPrice = totalPrice;
        this.address = address;
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
