package com.cse364.domain;

import java.util.List;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class MovieTest {
    private Movie movie;
    private List<Genre> genreList;

    @Before
    public void init() {
        genreList = List.of(
            new Genre("Animation"),
            new Genre("Children's"),
            new Genre("Comedy")
        );

        movie = new Movie(
            1,
            "Toy Story",
            genreList,
            "link1"
        );
    }

    @Test
    public void testConstructor() {
        assertEquals(movie.getId(), 1);
        assertEquals(movie.getTitle(), "Toy Story");
        assertEquals(movie.getGenres(), genreList);
        assertEquals(movie.getLink(), "link1");
    }

    @Test
    public void testHasGenre() {
        assertTrue(
            movie.hasGenre(new Genre("Children's"))
        );
        assertFalse(
            movie.hasGenre(new Genre("Different Genre"))
        );
    }

    @Test
    public void testHasAllGenres() {
        assertTrue(
            movie.hasAllGenres(List.of(
                new Genre("Comedy"),
                new Genre("Animation")
            ))
        );
        assertFalse(
            movie.hasAllGenres(List.of(
                new Genre("Children's"),
                new Genre("Different Genre")
            ))
        );
    }

    @Test
    public void testHasOneOfGenres() {
        assertTrue(
                movie.hasOneOfGenres(List.of(
                        new Genre("Comedy"),
                        new Genre("X")
                ))
        );
        assertFalse(
                movie.hasOneOfGenres(List.of(
                        new Genre("X"),
                        new Genre("Y")
                ))
        );
    }

    @Test
    public void testEquals() {
        assertEquals(movie, new Movie(
                1,
                "Toy Story",
                genreList,
                "link"
        ));
        assertNotEquals(movie, new Movie(
                2,
                "Hard Story",
                genreList,
                "link"
        ));
        assertNotEquals(movie, null);
    }
}
