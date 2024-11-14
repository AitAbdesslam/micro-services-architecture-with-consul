package com.ms.customer_service;

import com.ms.customer_service.entities.Customer;
import com.ms.customer_service.repo.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	@Profile("!test")
	CommandLineRunner start(CustomerRepository customerRepository)
	{
		return args -> {
			customerRepository.saveAll(List.of(
					Customer.builder()
							.name("Mohamed")
							.email("med@gmail.com")
							.address("Hay nahda Rabat")
							.ice("3457987572922928")
							.rc("134903")
							.tax_professionel("26301176")
							.bank("Crédit Immobilier et Hôtelier (CIH)")
							.rib("230 432 2675595221094400 12")
							.build(),
					Customer.builder().name("Hassan").email("hasan@gmail.com").build(),
					Customer.builder().name("IMane").email("imane@gmail.com").build()
			));
			customerRepository.findAll().forEach(System.out::println);

		};
	}

}
