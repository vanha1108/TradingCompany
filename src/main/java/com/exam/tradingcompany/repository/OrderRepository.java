package com.exam.tradingcompany.repository;

import com.exam.tradingcompany.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Nguyễn Văn Hà
 * 9:24 PM 5/9/2021
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
