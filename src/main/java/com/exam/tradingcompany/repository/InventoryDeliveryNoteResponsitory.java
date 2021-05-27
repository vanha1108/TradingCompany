package com.exam.tradingcompany.repository;

import com.exam.tradingcompany.entities.InventoryDeliveryNote;
import com.exam.tradingcompany.entities.InventoryReceivingNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface InventoryDeliveryNoteResponsitory extends JpaRepository<InventoryDeliveryNote,Long> {
    //Lấy thông tin phiếu xuất kho trong 1 khảng thời gian
    @Query("select deleverynote from  InventoryDeliveryNote  deleverynote where deleverynote.date >= ?1 and deleverynote.date <= ?2")
    public Page<InventoryDeliveryNote> findAllByPeriod(Date dateStart, Date dateEnd, Pageable pageable);

    //Lấy thông tin số lượng sản phẩm được xuất trong 1 khoảng thời gian
    @Query("select sum(deleverynote.amount) from  InventoryDeliveryNote  deleverynote where deleverynote.date >= ?1 and deleverynote.date <= ?2 and deleverynote.product.id = ?3")
    Double findAmountProductByPeriod(Date dateStart, Date dateEnd,Long id);
}
