package com.exam.tradingcompany.services.salesinvoice;

import com.exam.tradingcompany.entities.SalesInvoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface SalesInvoiceService {
    //Tìm tất cả hóa đơn trong 1 khoảng thời gian
    Page<SalesInvoice> findAllSalesInvoceByPeriod(Date start, Date end, Pageable pageable);

    //Như trên nhưng bởi một đối tượng khách hàng
    Page<SalesInvoice> findAllSalesInvoceByCustomerPeriod(String idCustomer, Date start, Date end, Pageable pageable) throws Exception;

    //Bởi một nhân viên
    Page<SalesInvoice> findAllSalesInvoceByStaffPeriod(String id_staff, Date startDate, Date endDate, Pageable paging) throws Exception;

    //Tổng doanh thu bởi 1 khách hàng
    Double getTotalRevenueByCustomerPeriod(String id_customer, Date startDate, Date endDate) throws Exception;

    //Tổng doanh thu bởi 1 nhân viên
    Double getTotalRevenueByStaffPeriod(String id_staff, Date startDate, Date endDate) throws Exception;
}
