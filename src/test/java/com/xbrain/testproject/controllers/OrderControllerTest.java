package com.xbrain.testproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xbrain.testproject.models.dtos.OrderRequestDTO;
import com.xbrain.testproject.models.entities.Client;
import com.xbrain.testproject.models.entities.OrderModel;
import com.xbrain.testproject.models.entities.Product;
import com.xbrain.testproject.services.ClientService;
import com.xbrain.testproject.services.OrderService;
import com.xbrain.testproject.services.ProductService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    private static MediaType contentType;
    private static OrderRequestDTO orderRequestDTO;
    private static Client client;
    private static Product product1;
    private static Product product2;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderServiceMock;
    @MockBean
    private ProductService productServiceMock;
    @MockBean
    private ClientService clientServiceMock;


    @BeforeClass
    public static void init() {
        orderRequestDTO = new OrderRequestDTO();
        contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8"));
        client = new Client("John", "john@gmail.com", "hello");
        client.setId(2L);
        product1 = new Product(200, "cadeira");
        product1.setId(1L);
        product2 = new Product(500, "mesa");
        product2.setId(2L);
    }

    @Test
    public void createOrderShouldReturnError_when_clientIdIsMissing() throws Exception {
        orderRequestDTO.setClientId(null);
        String orderRequestDTOJson = objectMapper.writeValueAsString(orderRequestDTO);
        String expectedErrorMessage = "Missing parameter: clientId";

        mockMvc.perform(post("/order")
                .contentType(contentType)
                .content(orderRequestDTOJson))
                .andExpect(status().is(400))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.status", is("error")))
                .andExpect(jsonPath("$.message", is(expectedErrorMessage)))
                .andDo(print());
    }

    @Test
    public void createOrderShouldReturnError_when_orderedProductCodesIsMissing() throws Exception {
        orderRequestDTO.setClientId(2L);
        orderRequestDTO.setOrderedProductCodes(null);
        String orderRequestDTOJson = objectMapper.writeValueAsString(orderRequestDTO);
        String expectedErrorMessage = "Missing parameter: orderedProductCodes";

        mockMvc.perform(post("/order")
                .contentType(contentType)
                .content(orderRequestDTOJson))
                .andExpect(status().is(400))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.status", is("error")))
                .andExpect(jsonPath("$.message", is(expectedErrorMessage)))
                .andDo(print());
    }

    @Test
    public void createOrderShouldReturnError_when_orderedProductCodesIsEmpty() throws Exception {
        orderRequestDTO.setClientId(2L);
        orderRequestDTO.setOrderedProductCodes(new ArrayList<>());
        String orderRequestDTOJson = objectMapper.writeValueAsString(orderRequestDTO);
        String expectedErrorMessage = "Missing parameter: orderedProductCodes";

        mockMvc.perform(post("/order")
                .contentType(contentType)
                .content(orderRequestDTOJson))
                .andExpect(status().is(400))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.status", is("error")))
                .andExpect(jsonPath("$.message", is(expectedErrorMessage)))
                .andDo(print());
    }

    @Test
    public void createOrderShouldReturnError_when_addressIsMissing() throws Exception {
        orderRequestDTO.setClientId(2L);
        orderRequestDTO.setOrderedProductCodes(new ArrayList<>(Arrays.asList(1L, 2L)));
        orderRequestDTO.setAddress(null);
        String orderRequestDTOJson = objectMapper.writeValueAsString(orderRequestDTO);
        String expectedErrorMessage = "Missing parameter: address";

        mockMvc.perform(post("/order")
                .contentType(contentType)
                .content(orderRequestDTOJson))
                .andExpect(status().is(400))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.status", is("error")))
                .andExpect(jsonPath("$.message", is(expectedErrorMessage)))
                .andDo(print());
    }

    @Test
    public void createOrderShouldReturnError_when_addressIsEmpty() throws Exception {
        orderRequestDTO.setClientId(2L);
        orderRequestDTO.setOrderedProductCodes(new ArrayList<>(Arrays.asList(1L, 2L)));
        orderRequestDTO.setAddress("  ");
        String orderRequestDTOJson = objectMapper.writeValueAsString(orderRequestDTO);
        String expectedErrorMessage = "Missing parameter: address";

        mockMvc.perform(post("/order")
                .contentType(contentType)
                .content(orderRequestDTOJson))
                .andExpect(status().is(400))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.status", is("error")))
                .andExpect(jsonPath("$.message", is(expectedErrorMessage)))
                .andDo(print());
    }

    @Test
    public void createOrderShouldReturnError_when_totalPriceIsLessThanZero() throws Exception {
        orderRequestDTO.setClientId(2L);
        orderRequestDTO.setOrderedProductCodes(new ArrayList<>(Arrays.asList(1L, 2L)));
        orderRequestDTO.setAddress("Londrina");
        orderRequestDTO.setTotalPrice(-300);
        String orderRequestDTOJson = objectMapper.writeValueAsString(orderRequestDTO);
        String expectedErrorMessage = "Price is invalid";

        mockMvc.perform(post("/order")
                .contentType(contentType)
                .content(orderRequestDTOJson))
                .andExpect(status().is(400))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.status", is("error")))
                .andExpect(jsonPath("$.message", is(expectedErrorMessage)))
                .andDo(print());
    }

    @Test
    public void createOrderShouldReturnError_when_clientIdIsNotRegistered() throws Exception {
        orderRequestDTO.setClientId(5L);
        orderRequestDTO.setAddress("Londrina");
        orderRequestDTO.setOrderedProductCodes(new ArrayList<>(Arrays.asList(1L, 2L)));
        orderRequestDTO.setTotalPrice(3000);
        String orderDTOJson = objectMapper.writeValueAsString(orderRequestDTO);
        String expectedErrorMessage = "Given clientId is not registered";

        when(clientServiceMock.isClientRegistered(any())).thenReturn(false);

        mockMvc.perform(post("/order")
                .contentType(contentType)
                .content(orderDTOJson))
                .andExpect(status().is(406))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.status", is("error")))
                .andExpect(jsonPath("$.message", is(expectedErrorMessage)))
                .andDo(print());

        verify(clientServiceMock, Mockito.times(1)).isClientRegistered(5L);
    }

    @Test
    public void createOrderShouldReturnError_when_productCodeIsNotRegistered() throws Exception {
        orderRequestDTO.setClientId(2L);
        orderRequestDTO.setAddress("Londrina");
        orderRequestDTO.setOrderedProductCodes(new ArrayList<>(Arrays.asList(10L)));
        orderRequestDTO.setTotalPrice(3000);
        String orderDTOJson = objectMapper.writeValueAsString(orderRequestDTO);
        String expectedErrorMessage = "10 product code does not exist.";

        when(clientServiceMock.isClientRegistered(any())).thenReturn(true);
        when(productServiceMock.existProduct(any())).thenReturn(false);

        mockMvc.perform(post("/order")
                .contentType(contentType)
                .content(orderDTOJson))
                .andExpect(status().is(406))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.status", is("error")))
                .andExpect(jsonPath("$.message", is(expectedErrorMessage)))
                .andDo(print());

        verify(clientServiceMock, Mockito.times(1)).isClientRegistered(2L);
        verify(productServiceMock, Mockito.times(1)).existProduct(10L);
    }

    @Test
    public void createOrderShouldReturnSavedOrderDTO_when_allParametersAreOK() throws Exception {
        orderRequestDTO.setClientId(2L);
        orderRequestDTO.setAddress("Londrina");
        orderRequestDTO.setOrderedProductCodes(new ArrayList<>(Arrays.asList(1L, 2L)));
        orderRequestDTO.setTotalPrice(3000);
        String orderDTOJson = objectMapper.writeValueAsString(orderRequestDTO);
        OrderModel savedOrder = new OrderModel("Londrina", 3000, client, new ArrayList<>(Arrays.asList(product1, product2)));
        savedOrder.setId(1L);

        when(clientServiceMock.isClientRegistered(any())).thenReturn(true);
        when(productServiceMock.existProduct(any())).thenReturn(true);
        when(orderServiceMock.saveOrder(any(), any(), anyInt(), any())).thenReturn(savedOrder);

        mockMvc.perform(post("/order")
                .contentType(contentType)
                .content(orderDTOJson))
                .andExpect(status().is(200))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.orderId", is(1)))
                .andExpect(jsonPath("$.clientId", is(2)))
                .andExpect(jsonPath("$.orderedProductCodes[0]", is(1)))
                .andExpect(jsonPath("$.orderedProductCodes[1]", is(2)))
                .andExpect(jsonPath("$.totalPrice", is(3000)))
                .andExpect(jsonPath("$.address", is("Londrina")))
                .andDo(print());

        verify(clientServiceMock, Mockito.times(1)).isClientRegistered(2L);
        verify(productServiceMock, Mockito.times(1)).existProduct(1L);
        verify(productServiceMock, Mockito.times(1)).existProduct(2L);
        verify(orderServiceMock, Mockito.times(1)).saveOrder(any(), any(), anyInt(), any());
        verify(orderServiceMock, Mockito.times(1)).sendMessage(1L, "Londrina");
    }
}
