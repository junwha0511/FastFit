package com.cse364.infra;

import com.cse364.infra.dtos.*;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataLoaderTest {
    private String moviesData;
    private String linksData;
    private String usersData;
    private String ratingsData;

    @Before
    public void init() {
        moviesData = "7::Sabrina (1995)::Comedy|Romance\n" +
                "8::Tom and Huck (1995)::Adventure|Children's\n" +
                "9::Sudden Death (1995)::Action";

        linksData = "7::0114319\n" +
                "8::0112302\n" +
                "9::0114576";

        usersData = "4::M::45::7::02460\n" +
                "5::M::25::20::55455\n" +
                "6::F::50::9::55117";

        ratingsData = "1::608::4::978301398\n" +
                "1::1246::4::978302091\n" +
                "2::1357::5::978298709";
    }

    @Test
    public void testLoadMovies() {
        List<MovieDto> loadedMovies = DataLoader.loadMovies(
                new StringReader(moviesData),
                new StringReader(linksData),
                "movie/link/"
        );
        List<MovieDto> expectedMovies = List.of(
                new MovieDto(7, "Sabrina (1995)", List.of("Comedy", "Romance"), "movie/link/0114319"),
                new MovieDto(8, "Tom and Huck (1995)", List.of("Adventure", "Children's"), "movie/link/0112302"),
                new MovieDto(9, "Sudden Death (1995)", List.of("Action"), "movie/link/0114576")
        );

        assertEquals(loadedMovies, expectedMovies);
    }

    @Test
    public void testLoadUsers() {
        List<UserDto> loadedUsers = DataLoader.loadUsers(new StringReader(usersData));
        List<UserDto> expectedUsers = List.of(
                new UserDto(4, "M", 45, 7, "02460"),
                new UserDto(5, "M", 25, 20, "55455"),
                new UserDto(6, "F", 50, 9, "55117")
        );

        assertEquals(loadedUsers, expectedUsers);
    }

    @Test
    public void testLoadRatings() {
        List<RatingDto> loadedRatings = DataLoader.loadRatings(new StringReader(ratingsData));
        List<RatingDto> expectedRatings = List.of(
                new RatingDto(1, 608, 4, 978301398),
                new RatingDto(1, 1246, 4, 978302091),
                new RatingDto(2, 1357, 5, 978298709)
        );

        assertEquals(loadedRatings, expectedRatings);
    }
}
