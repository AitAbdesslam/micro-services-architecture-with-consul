package com.ms.customer_service.services;

import com.ms.customer_service.dto.CustomerDTO;
import com.ms.customer_service.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerDTO getCustomerById(Long id) throws CustomerNotFoundException;
    CustomerDTO getCustomerByEmail(String email) throws CustomerNotFoundException;
    List<CustomerDTO> getAllCustomers();
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    void deleteCustomerById(Long id) throws CustomerNotFoundException;
}
