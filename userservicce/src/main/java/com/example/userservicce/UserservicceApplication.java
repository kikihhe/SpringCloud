package com.example.userservicce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserservicceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserservicceApplication.class, args);
    }

}
