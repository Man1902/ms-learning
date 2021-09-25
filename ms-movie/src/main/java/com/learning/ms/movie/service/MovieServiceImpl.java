package com.learning.ms.movie.service;

import com.learning.ms.movie.model.Movie;
import com.learning.ms.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie getMovie(Long movieId) throws Exception {
        return movieRepository.findById(movieId).orElseThrow(() -> new Exception("Movie not found"));
    }

    @Override
    public Movie saveMovie(Movie movie) throws Exception {
        return movieRepository.save(movie);
    }
}
