package com.basics;

import java.util.ArrayList;
import java.util.Collections;

public class Geeks {
	 public static void main(String[] args) {
	     
	     // Create a list of movies
	     ArrayList<Movie> l = new ArrayList<>();
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
	 }}