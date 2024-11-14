package com.ms.order_service.entities;

import com.ms.order_service.enums.OrderStatus;
import com.ms.order_service.model.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @NotNull(message = "Customer ID must not be null")
    private Long customerId;

    @Transient
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;

    public double getTotal(){
        double somme=0;
        for(ProductItem pi:productItems){
            somme+=pi.getAmount();
        }
        return somme;
    }

}
