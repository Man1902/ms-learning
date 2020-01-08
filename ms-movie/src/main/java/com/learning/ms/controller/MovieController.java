package com.learning.ms.controller;

import com.learning.ms.model.Movie;
import com.learning.ms.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private RestTemplate restTemplate;

    private static final String API_KEY = "cea3b7a0b210db1ea9f3707365849dd8";

    @RequestMapping("/{movieId}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long movieId) throws Exception {
        LOGGER.info("/movies/{movieId} API invocation -> Start");
        Movie movie = movieService.getMovie(movieId); // OR

        /*MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + API_KEY, MovieSummary.class);
        Movie movie = new Movie(movieId, movieSummary.getOriginal_title());*/

        LOGGER.info("/movies/{movieId} API invocation -> End");
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) throws Exception {
        Movie movie1 = movieService.saveMovie(movie);
        return new ResponseEntity<>(movie1, HttpStatus.CREATED);
    }
}
