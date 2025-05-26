package com.basics;

import java.util.Comparator;

class NameCompare implements Comparator<Movie> {
    public int compare(Movie m1, Movie m2) {
      
        // Sort by name in alphabetical order
        return m1.getName().compareTo(m2.getName()); 
    }
}




