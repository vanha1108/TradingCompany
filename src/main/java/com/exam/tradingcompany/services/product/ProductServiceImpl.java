package com.exam.tradingcompany.services.product;

import com.exam.tradingcompany.entities.Product;
import com.exam.tradingcompany.entities.dto.ProductWarehouse;
import com.exam.tradingcompany.repository.ProductRepository;
import com.exam.tradingcompany.services.inventorydeliverynote.InventoryDeliveryNoteService;
import com.exam.tradingcompany.services.inventoryrecevingnote.InventoryRecevingNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRecevingNoteService inventoryRecevingNoteService;

    @Autowired
    private InventoryDeliveryNoteService inventoryDeliveryNoteService;

    @Override
    public List<ProductWarehouse> getInfoProductWareHouse(Date start, Date end) {
        List<Product> products = productRepository.findAll();

        if(products != null){
            List<ProductWarehouse> returnDto = new ArrayList<ProductWarehouse>();

            for (Product product: products) {
                ProductWarehouse productWarehouse = new ProductWarehouse();
                productWarehouse.setName(product.getName());

                productWarehouse.setReceived(inventoryRecevingNoteService.getCountProductByPeriod(product.getId(),start,end));
                productWarehouse.setDelivery(inventoryDeliveryNoteService.getCountProductByPeriod(product.getId(),start,end));

                Double balance = productWarehouse.getReceived() - productWarehouse.getDelivery();
                productWarehouse.setBalance(balance);

                returnDto.add(productWarehouse);


            }
            return  returnDto;
        }
        return null;
    }
}
