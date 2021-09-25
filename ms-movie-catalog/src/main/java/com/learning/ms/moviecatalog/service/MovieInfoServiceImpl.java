package com.learning.ms.moviecatalog.service;

import com.learning.ms.moviecatalog.model.Movie;
import com.learning.ms.moviecatalog.model.MovieCatalog;
import com.learning.ms.moviecatalog.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoServiceImpl implements MovieInfoService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "getMovieCatalogFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public MovieCatalog getMovieCatalog(Rating rating) {
        Movie movie = restTemplate.getForObject("http://ms-movie/movies/" + rating.getMovieId(), Movie.class);
        return new MovieCatalog(movie.getMovieName(), rating.getRating());
    }

    public MovieCatalog getMovieCatalogFallback(Rating rating) {
        return new MovieCatalog("Movie name not found", rating.getRating());
    }
}
