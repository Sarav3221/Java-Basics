package com.basics;

import java.util.Comparator;

class Rating implements Comparator<Movie> {
    public int compare(Movie m1, Movie m2) {
      
        // Sort by rating in descending order
        return Double.compare(m2.getRating(), m1.getRating());
    }
}