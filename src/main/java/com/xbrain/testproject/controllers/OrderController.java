package com.xbrain.testproject.controllers;

import com.xbrain.testproject.models.dtos.ErrorMessage;
import com.xbrain.testproject.models.dtos.OrderRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class OrderController {

    @PostMapping("/order")
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        Long clientId = orderRequestDTO.getClientId();
        ArrayList<Long> orderedProductCodes = orderRequestDTO.getOrderedProductCodes();
        int price = orderRequestDTO.getTotalPrice();
        String address = orderRequestDTO.getAddress();

        if (clientId == null) {
            return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter: clientId"));
        }

        if (orderedProductCodes == null || orderedProductCodes.isEmpty()) {
            return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter: orderedProductCodes"));
        }

        if (address == null || address.isEmpty()) {
            return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter: address"));
        }

        if (price < 0) {
            return ResponseEntity.status(400).body(new ErrorMessage("Price is invalid"));
        }

        return ResponseEntity.status(200).body();
    }
}
