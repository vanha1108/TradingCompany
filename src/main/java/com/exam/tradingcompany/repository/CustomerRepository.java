package com.exam.tradingcompany.repository;

import com.exam.tradingcompany.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Nguyễn Văn Hà
 * 3:00 AM 5/9/2021
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
