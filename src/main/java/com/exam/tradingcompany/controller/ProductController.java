package com.exam.tradingcompany.controller;

import com.exam.tradingcompany.entities.dto.ProductWarehouse;
import com.exam.tradingcompany.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //Lấy thông tin sản phẩm trong kho theo một khoảng thời gian
    //Dữ liệu truyền vào ở dạng params: 31/12/2022
    @GetMapping("/info-warehouse")
    public ResponseEntity<List<ProductWarehouse>> getInfoProductWarehouseByPeriod(@RequestParam String start,
                                                                                  @RequestParam String end) throws ParseException {
        try {
            Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(start);
            Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(end);

            return new ResponseEntity<>(productService.getInfoProductWareHouse(startDate,endDate),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
