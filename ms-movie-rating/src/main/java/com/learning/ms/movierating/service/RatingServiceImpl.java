package com.learning.ms.movierating.service;

import com.learning.ms.movierating.model.Rating;
import com.learning.ms.movierating.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating saveRating(Rating rating) throws Exception {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings(Long userId) throws Exception {
        return ratingRepository.findByUserId(userId);
    }
}
