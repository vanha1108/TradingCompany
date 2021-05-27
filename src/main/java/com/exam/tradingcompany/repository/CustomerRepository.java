package com.exam.tradingcompany.repository;

import com.exam.tradingcompany.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Nguyễn Văn Hà
 * 3:00 AM 5/9/2021
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //Tìm kiếm Khách hàng theo tên đúng
    @Query("select customer from Customer customer where customer.name = ?1")
    public Customer findCustomerByName(String name);

    //Theo địa chỉ đúng
    @Query("select customer from Customer customer where customer.address = ?1")
    public Customer findCustomerByAddress(String address);

    //Theo số điện thoại đúng
    @Query("select customer from Customer customer where customer.phone = ?1")
    public Customer findCustomerByPhone(String phone);


}
