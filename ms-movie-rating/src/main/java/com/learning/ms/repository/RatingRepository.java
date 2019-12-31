package com.learning.ms.repository;

import com.learning.ms.model.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {
    List<Rating> findByUserId(Long userId);
}
