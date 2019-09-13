package com.xbrain.testproject.services;

import com.xbrain.testproject.models.entities.Client;
import com.xbrain.testproject.models.entities.OrderModel;
import com.xbrain.testproject.models.entities.Product;
import com.xbrain.testproject.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderModel saveOrder(Client client, ArrayList<Product> orderedProducts, int totalPrice, String address){
        return orderRepository.save(new OrderModel(address, totalPrice, client, orderedProducts));
    }
}
