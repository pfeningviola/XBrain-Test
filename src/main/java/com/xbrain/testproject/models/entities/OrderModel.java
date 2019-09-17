package com.xbrain.testproject.models.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private int totalPrice;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "order_product",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private List<Product> orderedProducts;

    public OrderModel(){}

    public OrderModel(String address, int totalPrice, Client client, ArrayList<Product> orderedProducts) {
        this.address = address;
        this.totalPrice = totalPrice;
        this.client = client;
        this.orderedProducts = orderedProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(ArrayList<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }
}
