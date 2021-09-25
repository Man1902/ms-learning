package com.learning.ms.movierating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix // OR @EnableCircuitBreaker
public class MovieRatingApp {
	public static void main(String[] args) {
		SpringApplication.run(MovieRatingApp.class, args);

	}

}
