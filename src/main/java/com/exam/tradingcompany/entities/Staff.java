package com.exam.tradingcompany.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Nguyễn Văn Hà
 * 12:24 AM 5/9/2021
 */
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String email;

    @ManyToMany(mappedBy = "staffDeliveries")
    private List<Product> productDeliveries = new ArrayList<>();

    @ManyToMany(mappedBy = "staffDeliveries")
    private List<Product> productRecieves = new ArrayList<>();

    @OneToMany(mappedBy = "staff")
    private List<Order> orders = new ArrayList<>();

    public Staff() {
    }

    public Staff(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Product> getProductDeliveries() {
        return productDeliveries;
    }

    public void setProductDeliveries(List<Product> productDeliveries) {
        this.productDeliveries = productDeliveries;
    }

    public List<Product> getProductRecieves() {
        return productRecieves;
    }

    public void setProductRecieves(List<Product> productRecieves) {
        this.productRecieves = productRecieves;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
