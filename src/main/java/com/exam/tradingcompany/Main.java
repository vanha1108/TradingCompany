package com.exam.tradingcompany;

import com.exam.tradingcompany.entities.*;
import com.exam.tradingcompany.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

/**
 * Nguyễn Văn Hà
 * 1:50 AM 5/9/2021
 */
@SpringBootApplication
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(com.exam.tradingcompany.Main.class, args);
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryReceivingNoteRepository inventoryReceivingNoteRepository;

    @Autowired InventoryDeliveryNoteResponsitory inventoryDeliveryNoteResponsitory;

    @Override
    public void run(String... args) throws Exception {
        iniData();
    }

    //Hàm khởi tạo data, có thể nhân bản lên để có nhiều data
    //Chú ý ràng buộc để sắp xếp thứ tự khi khởi tạo
    private void iniData(){
        if(customerRepository.count()==0){
            Customer customer =new Customer();
            customer.setName("CUSTOMER");
            customer.setAddress("CUSTOMER");
            customer.setEmail("autho@gmail.com");
            customer.setPhone("0123456789");
            customer.setContactPerson("private contact");
            customerRepository.save(customer);

            Staff staff = new Staff();
            staff.setAddress("ADDRESS");
            staff.setEmail("staff@gmail.com");
            staff.setName("STAFF1");
            staff.setPhone("6318638866");
            staffRepository.save(staff);

            Category category = new Category();
            category.setName("category");
            categoryRepository.save(category);

            Product product = new Product();
            product.setBrand("brand");
            product.setName("Product Name");
            product.setCompany("company1");
            product.setDescription("description");
            product.setModel("model");
            product.setSellingPrice(1000);
            product.setCategory(category);
            productRepository.save(product);

            InventoryReceivingNote inventoryReceivingNote = new InventoryReceivingNote();
            inventoryReceivingNote.setProduct(product);
            inventoryReceivingNote.setAmount(10);
            inventoryReceivingNote.setPrice(800);
            inventoryReceivingNote.setDate(new Date());
            inventoryReceivingNote.setStaff(staff);
            inventoryReceivingNoteRepository.save(inventoryReceivingNote);

            InventoryDeliveryNote inventoryDeliveryNote = new InventoryDeliveryNote();
            inventoryDeliveryNote.setProduct(product);
            inventoryDeliveryNote.setAmount(10);
            inventoryDeliveryNote.setPrice(800);
            inventoryDeliveryNote.setDate(new Date());
            inventoryDeliveryNote.setStaff(staff);
            inventoryDeliveryNoteResponsitory.save(inventoryDeliveryNote);

            SalesInvoice salesInvoice = new SalesInvoice();
            salesInvoice.setCustomer(customer);
            salesInvoice.setPrice(3000);
            salesInvoice.setQuantity(2);
            salesInvoice.setTotal(6000);
            salesInvoice.setDate(new Date());
            salesInvoice.setStaff(staff);
            salesInvoiceRepository.save(salesInvoice);

            SalesInvoice salesInvoice2 = new SalesInvoice();
            salesInvoice2.setCustomer(customer);
            salesInvoice2.setPrice(3000);
            salesInvoice2.setQuantity(4);
            salesInvoice2.setTotal(12000);
            salesInvoice2.setDate(new Date());
            salesInvoice2.setStaff(staff);
            salesInvoiceRepository.save(salesInvoice2);

        }

    }
}
