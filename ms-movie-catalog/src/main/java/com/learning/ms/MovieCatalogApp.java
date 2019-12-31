package com.learning.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix // OR @EnableCircuitBreaker
public class MovieCatalogApp {
    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogApp.class, args);
    }
}
