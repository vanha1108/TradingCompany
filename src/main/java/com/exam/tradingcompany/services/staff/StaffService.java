package com.exam.tradingcompany.services.staff;

import com.exam.tradingcompany.entities.Customer;
import com.exam.tradingcompany.entities.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    //Tìm kiếm thông tin theo id
    Optional<Staff> findById(Long id);

    Page<Staff> findAll(Pageable pageable);

    void save(Staff staff);

    void deleteById(Long id);
}
