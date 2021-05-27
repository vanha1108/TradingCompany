package com.exam.tradingcompany.services.salesinvoice;

import com.exam.tradingcompany.entities.SalesInvoice;
import com.exam.tradingcompany.repository.SalesInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SalesInvoiceServiceImpl implements SalesInvoiceService {

    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;

    @Override
    public Page<SalesInvoice> findAllSalesInvoceByPeriod(Date start, Date end, Pageable pageable) {
        return salesInvoiceRepository.findAllSaleInvoiceByPeriod(start, end, pageable);
    }

    @Override
    public Page<SalesInvoice> findAllSalesInvoceByCustomerPeriod(String idCustomer, Date start, Date end, Pageable pageable) throws Exception {
        try {
            return salesInvoiceRepository.findAllSaleInvoiceByCustomerPeriod(Long.parseLong(idCustomer), start, end, pageable);
        } catch (Exception e) {
            throw new Exception("Id is not correct");
        }
    }

    @Override
    public Page<SalesInvoice> findAllSalesInvoceByStaffPeriod(String id_staff, Date startDate, Date endDate, Pageable paging) throws Exception {
        try {
            return salesInvoiceRepository.findAllSaleInvoiceByCustomerPeriod(Long.parseLong(id_staff), startDate, endDate, paging);
        } catch (Exception e) {
            throw new Exception("Id is not correct");
        }
    }

    @Override
    public Double getTotalRevenueByCustomerPeriod(String id_customer, Date startDate, Date endDate) throws Exception {
        return salesInvoiceRepository.getTotalRevenueByCustomerPeriod(Long.parseLong(id_customer), startDate, endDate);
    }

    @Override
    public Double getTotalRevenueByStaffPeriod(String id_staff, Date startDate, Date endDate) throws Exception {
        return salesInvoiceRepository.getTotalRevenueByStaffPeriod(Long.parseLong(id_staff), startDate, endDate);
    }
}
