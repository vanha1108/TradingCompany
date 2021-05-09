package com.exam.tradingcompany.controller;

import com.exam.tradingcompany.entities.Order;
import com.exam.tradingcompany.repository.OrderRepository;
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
 * 9:24 PM 5/9/2021
 */
@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/order")
    public ResponseEntity<?> getAllOrder(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        try {
            List<Order> orders = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);
            Page<Order> orderPage = orderRepository.findAll((paging));
            orders = orderPage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("orders", orders);
            response.put("currentPage", orderPage.getNumber());
            response.put("totalItems", orderPage.getTotalElements());
            response.put("totalPages", orderPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getSalesById(@PathVariable("id") Long id) {
        try {
            Optional<Order> order = orderRepository.findById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        try {
            order.setDate(new Date(System.currentTimeMillis()));
            orderRepository.save(order);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/order/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") Long id, @RequestBody Order order) {
        Optional<Order> check = orderRepository.findById(id);
        if (!check.isPresent()) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
        try {
            order.setId(id);
            orderRepository.save(order);
            return new ResponseEntity<>(check, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable("id") Long id) {
        try {
            orderRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
