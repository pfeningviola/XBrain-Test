package com.xbrain.testproject.services;

import com.xbrain.testproject.models.dtos.DeliveryRabbit;
import com.xbrain.testproject.models.entities.Client;
import com.xbrain.testproject.models.entities.OrderModel;
import com.xbrain.testproject.models.entities.Product;
import com.xbrain.testproject.repositories.OrderRepository;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public OrderModel saveOrder(Client client, ArrayList<Product> orderedProducts, int totalPrice, String address){
        return orderRepository.save(new OrderModel(address, totalPrice, client, orderedProducts));
    }

    public void sendMessage(Long orderId, String address){
        rabbitTemplate.convertAndSend(this.queue.getName(), new DeliveryRabbit(orderId, address));
    }
}
