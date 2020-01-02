package com.learning.ms.controller;

import com.learning.ms.model.Rating;
import com.learning.ms.model.UserRating;
import com.learning.ms.service.RatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ratings")
public class RatingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> saveRating(@RequestBody Rating rating) throws Exception {
        Rating rating1 = ratingService.saveRating(rating);
        return new ResponseEntity<>(rating1, HttpStatus.CREATED);
    }

    @RequestMapping("/users/{userId}")
    @HystrixCommand(fallbackMethod = "getUserRatingsFailOver", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public ResponseEntity<UserRating> getUserRatings(@PathVariable Long userId) throws Exception {
        LOGGER.info("/ratings/users/{userId} API invocation -> Start");
        List<Rating> ratings = ratingService.getRatings(userId);
        UserRating userRating = new UserRating();
        userRating.setRatings(ratings);
        LOGGER.info("/ratings/users/{userId} API invocation -> End");
        return new ResponseEntity<>(userRating, HttpStatus.OK);
    }

    public ResponseEntity<UserRating> getUserRatingsFailOver(Long userId) throws Exception {
        return new ResponseEntity<>(new UserRating(), HttpStatus.OK);
    }
}
