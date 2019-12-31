package com.learning.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServiceApp.class, args);
    }

}
