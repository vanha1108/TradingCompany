package com.exam.tradingcompany.controller;

import com.exam.tradingcompany.entities.Order;
import com.exam.tradingcompany.entities.SalesInvoice;
import com.exam.tradingcompany.repository.SalesInvoiceRepository;
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
 * 9:33 PM 5/9/2021
 */
@RestController
public class SalesInvoiceController {

    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;

    @GetMapping("/salesinvoice")
    public ResponseEntity<?> getAllSales(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        try {
            List<SalesInvoice> sales = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);
            Page<SalesInvoice> salesPage = salesInvoiceRepository.findAll((paging));
            sales = salesPage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("salesinvoices", sales);
            response.put("currentPage", salesPage.getNumber());
            response.put("totalItems", salesPage.getTotalElements());
            response.put("totalPages", salesPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/salesinvoice/{id}")
    public ResponseEntity<?> getStaffById(@PathVariable("id") Long id) {
        try {
            Optional<SalesInvoice> sales = salesInvoiceRepository.findById(id);
            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/salesinvoice")
    public ResponseEntity<?> createSales(@RequestBody SalesInvoice salesInvoice) {
        try {
            if (GenericValidator.isBlankOrNull(salesInvoice.getNameSalesStaff())) {
                return new ResponseEntity<>("Please enter the full information", HttpStatus.NO_CONTENT);
            }
            salesInvoice.setDate(new Date(System.currentTimeMillis()));
            salesInvoiceRepository.save(salesInvoice);
            return new ResponseEntity<>(salesInvoice, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/salesInvoices/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") Long id, @RequestBody SalesInvoice salesInvoice) {
        Optional<SalesInvoice> check = salesInvoiceRepository.findById(id);
        if (!check.isPresent()) {
            return new ResponseEntity<>("SalesInvoice not found", HttpStatus.NOT_FOUND);
        }
        try {
            salesInvoice.setId(id);
            salesInvoiceRepository.save(salesInvoice);
            return new ResponseEntity<>(check, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/salesinvoices/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable("id") Long id) {
        try {
            salesInvoiceRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
