package com.ms.customer_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotEmpty
    @Size(min = 10)
    private String name;
    @NotEmpty
    private String email;
    private String address;
    private int group_id = 1;
    private int price_group_id = 1;
    private String ice;
    private String rc;
    private String num_if;
    private double capital;
    private String patente;
    private String rib;
    private String bank;
    private int business_id;
    private String tax_professionel;
    private Date date_mise_marche = new Date();
    private Date created_at = new Date();
    private String phone;
    private String file_contrat_open_compte;
}
