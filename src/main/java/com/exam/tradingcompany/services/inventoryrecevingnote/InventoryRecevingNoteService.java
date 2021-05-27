package com.exam.tradingcompany.services.inventoryrecevingnote;

import com.exam.tradingcompany.entities.InventoryReceivingNote;
import com.exam.tradingcompany.entities.SalesInvoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface InventoryRecevingNoteService {
    //Tìm kiếm tất cả thông tin nhập kho trong 1 khoảng thời gian
    Page<InventoryReceivingNote> findAllInventoryRecevingNoteByPeriod(Date start, Date end, Pageable pageable);

    //Đếm số lượng của một sản phẩm được nhập kho trong 1 khoảng thời gian
    Double getCountProductByPeriod(Long id, Date start, Date end);
}
