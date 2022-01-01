package com.werfen.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class InventoryApp {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApp.class, args);
    }
}

