package com.basics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Geeks {
	 public static void main(String[] args) {
	     
	     // Create a list of movies
	     List<Movie> l = new ArrayList<>();
	     l.add(new Movie("Star Wars", 8.7, 1977));
	     l.add(new Movie("Empire Strikes Back", 8.8, 1980));
	     l.add(new Movie("Return of the Jedi", 8.4, 1983));
	     
	     
	     // Sort movies using Comparable's 
	     // compareTo method by year
	     Collections.sort(l);

	     // Display the sorted list of movies
	     System.out.println("Movies after sorting by year:");
	     for (Movie m : l) {
	         System.out.println(m.getName() + " " + m.getRating() + " " + m.getYear());
	     }
	     System.out.println("---------------------");
	     // Sort movies by rating and display all
	        Collections.sort(l, new Rating());
	        System.out.println("Movies sorted by rating:");
	        for (Movie m : l) {
	            System.out.println(m.getName() + " " + m.getRating() + " " + m.getYear());
	        }
	        System.out.println("---------------------");
	        // Sort movies by name and display all
	        Collections.sort(l, new NameCompare());
	        System.out.println("\nMovies sorted by name:");
	        for (Movie m : l) {
	            System.out.println(m.getName() + " " + m.getRating() + " " + m.getYear());
	        }
	        
	   /*     Movies after sorting by year:
	        	Star Wars 8.7 1977
	        	Empire Strikes Back 8.8 1980
	        	Return of the Jedi 8. 4 1983
	        	---------------------
	        	Movies sorted by rating:
	        	Empire Strikes Back 8.8 1980
	        	Star Wars 8.7 1977
	        	Return of the Jedi 8.4 1983
	        	---------------------

	        	Movies sorted by name:
	        	Empire Strikes Back 8.8 1980
	        	Return of the Jedi 8.4 1983
	        	Star Wars 8.7 1977*/

	 }}