package com.exam.tradingcompany.services.inventorydeliverynote;

import com.exam.tradingcompany.entities.InventoryDeliveryNote;
import com.exam.tradingcompany.entities.InventoryReceivingNote;
import com.exam.tradingcompany.repository.InventoryDeliveryNoteResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InventoryDeliveryNoteServiceImpl implements InventoryDeliveryNoteService {
    @Autowired
    private InventoryDeliveryNoteResponsitory inventoryDeliveryNoteResponsitory;

    @Override
    public Page<InventoryDeliveryNote> findAllInventoryDeliveryNoteByPeriod(Date start, Date end, Pageable pageable) {
        return inventoryDeliveryNoteResponsitory.findAllByPeriod(start,end,pageable);
    }

    @Override
    public Double getCountProductByPeriod(Long id, Date start, Date end) {
        return inventoryDeliveryNoteResponsitory.findAmountProductByPeriod(start,end,id);
    }

}
