package com.learning.ms.movierating.service;

import com.learning.ms.movierating.model.Rating;

import java.util.List;

public interface RatingService {
    Rating saveRating(Rating rating) throws Exception;

    List<Rating> getRatings(Long userId) throws Exception;
}
