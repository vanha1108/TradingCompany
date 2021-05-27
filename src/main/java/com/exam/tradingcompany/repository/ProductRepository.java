package com.exam.tradingcompany.repository;

import com.exam.tradingcompany.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
