package com.xbrain.testproject.services;

import com.xbrain.testproject.models.entities.Client;
import com.xbrain.testproject.models.entities.OrderModel;
import com.xbrain.testproject.models.entities.Product;
import com.xbrain.testproject.repositories.OrderRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    private static OrderService orderService;
    private static Client client;
    private static Product product1;
    private static Product product2;

    private static OrderRepository orderRepositoryMock = Mockito.mock(OrderRepository.class);

    @BeforeClass
    public static void init() {
        orderService = new OrderService(orderRepositoryMock);
        client = new Client("John", "john@gmail.com", "hello");
        client.setId(2L);
        product1 = new Product(200, "cadeira");
        product1.setId(1L);
        product2 = new Product(500, "mesa");
        product2.setId(2L);
    }

    //tryed to test the message sending and receiving via RabbitMQ, but I didn't find working solution
    @Test
    public void saveOrderShouldReturnProperResult_when_OrderIsSaved() {
        OrderModel expectedOrder = new OrderModel("Londrina", 3000, client, new ArrayList<>(Arrays.asList(product1, product2)));

        when(orderRepositoryMock.save(any())).thenReturn(expectedOrder);

        OrderModel resultOrder = orderService.saveOrder(client, new ArrayList<>(Arrays.asList(product1, product2)), 3000, "Londrina");
        assertEquals(expectedOrder, resultOrder);

        verify(orderRepositoryMock, Mockito.times(1)).save(any());
    }
}
