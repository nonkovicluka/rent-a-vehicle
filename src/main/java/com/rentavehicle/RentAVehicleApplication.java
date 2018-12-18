package com.rentavehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RentAVehicleApplication {

    @Autowired
    private TestData td;

    public static void main(String[] args) {
        SpringApplication.run(RentAVehicleApplication.class, args);
    }
}
