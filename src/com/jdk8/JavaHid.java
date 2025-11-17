package com.jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JavaHid {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> l1=Arrays.asList(2,4,7,6,9);
		
		//find a best way max sum - triplet largest -9,7,6
		
	//	l1.stream().sorted(Comparator.reverseOrder()).limit(3).tolist
		
		l1.stream()
        .sorted(Comparator.reverseOrder()) // sort in descending
        .limit(3)                          // take first 3 elements
     .collect(Collectors.toList());
		
	int l2=	l1.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
	System.out.println(l2);
        
	}

}
