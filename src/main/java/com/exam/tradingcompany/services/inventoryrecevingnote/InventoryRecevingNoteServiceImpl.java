package com.exam.tradingcompany.services.inventoryrecevingnote;

import com.exam.tradingcompany.entities.InventoryReceivingNote;
import com.exam.tradingcompany.repository.InventoryReceivingNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InventoryRecevingNoteServiceImpl implements InventoryRecevingNoteService {
    @Autowired
    private InventoryReceivingNoteRepository inventoryReceivingNoteRepository;


    @Override
    public Page<InventoryReceivingNote> findAllInventoryRecevingNoteByPeriod(Date start, Date end, Pageable pageable) {
        return inventoryReceivingNoteRepository.findAllByPeriod(start,end,pageable);
    }

    @Override
    public Double getCountProductByPeriod(Long id, Date start, Date end) {
        return inventoryReceivingNoteRepository.findAmountProductByPeriod(start,end,id);
    }
}
