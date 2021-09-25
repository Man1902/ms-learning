package com.learning.ms.moviecatalog.service;

import com.learning.ms.moviecatalog.model.Rating;
import com.learning.ms.moviecatalog.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

@Service
public class UserRatingServiceImpl implements UserRatingService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    @HystrixCommand(
            fallbackMethod = "getUserRatingFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            },
            threadPoolKey = "userRatingPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
            }
    )
    public UserRating getUserRating(Long userId) {
        // Approach 1 : Using RestTemplate (deprecated in latest version)
        return restTemplate.getForObject("http://ms-movie-rating/ratings/users/" + userId, UserRating.class);

        // Approach 2 : Using WebClient (Reactive way)
       /* return webClientBuilder.build()
                .get()
                .uri("http://ms-movie-rating/ratings/users/" + userId)
                .retrieve()
                .bodyToMono(UserRating.class)
                .block();*/
    }

    public UserRating getUserRatingFallback(Long userId) {
        UserRating userRating = new UserRating();
        userRating.setRatings(Arrays.asList(new Rating(userId, 0L, 0)));
        return userRating;
    }
}
