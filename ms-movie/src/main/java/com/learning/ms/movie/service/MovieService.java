package com.learning.ms.movie.service;

import com.learning.ms.movie.model.Movie;

public interface MovieService {
    Movie getMovie(Long movieId) throws Exception;

    Movie saveMovie(Movie movie) throws Exception;
}
