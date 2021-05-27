package com.exam.tradingcompany.services.inventorydeliverynote;

import com.exam.tradingcompany.entities.InventoryDeliveryNote;
import com.exam.tradingcompany.entities.InventoryReceivingNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface InventoryDeliveryNoteService {
    //Tìm kiếm tất cả thông tin xuất kho trong 1 khoảng thời gian
    Page<InventoryDeliveryNote> findAllInventoryDeliveryNoteByPeriod(Date start, Date end, Pageable pageable);

    //Đếm số lượng của một sản phẩm được xuất kho trong 1 khoảng thời gian
    Double getCountProductByPeriod(Long id, Date start, Date end);

}
