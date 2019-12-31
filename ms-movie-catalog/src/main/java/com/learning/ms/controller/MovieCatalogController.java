package com.learning.ms.controller;

import com.learning.microservice.model.MovieCatalog;
import com.learning.ms.model.Movie;
import com.learning.ms.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/moviecatalogs")
public class MovieCatalogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieCatalogController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;


    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getMovieCatalogDtlsFailOver", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public List<MovieCatalog> getMovieCatalogDtls(@PathVariable int userId) {
		LOGGER.info("/moviecatalogs/{userId} API invocation -> Start");
        // 1st Microservice call
        //UserRating userRating = restTemplate.getForObject("http://localhost:8082/ratings/users/" + userId, UserRating.class);
        UserRating userRating = restTemplate.getForObject("http://ms-movie-rating/ratings/users/" + userId, UserRating.class);

        List<MovieCatalog> listMovieCatalog = userRating.getRatings().stream().map(rating -> {
            // 2nd Microservice call
            // Approach 1 : Using RestTemplate (deprecated in latest version)
            Movie movie = restTemplate.getForObject("http://ms-movie/movies/" + rating.getMovieId(), Movie.class);

            // Approach 2 : Using WebClient (Reactive way)
			/*Movie movie = webClientBuilder.build()
					.get()
					.uri("http://localhost:8083/movies/" + rating.getMovieId())
					.retrieve()
					.bodyToMono(Movie.class)
					.block();*/

            return new MovieCatalog(movie.getMovieName(), rating.getRating());
        }).collect(Collectors.toList());
		LOGGER.info("/moviecatalogs/{userId} API invocation -> End");
        return listMovieCatalog;
    }

    public List<MovieCatalog> getMovieCatalogDtlsFailOver(int usedId) {
        return new ArrayList<>();
    }
}
