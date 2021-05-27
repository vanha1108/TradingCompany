package com.exam.tradingcompany.services.staff;

import com.exam.tradingcompany.entities.Staff;
import com.exam.tradingcompany.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Override
    public Optional<Staff> findById(Long id) {
        return staffRepository.findById(id);
    }
}
