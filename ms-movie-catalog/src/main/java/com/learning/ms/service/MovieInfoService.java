package com.learning.ms.service;

import com.learning.ms.model.MovieCatalog;
import com.learning.ms.model.Rating;

public interface MovieInfoService {
    public MovieCatalog getMovieCatalog(Rating rating);
}
