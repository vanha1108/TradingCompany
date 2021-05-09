package com.exam.tradingcompany.controller;

import com.exam.tradingcompany.entities.Customer;
import com.exam.tradingcompany.repository.CustomerRepository;
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
 * 3:01 AM 5/9/2021
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer")
    public ResponseEntity<?> getAllCustomer(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        try {
            List<Customer> customers = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);
            Page<Customer> customerPage = customerRepository.findAll((paging));
            customers = customerPage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("customers", customers);
            response.put("currentPage", customerPage.getNumber());
            response.put("totalItems", customerPage.getTotalElements());
            response.put("totalPages", customerPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id) {
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        if (GenericValidator.isBlankOrNull(customer.getName()) || GenericValidator.isBlankOrNull(customer.getAddress())
                || GenericValidator.isBlankOrNull(customer.getEmail()) || GenericValidator.isBlankOrNull(customer.getPhone())
                || GenericValidator.isBlankOrNull(customer.getContactPerson())) {
            return new ResponseEntity<>("Please enter the full information", HttpStatus.BAD_REQUEST);
        }
        try {
            customerRepository.save(customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        Optional<Customer> check = customerRepository.findById(id);
        if (!check.isPresent()) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
        if (GenericValidator.isBlankOrNull(customer.getName()) || GenericValidator.isBlankOrNull(customer.getAddress())
                || GenericValidator.isBlankOrNull(customer.getEmail()) || GenericValidator.isBlankOrNull(customer.getPhone())
                || GenericValidator.isBlankOrNull(customer.getContactPerson())) {
            return new ResponseEntity<>("Please enter the full information", HttpStatus.BAD_REQUEST);
        }
        try {
            customer.setId(id);
            customerRepository.save(customer);
            return new ResponseEntity<>(check, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        try {
            customerRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
