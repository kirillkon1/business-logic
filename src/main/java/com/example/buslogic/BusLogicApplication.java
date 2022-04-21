package com.example.buslogic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BusLogicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusLogicApplication.class, args);
    }

}
