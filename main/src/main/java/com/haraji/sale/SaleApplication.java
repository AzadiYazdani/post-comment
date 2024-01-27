package com.haraji.sale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.haraji.sale.database.repository")
@EntityScan("com.haraji.sale.database.entity")
public class SaleApplication {


    public static void main(String[] args) {
        SpringApplication.run(SaleApplication.class, args);
        System.out.println("Application started");
    }

}
