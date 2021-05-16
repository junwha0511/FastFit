package com.cse364.infra;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.cse364.domain.*;

public class InMemoryMovieRepository implements MovieRepository {
    private HashMap<Integer, Movie> movies = new HashMap<>();

    public InMemoryMovieRepository() { }

    /**
     * Adds a movie to the storage.
     */
    public void add(Movie movie) {
        movies.put(movie.getId(), movie);
    }

    public Movie get(int id) {
        return movies.get(id);
    }

    public Movie get(String title) {
        for (Movie mov : movies.values()) {
            if (Objects.equals(title, mov.getTitle())) {
                return mov;
            }
        }
        
        return null;
    }

    public List<Movie> all() {
        return new ArrayList(movies.values());
    }   
}
