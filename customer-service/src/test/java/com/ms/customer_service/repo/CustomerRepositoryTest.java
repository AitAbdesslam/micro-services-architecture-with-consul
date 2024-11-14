package com.ms.customer_service.repo;

import com.ms.customer_service.entities.Customer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

//import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp()
    {
        System.out.println("-----------------------Start Insertion for CUstomers in Profile Test --------------");
        customerRepository.save(Customer.builder()
                .name("Mohamed Ali")
                .email("mohamed.ali@gmail.com")
                .address("Hay nahda Rabat")
                .ice("3457987572922928")
                .rc("134903")
                .tax_professionel("26301176")
                .bank("Crédit Immobilier et Hôtelier (CIH)")
                .rib("230 432 2675595221094400 12")
                .build());


        customerRepository.save(Customer.builder()
                .name("Ahmed Yassine")
                .email("ahmed.yassine@gmail.com")
                .address("Hay nahda Rabat")
                .ice("3457987572922928")
                .rc("134903")
                .tax_professionel("26301176")
                .bank("Crédit Immobilier et Hôtelier (CIH)")
                .rib("230 432 2675595221094400 12")
                .build());

        System.out.println("-----------------------Start Insertion for CUstomers in Profile Test --------------");
    }

    @Test
    void shouldFindCustomersByFirstName(){
        List<Customer> expected = List.of(
                Customer.builder()
                        .name("Mohamed Ali")
                        .email("mohamed.ali@gmail.com")
                        .address("Hay nahda Rabat")
                        .ice("3457987572922928")
                        .rc("134903")
                        .tax_professionel("26301176")
                        .bank("Crédit Immobilier et Hôtelier (CIH)")
                        .rib("230 432 2675595221094400 12")
                        .build(),
                Customer.builder()
                        .name("Ahmed Yassine")
                        .email("ahmed.yassine@gmail.com")
                        .address("Hay nahda Rabat")
                        .ice("3457987572922928")
                        .rc("134903")
                        .tax_professionel("26301176")
                        .bank("Crédit Immobilier et Hôtelier (CIH)")
                        .rib("230 432 2675595221094400 12")
                        .build()
        );
        List<Customer> result = customerRepository.findByNameContainingIgnoreCase("m");
        //assertEquals(result.size(),2);
        assertThat(expected.size()).isEqualTo(2);
        assertThat(result.size()).isEqualTo(2);
        //assertThat(expected).usingRecursiveComparison().ignoringFields("id").isEqualTo(result);
    }

    @Test
    void shouldFindCustomersByEmail(){
        String givenEmail = "mohamed.ali@gmail.com";
        //Customer expected = Customer.builder().name("Mohamed Ali").email("ahmed.yassine@gmail.com").build();
        Customer result = customerRepository.findByEmail(givenEmail);
        //assertThat(result.getEmail()).isNotNull();
        assertThat(result.getName()).isEqualTo("Mohamed Ali");
        //assertThat(expected).usingRecursiveComparison().ignoringFields("id").isEqualTo(result);
    }

    void shouldNotFindCustomersByEmail(){
        String givenEmail="ezza@gmail.com";
        Customer result = customerRepository.findByEmail(givenEmail);
        assertThat(result).isNull();
    }

}