package com.exam.tradingcompany.repository;

import com.exam.tradingcompany.entities.InventoryReceivingNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface InventoryReceivingNoteRepository extends JpaRepository<InventoryReceivingNote,Long> {
    //Lấy thông tin phiếu nhập kho theo 1 khoảng thời gian
    @Query("select recevingnote from  InventoryReceivingNote  recevingnote where recevingnote.date >= ?1 and recevingnote.date <= ?2")
    public Page<InventoryReceivingNote> findAllByPeriod(Date dateStart, Date dateEnd, Pageable pageable);

    //Lấy số lượng sản phẩm đã nhập kho trong 1 khoảng thời gian
    @Query("select sum(recevingnote.amount) from  InventoryReceivingNote  recevingnote where recevingnote.date >= ?1 and recevingnote.date <= ?2 and recevingnote.product.id = ?3")
    Double findAmountProductByPeriod(Date dateStart, Date dateEnd,Long id);
}
