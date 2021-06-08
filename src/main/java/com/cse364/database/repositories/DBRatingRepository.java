package com.cse364.database.repositories;

import com.cse364.domain.Movie;
import com.cse364.domain.Rating;
import com.cse364.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DBRatingRepository extends MongoRepository<Rating, String> {
    @Query("{'movie': ?0)")
    List<Rating> filterByMovie(Movie movie);

    @Query("{'user': ?0)")
    List<Rating> filterByUser(User user);
}
