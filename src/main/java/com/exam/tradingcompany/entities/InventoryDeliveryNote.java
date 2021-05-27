package com.exam.tradingcompany.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Nguyễn Văn Hà
 * 12:33 AM 5/9/2021
 */
@Entity
@Table(name = "inventorydeliverynote")
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDeliveryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @OneToOne(mappedBy = "inventoryDeliveryNote")
    private SalesInvoice salesInvoice;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private int amount;

    @Column
    private int price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public SalesInvoice getOrder() {
        return salesInvoice;
    }

    public void setOrder(SalesInvoice order) {
        this.salesInvoice = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
