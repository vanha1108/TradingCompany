package com.exam.tradingcompany.repository;

import com.exam.tradingcompany.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Nguyễn Văn Hà
 * 1:20 AM 5/9/2021
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
