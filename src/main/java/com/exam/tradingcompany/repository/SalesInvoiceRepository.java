package com.exam.tradingcompany.repository;

import com.exam.tradingcompany.entities.SalesInvoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Nguyễn Văn Hà
 * 9:32 PM 5/9/2021
 */
@Repository
public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Long> {
    //Tìm tất cả các hóa đơn theo 1 hoảng thời gian
    @Query("select salein from  SalesInvoice  salein where salein.date >= ?1 and salein.date <= ?2")
    public Page<SalesInvoice> findAllSaleInvoiceByPeriod(Date dateStart, Date dateEnd, Pageable pageable);

    //Tìm tất cả hóa đơn theo 1 khoảng thời gian bởi 1 người dùng nào đó
    @Query("select salein from  SalesInvoice  salein where salein.date >= ?2 and salein.date <= ?3 and  salein.customer.id =?1")
    public Page<SalesInvoice> findAllSaleInvoiceByCustomerPeriod(Long id, Date dateStart, Date dateEnd, Pageable pageable);

    //Tương tự nhưng bởi nhân viên
    @Query("select salein from  SalesInvoice  salein where salein.date >= ?2 and salein.date <= ?3 and  salein.staff.id =?1")
    public Page<SalesInvoice> findAllSaleInvoiceByStaffPeriod(Long id, Date dateStart, Date dateEnd, Pageable pageable);

    //Lấy tổng doanh thu của 1 khách hàng trong 1 khoảng thời gian
    @Query("select SUM(salein.total) from  SalesInvoice  salein where salein.date >= ?2 and salein.date <= ?3 and  salein.customer.id =?1")
    public Double getTotalRevenueByCustomerPeriod(Long id, Date dateStart, Date dateEnd);

    //Tương tự nhưng bởi nhân viên
    @Query("select SUM(salein.total) from  SalesInvoice  salein where salein.date >= ?2 and salein.date <= ?3 and  salein.staff.id =?1")
    public Double getTotalRevenueByStaffPeriod(Long id, Date dateStart, Date dateEnd);

}
