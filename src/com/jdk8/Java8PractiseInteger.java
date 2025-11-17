package com.jdk8;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8PractiseInteger {

	public static void main(String[] args) {
		//how to use lambda expression
		List<Integer> l2=Arrays.asList(1,9,5,5,2,1,9,8);
		List<Integer> l3=Arrays.asList(3,4,5,7,2,1,9,8);

//sum of array
//		l2.stream().mapToInt(Integer::intValue).sum();

//min max
		int min = l2.stream().mapToInt(i -> i).min().getAsInt();
		int max = l2.stream().mapToInt(i -> i).max().getAsInt();
		
//average
 Double lavg=	  l2.stream().mapToInt(Integer::intValue).average().getAsDouble();		
			
//reverse
			//l2.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).forEach(System.out::println);
//Merge two lists and remove duplicates			
			//Stream.concat(l2.stream(), l3.stream()).distinct().collect(Collectors.toList()).forEach(System.out::println);
			
			//	l2.stream().sorted().collect(Collectors.toList()).forEach(System.out::println);

			
//Optional	
		/*	List<Integer> numbers = Arrays.asList(1, null, 3, null, 5);
			List<Integer> cleanedList = numbers.stream()
		            .flatMap(num -> Optional.ofNullable(num).stream())
		            .collect(Collectors.toList());

		System.out.println(cleanedList); // [1, 3, 5]


		List<Integer> numbers = Arrays.asList(1, null, 3, null, 5);

		List<Integer> cleanedList = numbers.stream()
		                                   .filter(Objects::nonNull)
		                                   .collect(Collectors.toList());
		System.out.println(cleanedList); // [1, 3, 5]
		*/
			

		//	System.out.println(lavg);
			//Map<Integer,long> map=l2.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
//find unique elements		
		Set<Integer> nondup=new HashSet<>();
		l2.stream().filter(n->!nondup.add(n)).forEach(System.out::println);
		
		Set<Integer> seen=new HashSet<>();
			
			//l2.stream().filter(n->n%2==0).forEach(System.out::println);
		
//find not dupicate  
//			l2.stream().filter(n->seen.add(n)).forEach(System.out::println);
			//
//			l2.stream().filter(n->!seen.add(n)).forEach(System.out::println);

//frequency	
				
				List<Integer> nums = Arrays.asList(1, 2, 2, 3, 3, 3);
				nums.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().forEach(System.out::println);			
				
				
	}

}
