package com.exam.tradingcompany.controller;

import com.exam.tradingcompany.entities.InventoryDeliveryNote;
import com.exam.tradingcompany.entities.InventoryReceivingNote;
import com.exam.tradingcompany.services.inventorydeliverynote.InventoryDeliveryNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/inventorydeliverynote")
public class InventoryDeliveryNoteController {
    @Autowired
    private InventoryDeliveryNoteService inventoryDeliveryNoteService;

    //Lấy thông tin phiếu giao hàng theo khoảng thời gian
    //truyền vào params: page(option),size(option),start(required),end(required)
    @GetMapping("/period")
    public ResponseEntity<?> findAllByPeriod(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "3") int size,
                                             @RequestParam() String start,
                                             @RequestParam() String end){
        try {
            Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(start);
            Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(end);
            List<InventoryDeliveryNote> deleveryNotes = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);
            Page<InventoryDeliveryNote> recevingNotePage = inventoryDeliveryNoteService.findAllInventoryDeliveryNoteByPeriod(startDate,endDate,paging);
            deleveryNotes = recevingNotePage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("recevingnotes", deleveryNotes);
            response.put("currentPage", recevingNotePage.getNumber());
            response.put("totalItems", recevingNotePage.getTotalElements());
            response.put("totalPages", recevingNotePage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
