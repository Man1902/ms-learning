package com.learning.ms.service;

import com.learning.ms.model.Rating;

import java.util.List;

public interface RatingService {
    Rating saveRating(Rating rating) throws Exception;

    List<Rating> getRatings(Long userId) throws Exception;
}
