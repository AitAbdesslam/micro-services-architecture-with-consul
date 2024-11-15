package com.ms.secservice;

import com.ms.secservice.sec.config.RsaKeysConfig;
import com.ms.secservice.sec.entities.AppRole;
import com.ms.secservice.sec.entities.AppUser;
import com.ms.secservice.sec.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeysConfig.class)
//protoger le ressource
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityServiceApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(AccountService accountService){
        return args -> {
            accountService.addNewRole(new AppRole(null,"USER"));
            accountService.addNewRole(new AppRole(null,"ADMIN"));
            accountService.addNewRole(new AppRole(null,"CUSTOMER_MANAGER"));
            accountService.addNewRole(new AppRole(null,"PRODUCT_MANAGER"));
            accountService.addNewRole(new AppRole(null,"BILLS_MANAGER"));

            accountService.addNewUser(new AppUser(null,"user1",passwordEncoder().encode("1234"),new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"admin",passwordEncoder().encode("1234"),new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"user2",passwordEncoder().encode("1234"),new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"user3",passwordEncoder().encode("1234"),new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"user4",passwordEncoder().encode("1234"),new ArrayList<>()));

            accountService.addRoleToUser("user1","USER");
            accountService.addRoleToUser("admin","USER");
            accountService.addRoleToUser("admin","ADMIN");
            accountService.addRoleToUser("user2","USER");
            accountService.addRoleToUser("user2","CUSTOMER_MANAGER");
            accountService.addRoleToUser("user3","USER");
            accountService.addRoleToUser("user3","PRODUCT_MANAGER");
            accountService.addRoleToUser("user4","USER");
            accountService.addRoleToUser("user4","BILLS_MANAGER");
        };
    }

}
