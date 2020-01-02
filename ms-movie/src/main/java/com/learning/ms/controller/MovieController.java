package com.learning.ms.controller;

import com.learning.ms.model.Movie;
import com.learning.ms.service.MovieService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @RequestMapping("/{movieId}")
    @HystrixCommand(fallbackMethod = "getMovieFailOver", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public ResponseEntity<Movie> getMovie(@PathVariable Long movieId) throws Exception {
        LOGGER.info("/movies/{movieId} API invocation -> Start");
        Movie movie = movieService.getMovie(movieId);
        LOGGER.info("/movies/{movieId} API invocation -> End");
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    public ResponseEntity<Movie> getMovieFailOver(Long movieId) throws Exception {
        return new ResponseEntity<>(new Movie(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) throws Exception {
        Movie movie1 = movieService.saveMovie(movie);
        return new ResponseEntity<>(movie1, HttpStatus.CREATED);
    }
}
