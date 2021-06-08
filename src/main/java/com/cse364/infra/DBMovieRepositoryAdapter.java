package com.cse364.infra;

import com.cse364.database.repositories.DBMovieRepository;
import com.cse364.domain.Movie;
import com.cse364.domain.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DBMovieRepositoryAdapter implements MovieRepository {
    DBMovieRepository movies;

    public DBMovieRepositoryAdapter(DBMovieRepository movies) {
        this.movies = movies;
    }

    /**
     * Adds a movie to the storage.
     */
    public void add(Movie movie) {
        movies.insert(movie);
    }

    public Movie get(int id) {
        return movies.findById(id).get();
    }

    public Movie get(String title) {
        StringBuilder sb = new StringBuilder();
        for(String i : title.split("")){
            sb.append("["+i+"]");
        }
        return movies.get(sb.toString());
//        return movies.get(title);
    }

    public List<Movie> all() {
        return movies.findAll();
    }
}
