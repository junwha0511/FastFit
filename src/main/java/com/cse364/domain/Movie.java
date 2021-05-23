package com.cse364.domain;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(of="id")
public class Movie {
    int id;
    @NonNull String title;
    @NonNull List<Genre> genres;
    @NonNull String link;

    /**
     * Returns whether this movie has given genre.
     */
    public boolean hasGenre(Genre genre) {
        for (Genre g: genres) {
            if (g.equals(genre)) { return true; }
        }
        return false;
    }

    /**
     * Returns whether this movie at least one of all given genres.
     */
    public boolean hasOneOfGenres(List<Genre> genres) {
        for (Genre genre: genres) {
            if (hasGenre(genre)) { return true; }
        }
        return false;
    }

    /**
     * Returns whether this movie has all given genres.
     */
    public boolean hasAllGenres(List<Genre> genres) {
        for (Genre genre: genres) {
            if (!hasGenre(genre)) { return false; }
        }
        return true;
    }
}
