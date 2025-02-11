package com.example.elevatorsapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ElevatorsApplication {

    public static void main(String[] args) {
        System.out.println("Elevator Application starts to run.");
        SpringApplication.run(ElevatorsApplication.class, args);
    }

}
