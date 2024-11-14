package com.ms.customer_service.services;

import com.ms.customer_service.dto.CustomerDTO;
import com.ms.customer_service.entities.Customer;
import com.ms.customer_service.exceptions.CustomerNotFoundException;
import com.ms.customer_service.mappers.CustomerMapper;
import com.ms.customer_service.repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Override
    public CustomerDTO getCustomerById(Long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null){
            throw new CustomerNotFoundException("Customer with id " + id + " not found.");
        }
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) throws CustomerNotFoundException {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null){
            throw new CustomerNotFoundException("Customer with email " + email + " not found.");
        }
        if (!customer.getEmail().equals(email)) {
            throw new CustomerNotFoundException("Client with email " + email + " not found.");
        }
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
        return customerDTOS;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.fromCustomer(savedCustomer);
    }

    @Override
    public void deleteCustomerById(Long id) throws CustomerNotFoundException {
        if(!customerRepository.existsById(id)){
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null){
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        customerRepository.delete(customer);
    }
}
