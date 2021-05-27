package com.exam.tradingcompany.services.product;

import com.exam.tradingcompany.entities.Product;
import com.exam.tradingcompany.entities.dto.ProductWarehouse;

import java.util.Date;
import java.util.List;

public interface ProductService {

    //Câu hỏi cuối bài, lấy thông tin xuất nhập kho theo khoảng thời gian cho từng sp
    List<ProductWarehouse> getInfoProductWareHouse(Date start, Date end);
}
