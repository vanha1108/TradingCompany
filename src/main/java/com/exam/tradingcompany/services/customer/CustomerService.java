package com.exam.tradingcompany.services.customer;

import com.exam.tradingcompany.entities.Customer;

import java.util.Optional;

public interface CustomerService {
    //tìm kiếm thông tin khách hàng
    Customer searchCustomer(String name, String address, String phone);

    //Tìm kiếm khách hàng bởi id
    Optional<Customer> findById(Long id);
}
