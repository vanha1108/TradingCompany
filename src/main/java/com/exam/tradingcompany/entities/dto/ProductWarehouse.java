package com.exam.tradingcompany.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Dùng DTO này tạo ra một biểu mẫu mới, custom cách trả về của thông tin đang yêu cầu
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWarehouse {
    private String name;

    private Double received;

    private Double delivery;

    private Double balance;

}
