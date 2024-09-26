package com.example.ordersyncscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrderSyncSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderSyncSchedulerApplication.class, args);
    }

}
