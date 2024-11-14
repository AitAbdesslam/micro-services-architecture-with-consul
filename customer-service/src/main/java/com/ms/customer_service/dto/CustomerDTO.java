package com.ms.customer_service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDTO {
    private Long Id;
    private String name;
    private String email;
    private String address;
    private String ice;
    private String rc;
    private String num_if;
    private double capital;
    private String patente;
    private String rib;
    private String bank;
    private int business_id;
    private String tax_professionel;
    private String phone;
    private String file_contrat_open_compte;
}
