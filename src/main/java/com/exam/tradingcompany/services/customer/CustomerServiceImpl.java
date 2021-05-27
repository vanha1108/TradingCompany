package com.exam.tradingcompany.services.customer;

import com.exam.tradingcompany.entities.Customer;
import com.exam.tradingcompany.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer searchCustomer(String name, String address, String phone) {
        if(!name.equals("")){
           return customerRepository.findCustomerByName(name);
        }else if(!phone.equals("")){
            return customerRepository.findCustomerByPhone(phone);
        }else if(!address.equals("")){
            return customerRepository.findCustomerByAddress(address);
        }
        return null;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
    
}
