package com.generation.crudfarmacia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.generation.crudfarmacia") 
public class CrudfarmaciaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudfarmaciaApplication.class, args);
    }
}
