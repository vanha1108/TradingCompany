package com.exam.tradingcompany.controller;

import com.exam.tradingcompany.entities.Customer;
import com.exam.tradingcompany.entities.Order;
import com.exam.tradingcompany.entities.SalesInvoice;
import com.exam.tradingcompany.entities.Staff;
import com.exam.tradingcompany.repository.SalesInvoiceRepository;
import com.exam.tradingcompany.services.customer.CustomerService;
import com.exam.tradingcompany.services.salesinvoice.SalesInvoiceService;
import com.exam.tradingcompany.services.staff.StaffService;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Nguyễn Văn Hà
 * 9:33 PM 5/9/2021
 */
@RestController
public class SalesInvoiceController {

    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;

    @Autowired
    private SalesInvoiceService salesInvoiceService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StaffService staffService;

    //Lấy tất cả hóa đơn bán hàng
    @GetMapping("/salesinvoice")
    public ResponseEntity<?> getAllSales(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        try {
            List<SalesInvoice> sales = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);
            Page<SalesInvoice> salesPage = salesInvoiceRepository.findAll((paging));
            sales = salesPage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("salesinvoices", sales);
            response.put("currentPage", salesPage.getNumber());
            response.put("totalItems", salesPage.getTotalElements());
            response.put("totalPages", salesPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Lấy thông tin tổng doanh thu dựa trên hóa đơn trong 1 khoảng thời gian theo khách hàng
    //Params: start là ngày bắt đâu, end là ngày kết thúc, id_customer là id của khách hàng
    //Dữ liệu mẫu: 31/12/2022 . Dùng cho tất cả các route còn lại với định dạng này
    @GetMapping("/salesinvoice/revenue/filter-customer")
    public  ResponseEntity<?> getRevenueByCustomerPeriod(@RequestParam() String start,
                                                         @RequestParam() String end,
                                                         @RequestParam() String id_customer) throws ParseException {
        try {
            Optional<Customer> customer = customerService.findById(Long.parseLong(id_customer));
            if(customer == null){
                return new ResponseEntity<>("id khách hàng không đúng",HttpStatus.NOT_FOUND);
            }
            Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(start);
            Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(end);
            Double total = salesInvoiceRepository.getTotalRevenueByCustomerPeriod(Long.parseLong(id_customer),startDate,endDate);

            Map<String, Object> response = new HashMap<>();
            response.put("total_Revenue",total);
            response.put("start_date",startDate);
            response.put("end_date",endDate);
            response.put("customer",customer);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Lấy thông tin tổng doanh thu dựa trên hóa đơn trong 1 khoảng thời gian theo nhân viên lên hóa đơn
    //Params: start là ngày bắt đâu, end là ngày kết thúc, id_staff là id của khách hàng
    //Dữ liệu mẫu: 31/12/2022 . Dùng cho tất cả các route còn lại với định dạng này
    @GetMapping("/salesinvoice/revenue/filter-staff")
    public  ResponseEntity<?> getRevenueByStaffPeriod(@RequestParam() String start,
                                                         @RequestParam() String end,
                                                         @RequestParam() String id_staff) throws ParseException {
        try {
            Optional<Staff> staff = staffService.findById(Long.parseLong(id_staff));
            if(staff == null){
                return new ResponseEntity<>("id nhân viên không đúng",HttpStatus.NOT_FOUND);
            }
            Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(start);
            Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(end);
            Double total = salesInvoiceRepository.getTotalRevenueByStaffPeriod(Long.parseLong(id_staff),startDate,endDate);

            Map<String, Object> response = new HashMap<>();
            response.put("total_Revenue",total);
            response.put("start_date",startDate);
            response.put("end_date",endDate);
            response.put("staff",staff);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Lấy tất cả thông tin hóa đơn trong 1 khoảng thời gian
    //Params: start là ngày bắt đâu, end là ngày kết thúc, page and size is optional
    //Dữ liệu mẫu: 31/12/2022 . Dùng cho tất cả các route còn lại với định dạng này
    @GetMapping("/salesinvoice/period")
    public ResponseEntity<?> getAllSalesByPeriod(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "3") int size,
                                                 @RequestParam() String start,
                                                 @RequestParam() String end) {
        try {
            Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(start);
            Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(end);
            List<SalesInvoice> sales = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);
            Page<SalesInvoice> salesPage = salesInvoiceService.findAllSalesInvoceByPeriod(startDate,endDate,paging);
            sales = salesPage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("salesinvoices", sales);
            response.put("currentPage", salesPage.getNumber());
            response.put("totalItems", salesPage.getTotalElements());
            response.put("totalPages", salesPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Tương tự như trên, nhưng lọc theo 1 khách hàng nào đó
    @GetMapping("/salesinvoice/period/filter-by-customer")
    public ResponseEntity<?> getAllSalesByCustomerPeriod(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "3") int size,
                                                 @RequestParam() String start,
                                                 @RequestParam() String end,
                                                         @RequestParam String id_customer) {
        try {
            Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(start);
            Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(end);
            List<SalesInvoice> sales = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);
            Page<SalesInvoice> salesPage = salesInvoiceService.findAllSalesInvoceByCustomerPeriod(id_customer,startDate,endDate,paging);
            sales = salesPage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("salesinvoices", sales);
            response.put("currentPage", salesPage.getNumber());
            response.put("totalItems", salesPage.getTotalElements());
            response.put("totalPages", salesPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Tương tự, lọc theo một nhân viên bán hàng
    @GetMapping("/salesinvoice/period/filter-by-salestaff")
    public ResponseEntity<?> getAllSalesByStaffPeriod(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "3") int size,
                                                         @RequestParam() String start,
                                                         @RequestParam() String end,
                                                         @RequestParam String id_staff) {
        try {
            Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(start);
            Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(end);
            List<SalesInvoice> sales = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);
            Page<SalesInvoice> salesPage = salesInvoiceService.findAllSalesInvoceByStaffPeriod(id_staff,startDate,endDate,paging);
            sales = salesPage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("salesinvoices", sales);
            response.put("currentPage", salesPage.getNumber());
            response.put("totalItems", salesPage.getTotalElements());
            response.put("totalPages", salesPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Lấy thông tin hóa đơn theo id
    @GetMapping("/salesinvoice/{id}")
    public ResponseEntity<?> getStaffById(@PathVariable("id") Long id) {
        try {
            Optional<SalesInvoice> sales = salesInvoiceRepository.findById(id);
            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Thêm mới một hóa đơn
    @PostMapping("/salesinvoice")
    public ResponseEntity<?> createSales(@RequestBody SalesInvoice salesInvoice) {
        try {
            if (GenericValidator.isBlankOrNull(salesInvoice.getNameSalesStaff())) {
                return new ResponseEntity<>("Please enter the full information", HttpStatus.NO_CONTENT);
            }
            salesInvoice.setDate(new Date(System.currentTimeMillis()));
            salesInvoiceRepository.save(salesInvoice);
            return new ResponseEntity<>(salesInvoice, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Cập nhật thông tin hóa đơn theo id
    @PatchMapping("/salesInvoices/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") Long id, @RequestBody SalesInvoice salesInvoice) {
        Optional<SalesInvoice> check = salesInvoiceRepository.findById(id);
        if (!check.isPresent()) {
            return new ResponseEntity<>("SalesInvoice not found", HttpStatus.NOT_FOUND);
        }
        try {
            salesInvoice.setId(id);
            salesInvoiceRepository.save(salesInvoice);
            return new ResponseEntity<>(check, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Xóa đi một hóa đơn
    @DeleteMapping("/salesinvoices/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable("id") Long id) {
        try {
            salesInvoiceRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
