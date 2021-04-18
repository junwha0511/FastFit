package com.cse364;

import java.util.Map;
public class Occupation {
    private int id;
    private String name;

    // Getters
    int getId() { return id; }
    String getName() { return name; }

    /**
     * WARNING: Don't use this constructor directly!
     * Please access Genre data using GenreStorage instead.
     */
    Occupation(int id, String name) {
        this.id = id;
        this.name = name;
    }


    boolean equals(Occupation occupation) {
        return this.id == occupation.id;
    }
}
