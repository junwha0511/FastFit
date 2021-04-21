package com.cse364.infra;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;
import com.cse364.domain.*;

public class InMemoryRatingRepositoryTest {
    private InMemoryRatingRepository testObject;
    private User user1, user2, user3;
    private Movie movie1, movie2, movie3;
    private List<Rating> ratings;

    @Before
    public void init(){
        testObject = new InMemoryRatingRepository();
        user1 = new User(1, User.Gender.M, 20, new Occupation(1, "Teacher"), "10000");
        user2 = new User(2, User.Gender.F, 55, new Occupation(2, "Retired"), "10001");
        user3 = new User(3, User.Gender.M, 30, new Occupation(0, "Others"), "10002");
        movie1 = new Movie(
                1,
                "Toy Story",
                new ArrayList<Genre>() {{
                    add(new Genre("Animation"));
                    add(new Genre("Children's"));
                    add(new Genre("Comedy"));
                }}
        );
        movie2 = new Movie(
                2,
                "Jumanji",
                new ArrayList<Genre>() {{
                    add(new Genre("Adventure"));
                    add(new Genre("Children's"));
                    add(new Genre("Fantasy"));
                }}
        );
        movie3 = new Movie(
                3,
                "Grumpier Old Men",
                new ArrayList<Genre>() {{
                    add(new Genre("Comedy"));
                    add(new Genre("Romance"));
                }}
        );

        //Combination of all possible rating by user1 and user2 on movie1 and movie2
        testObject.add(new Rating(movie1, user1, 5, 0));
        testObject.add(new Rating(movie1, user2, 4, 0));
        testObject.add(new Rating(movie2, user1, 3, 0));
        testObject.add(new Rating(movie2, user2, 2, 0));
    }

    @Test
    public void testFilterByMovie(){
        ratings = testObject.filterByMovie(movie1);
        for(Rating rating : ratings){
            assertSame(rating.getMovie(), movie1);
            assertNotSame(rating.getMovie(), movie2);
        }
        assertNotNull(testObject.filterByMovie(movie3));
    }

    @Test
    public void testFilterByUser(){
        ratings = testObject.filterByUser(user1);
        for(Rating rating : ratings){
            assertSame(rating.getUser(), user1);
            assertNotSame(rating.getUser(), user2);
        }
        assertNotNull(testObject.filterByUser(user3));
    }
}
