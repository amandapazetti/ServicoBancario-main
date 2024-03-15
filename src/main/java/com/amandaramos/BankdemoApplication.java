package com.amandaramos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaAuditing
@EnableWebMvc
@SpringBootApplication
public class BankdemoApplication {

    public static void main(String[] args){
        SpringApplication.run(BankdemoApplication.class, args);
    }
}