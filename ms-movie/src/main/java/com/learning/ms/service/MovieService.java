package com.learning.ms.service;

import com.learning.ms.model.Movie;

public interface MovieService {
    Movie getMovie(Long movieId) throws Exception;

    Movie saveMovie(Movie movie) throws Exception;
}
