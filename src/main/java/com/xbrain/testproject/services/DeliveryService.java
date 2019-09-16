package com.xbrain.testproject.services;

import com.xbrain.testproject.models.dtos.DeliveryRabbit;
import com.xbrain.testproject.models.entities.Delivery;
import com.xbrain.testproject.repositories.DeliveryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    private DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @RabbitListener(queues = {"${queue.order.name}"})
    public void receiveDeliveryMessage(@Payload DeliveryRabbit delivery){
        saveDelivery(delivery.getOrderId(), delivery.getAddress());
    }

    public void saveDelivery(Long orderId, String address){
        deliveryRepository.save(new Delivery(orderId, address));
    }
}
