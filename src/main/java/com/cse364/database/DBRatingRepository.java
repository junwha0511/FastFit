package com.cse364.database;

import com.cse364.domain.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DBRatingRepository extends MongoRepository<Rating, String> {
}