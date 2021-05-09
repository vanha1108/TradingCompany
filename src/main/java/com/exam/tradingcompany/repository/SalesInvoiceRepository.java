package com.exam.tradingcompany.repository;

import com.exam.tradingcompany.entities.SalesInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Nguyễn Văn Hà
 * 9:32 PM 5/9/2021
 */
@Repository
public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Long> {
}
