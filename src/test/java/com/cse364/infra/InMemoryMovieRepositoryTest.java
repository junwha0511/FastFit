package com.cse364.infra;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.cse364.domain.*;

public class InMemoryMovieRepositoryTest {
    private InMemoryMovieRepository storage;
    private List<Movie> movies = List.of(
            new Movie(1, "A", null),
            new Movie(2, "B", null)
            );

    @Before
    public void init() {
        storage = new InMemoryMovieRepository();
    }

    @Test
    public void testAdd(){
        storage.add(movies.get(0));
        assertEquals(storage.get(1), movies.get(0));
    }

    @Test
    public void testAll(){
        for(Movie movie : movies){
            storage.add(movie);
        }

        assertTrue(movies.equals(storage.all()));
    }
}
