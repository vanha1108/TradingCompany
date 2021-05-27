package com.exam.tradingcompany.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Nguyễn Văn Hà
 * 12:37 AM 5/9/2021
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String model;

    @Column
    private String brand;

    @Column
    private String company;

    @Column
    private String description;

    @Column(name = "sellingprice")
    private double sellingPrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "inventoryreceivingnote", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id"))
    private List<Staff> staffReceives = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "inventorydeliverynote", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id"))
    private List<Staff> staffDeliveries = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Staff> getStaffReceives() {
        return staffReceives;
    }

    public void setStaffReceives(List<Staff> staffReceives) {
        this.staffReceives = staffReceives;
    }

    public List<Staff> getStaffDeliveries() {
        return staffDeliveries;
    }

    public void setStaffDeliveries(List<Staff> staffDeliveries) {
        this.staffDeliveries = staffDeliveries;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
