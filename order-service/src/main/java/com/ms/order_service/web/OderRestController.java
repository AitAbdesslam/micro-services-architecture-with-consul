package com.ms.order_service.web;

import com.ms.order_service.entities.Order;
import com.ms.order_service.model.Customer;
import com.ms.order_service.model.Product;
import com.ms.order_service.repository.OrderRepository;
import com.ms.order_service.repository.ProductItemRepository;
import com.ms.order_service.services.CustomerRestClientService;
import com.ms.order_service.services.InventoryRestClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OderRestController {

    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;


    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order=orderRepository.findById(id).get();
        Customer customer=customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(pi->{
            Product product=inventoryRestClientService.productById(pi.getProductId());
            pi.setProduct(product);
        });
        return order;
    }
}