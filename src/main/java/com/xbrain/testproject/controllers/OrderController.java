package com.xbrain.testproject.controllers;

import com.xbrain.testproject.models.dtos.ErrorMessage;
import com.xbrain.testproject.models.dtos.OrderRequestDTO;
import com.xbrain.testproject.models.entities.Product;
import com.xbrain.testproject.services.ClientService;
import com.xbrain.testproject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

//the structure of the created database is more complex then the necessary to solve this task,
// but in this way the application is more realistic and open to further developing
@RestController
public class OrderController {
    private ClientService clientService;
    private ProductService productService;

    @Autowired
    public OrderController(ClientService clientService, ProductService productService){
        this.clientService = clientService;
        this.productService = productService;
    }

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

        if (!clientService.isClientRegistered(clientId)){
            return ResponseEntity.status(406).body(new ErrorMessage("Given clientId is not registered"));
        }

        ArrayList<Product> orderedProducts = new ArrayList<>();
        for (Long id : orderedProductCodes) {
            if (!productService.existProduct(id)) {
                return ResponseEntity.status(406).body(new ErrorMessage(id + " product code does not exist."));
            } else {
                orderedProducts.add(productService.findProductById(id));
            }
        }

        return ResponseEntity.status(200).body();
    }
}
