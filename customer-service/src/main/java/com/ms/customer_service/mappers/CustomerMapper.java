package com.ms.customer_service.mappers;

import com.ms.customer_service.dto.CustomerDTO;
import com.ms.customer_service.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerMapper {


    public CustomerDTO fromCustomer(Customer customer)
    {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO)
    {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }

    ModelMapper modelMapper = new ModelMapper();

    public List<Customer> fromListCustomersDTO(List<CustomerDTO> customersDTO)
    {
        return customersDTO.stream().map(cDTO -> modelMapper.map(cDTO, Customer.class)).collect(Collectors.toList());
    }

    public List<CustomerDTO> fromListCustomers(List<Customer> customers)
    {
        return customers.stream().map(cus -> modelMapper.map(cus, CustomerDTO.class)).collect(Collectors.toList());
    }

}
