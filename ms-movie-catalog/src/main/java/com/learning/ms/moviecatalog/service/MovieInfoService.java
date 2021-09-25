package com.learning.ms.moviecatalog.service;

import com.learning.ms.moviecatalog.model.MovieCatalog;
import com.learning.ms.moviecatalog.model.Rating;

public interface MovieInfoService {
    public MovieCatalog getMovieCatalog(Rating rating);
}
