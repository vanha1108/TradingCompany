package com.exam.tradingcompany.entities.dto;

//Dùng DTO này tạo ra một biểu mẫu mới, custom cách trả về của thông tin đang yêu cầu
public class ProductWarehouse {
    private String name;

    private Double received;

    private Double delivery;

    private Double balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getReceived() {
        return received;
    }

    public void setReceived(Double received) {
        this.received = received;
    }

    public Double getDelivery() {
        return delivery;
    }

    public void setDelivery(Double delivery) {
        this.delivery = delivery;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
