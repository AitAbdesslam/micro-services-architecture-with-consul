package com.ms.customer_service.web;

import com.ms.customer_service.dto.CustomerDTO;
import com.ms.customer_service.exceptions.CustomerNotFoundException;
import com.ms.customer_service.services.CustomerServiceImpl;
import com.ms.customer_service.services.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerRestController {
    private CustomerServiceImpl customerService;
    private FileStorageService fileStorageService;

    @GetMapping("/all")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO)
    {
        return customerService.saveCustomer(customerDTO);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(
            @RequestParam("file") MultipartFile file,
            @RequestBody CustomerDTO customerDTO
    ) {
        try {
            String fileName = fileStorageService.save(file);

            /*CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setName(name);
            customerDTO.setAddress(address);
            customerDTO.setEmail(email);
            customerDTO.setPhone(phone);*/
            customerDTO.setFile_contrat_open_compte(fileName);

            // Here you would typically save the customer to a database
            // For this example, we're just returning the customer object
            customerService.saveCustomer(customerDTO);
            return ResponseEntity.ok(customerDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        customerService.deleteCustomerById(id);
    }
}
