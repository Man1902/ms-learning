package com.learning.ms.moviecatalog.service;

import com.learning.ms.moviecatalog.model.UserRating;

public interface UserRatingService {
    UserRating getUserRating(Long userId);
}
