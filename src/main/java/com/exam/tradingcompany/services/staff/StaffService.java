package com.exam.tradingcompany.services.staff;

import com.exam.tradingcompany.entities.Customer;
import com.exam.tradingcompany.entities.Staff;

import java.util.Optional;

public interface StaffService {
    //Tìm kiếm thông tin theo id
    Optional<Staff> findById(Long id);
}
