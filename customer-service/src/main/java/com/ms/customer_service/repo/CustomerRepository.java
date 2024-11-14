package com.ms.customer_service.repo;

import com.ms.customer_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource*/
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);

    boolean existsByEmail(String email);


    List<Customer> findByNameContainingIgnoreCase(String keyword);

}
