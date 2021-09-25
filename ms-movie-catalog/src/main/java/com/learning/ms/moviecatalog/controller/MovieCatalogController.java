package com.learning.ms.moviecatalog.controller;

import com.learning.ms.moviecatalog.model.MovieCatalog;
import com.learning.ms.moviecatalog.model.UserRating;
import com.learning.ms.moviecatalog.service.MovieInfoService;
import com.learning.ms.moviecatalog.service.UserRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/moviecatalogs")
public class MovieCatalogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieCatalogController.class);

    @Autowired
    private UserRatingService userRatingService;

    @Autowired
    private MovieInfoService movieInfoService;

    @Value("${my.prop1:default value}")
    private String myProp1FromConfigServer;

    @RequestMapping("/{userId}")
    public List<MovieCatalog> getMovieCatalogDtls(@PathVariable Long userId) {
        LOGGER.info("/moviecatalogs/{userId} API invoked");
        UserRating userRating = userRatingService.getUserRating(userId);
        return userRating.getRatings().stream()
                .map(rating -> movieInfoService.getMovieCatalog(rating))
                .collect(Collectors.toList());
    }

    @RequestMapping("/test/config-server")
    public String testConfigServer() {
        return "my.prop1 from config server value : " + myProp1FromConfigServer;
    }

}
