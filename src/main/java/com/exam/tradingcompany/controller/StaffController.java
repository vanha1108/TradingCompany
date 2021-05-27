package com.exam.tradingcompany.controller;

import com.exam.tradingcompany.entities.Staff;
import com.exam.tradingcompany.repository.StaffRepository;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Nguyễn Văn Hà
 * 1:21 AM 5/9/2021
 */
@RestController
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;

    //Lấy ra thông tin tất cả nhân viên
    @GetMapping("/staff")
    public ResponseEntity<?> getAllStaff(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        try {
            List<Staff> staffs = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);
            Page<Staff> staffPage = staffRepository.findAll((paging));
            staffs = staffPage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("staffs", staffs);
            response.put("currentPage", staffPage.getNumber());
            response.put("totalItems", staffPage.getTotalElements());
            response.put("totalPages", staffPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Lấy thông tin nhân viên theo id
    @GetMapping("/staff/{id}")
    public ResponseEntity<?> getStaffById(@PathVariable("id") Long id) {
        try {
            Optional<Staff> staff = staffRepository.findById(id);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Thêm mới một nhân viên
    @PostMapping("/staff")
    public ResponseEntity<?> createStaff(@RequestBody Staff staff) {
        if (GenericValidator.isBlankOrNull(staff.getName()) || GenericValidator.isBlankOrNull(staff.getAddress())
                || GenericValidator.isBlankOrNull(staff.getEmail()) || GenericValidator.isBlankOrNull(staff.getPhone())) {
            return new ResponseEntity<>("Please enter the full information", HttpStatus.BAD_REQUEST);
        }
        try {
            staffRepository.save(staff);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Cập nhật nhân viên theo id
    @PatchMapping("/staff/{id}")
    public ResponseEntity<?> updateStaff(@PathVariable("id") Long id, @RequestBody Staff staff) {
        Optional<Staff> check = staffRepository.findById(id);
        if (!check.isPresent()) {
            return new ResponseEntity<>("Staff not found", HttpStatus.NOT_FOUND);
        }
        if (GenericValidator.isBlankOrNull(staff.getName()) || GenericValidator.isBlankOrNull(staff.getAddress())
                || GenericValidator.isBlankOrNull(staff.getEmail()) || GenericValidator.isBlankOrNull(staff.getPhone())) {
            return new ResponseEntity<>("Please enter the full information", HttpStatus.BAD_REQUEST);
        }
        try {
            staff.setId(id);
            staffRepository.save(staff);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Xóa nhân viên
    @DeleteMapping("/staff/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable("id") Long id) {
        try {
            staffRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
