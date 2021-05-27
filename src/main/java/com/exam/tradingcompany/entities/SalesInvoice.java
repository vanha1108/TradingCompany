package com.exam.tradingcompany.entities;

import org.hibernate.validator.constraints.Currency;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Nguyễn Văn Hà
 * 12:34 AM 5/9/2021
 */
@Entity
@Table(name = "salesinvoice")
public class SalesInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    @Column
    private String nameSalesStaff;

    @Column
    private double price;

    @Column
    private int quantity;

    @Column
    private double total;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private  Customer customer;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private  Staff staff;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "inventorydeliverynote_id", referencedColumnName = "id")
    private InventoryDeliveryNote inventoryDeliveryNote;



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

    public String getNameSalesStaff() {
        return nameSalesStaff;
    }

    public void setNameSalesStaff(String nameSalesStaff) {
        this.nameSalesStaff = nameSalesStaff;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public InventoryDeliveryNote getInventoryDeliveryNote() {
        return inventoryDeliveryNote;
    }

    public void setInventoryDeliveryNote(InventoryDeliveryNote inventoryDeliveryNote) {
        this.inventoryDeliveryNote = inventoryDeliveryNote;
    }
}
