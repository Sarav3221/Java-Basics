package com.jdk8;

import java.util.ArrayList;
import java.util.List;

public class Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> emplist=new ArrayList<String>();
		emplist.add("Madam");
		emplist.add("Sir");
		emplist.add("MalayalaM");
		List<String> palindromelist=new ArrayList<String>();
		
		for (String string : emplist) {
			String rev=new StringBuilder(string).reverse().toString();
			if(rev.equalsIgnoreCase((string))) {
				palindromelist.add(string);
			}
		}
		System.out.println(palindromelist);

	}

}
