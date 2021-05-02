package com.cse364.infra;

import com.cse364.app.NoRatingForGenreException;
import com.cse364.domain.Genre;
import com.cse364.domain.Occupation;
import com.cse364.domain.Movie;
import com.cse364.domain.Gender;
import com.cse364.domain.UserInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Collections;

public class Controller {
    private final Config config;

    public Controller(Config config) {
        this.config = config;
    }

    public void main(String[] args) {
        //Select behaviour by input length
        if (args.length == 2) {
            getAverageRating(args);
        } else if (args.length == 3) {
            getTop10Movies(args);
        } else if (args.length == 4) {
            getTop10MoviesWithGenres(args);
        } else {
            System.out.println("Input Error : Input format is...\n" +
                                "    AverageRating : '[genre1\\|genre2\\| ... ] [occupation]'\n" + 
                                "    RankingForUser : ''\n" +
                                "    RankingForUser&Genre : ''");
        }
    }

    void getAverageRating(String[] args) {
        HashSet<Genre> genres = new HashSet<>();
        for (String genreName : args[0].split("\\|")) {
            Genre genre = config.genres.searchByName(genreName);
            if (genre == null) {
                System.out.format("Error : The genre %s does not exist in database\n", genreName);
                System.exit(0);
            }
            genres.add(genre);
        }

        Occupation occupation = config.occupations.searchByName(args[1]);

        if (occupation == null) {
            System.out.format("Error : The occupation %s does not exist in database\n", args[1]);
            System.exit(0);
        }

        printAverageRating(new ArrayList<>(genres), occupation);
    }

    void getTop10Movies(String[] args) {
        UserInfo theInfo = buildUserInfo(args);

        List<Movie> topRank = config.rankingService.getTopNMovie(theInfo, 10, Collections.emptyList());

        System.out.println("The movie we recommend are:");
        for (Movie movie : topRank) {
            System.out.format("%s\n", movie.getTitle());
        }
    }

    void getTop10MoviesWithGenres(String[] args) {
        UserInfo theInfo = buildUserInfo(args);
        
        HashSet<Genre> genres = new HashSet<>();
        for (String genreName : args[3].split("\\|")) {
            Genre genre = config.genres.searchByName(genreName);
            if (genre == null) {
                System.out.format("Error : The genre %s does not exist in database\n", genreName);
                System.exit(0);
            }
            genres.add(genre);
        }
        
        List<Movie> topRank = config.rankingService.getTopNMovie(theInfo, 10, new ArrayList<>(genres));

        System.out.println("The movie we recommend are:");
        for (Movie movie : topRank) {
            System.out.format("%s\n", movie.getTitle());
        }
    }

    /**
     * return String of genres combined with divider
     */
    String formatGenres(List<Genre> genres, String divider) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < genres.size(); i++) {
            sb.append(genres.get(i).getName());
            if (i < genres.size() - 1) { sb.append(divider); }
        }
        return sb.toString();
    }

    void printAverageRating(List<Genre> genres, Occupation occupation){
        try {
            double average = config.averageRatingService.averageRating(genres, occupation);

            System.out.format("Average rating of movies with genres [%s]\n", formatGenres(genres, ", "));
            System.out.format("rated by people with occupation [%s]\n", occupation.getName());
            System.out.format("is [%f].\n", average);
        } catch (NoRatingForGenreException e) {
            System.out.format(
                    "Error : There were no ratings given to movies with genre [%s] by people with occupation [%s]\n",
                    formatGenres(genres, ", "), occupation.getName()
            );
        }
    }

    /*
     * Build UserInfo from input strings
     */
    UserInfo buildUserInfo(String args[]) {
        Gender gender = null;
        if ("".equals(args[0])) {
            // pass
        } else if ("M".equals(args[0])) {
            gender = Gender.M;
        } else if ("F".equals(args[0])) {
            gender = Gender.F;
        } else {
            System.out.println("WHAT???");
            System.exit(0);
        }
        
        int age = -1;
        if (!"".equals(args[1])) { age = Integer.parseInt(args[1]); }
        Occupation occupation = config.occupations.searchByName(args[2]);
        return new UserInfo(gender, age, occupation, "00000");
    }
}
