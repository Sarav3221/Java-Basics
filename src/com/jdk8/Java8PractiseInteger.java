package com.jdk8;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8PractiseInteger {

	public static void main(String[] args) {
		
//prime		
		List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 15, 17, 19, 20);
		//numbers.stream().distinct().sorted().filter()
		
		// Array - Array.stream(arr)min max
		//List - mapToInt(Integer::intValue)
		//how to use lambda expression
		List<Integer> l2=Arrays.asList(1,5,3,5,2);
		List<Integer> l3=Arrays.asList(4,5,7,2,1,9,8);
//contains		
	List<Integer> contains=	l2.stream().filter(e->l3.contains(e)).collect(Collectors.toList());
	System.out.println("contains"+contains);
	List<Integer> contains1=	l2.stream().filter(l3::contains).distinct().collect(Collectors.toList());
	System.out.println("contains1"+contains1);	
//contains efficient -Set	
		Set<Integer> setContains=new HashSet<>(l3);
		List<Integer> containsset=	l2.stream().filter(setContains::contains).distinct().collect(Collectors.toList());
		System.out.println("containsset"+containsset);	
		
	List<Integer> contains12only=	l2.stream().filter(e->!l3.contains(e)).collect(Collectors.toList());
	System.out.println("contains12only"+contains12only);
		
	List<Integer> lnull=Arrays.asList(3,null,4,5,7,2,1,9,8);
		
	//List<Integer> nonnull=	lnull.stream().flatMap(e-> Optional.ofNullable(e).stream()).collect(Collectors.toList());     - stream present in java 9
	
	List<Integer> nonnull=	lnull.stream().flatMap(e-> Optional.ofNullable(e).map(Stream::of).orElseGet(Stream::empty)).collect(Collectors.toList());      //java 8
	System.out.println("nonnull"+nonnull);
	List<Integer> nonnull2=	lnull.stream().filter(Objects::nonNull).collect(Collectors.toList());
	
		System.out.println("remove null"+nonnull);
		System.out.println("remove null2"+nonnull2);
		//mapToInt - Stream<Integer> → IntStream because min,max works on 
		//(Integer::intValue) - Integer to int
//sum of array
//		l2.stream().mapToInt(Integer::intValue).sum();

//min max
		int sum =		l2.stream().mapToInt(Integer::intValue).sum();
		System.out.println();
		int min	=	l2.stream().mapToInt(Integer::intValue).min().getAsInt();
		int max	=	l2.stream().mapToInt(Integer::intValue).max().getAsInt();
		
		
		/*int min = l2.stream().mapToInt(i -> i).min().getAsInt();
		int max = l2.stream().mapToInt(i -> i).max().getAsInt();*/
		
//average
 Double lavg=	  l2.stream().mapToInt(Integer::intValue).average().getAsDouble();		
			System.out.println("lavg"+lavg);
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
		
		Set<Integer> nondup=new HashSet<>();
//find Duplicate elements 	l2.stream().filter(n->!nondup.add(n)).forEach(System.out::println);
//uniquew element - l2.stream().filter(n->nondup.add(n)).forEach(System.out::println);		
		Set<Integer> seen=new HashSet<>();
			
			//l2.stream().filter(n->n%2==0).forEach(System.out::println);
		
//find - unique - not dupicate  
//			l2.stream().filter(n->seen.add(n)).forEach(System.out::println);
			//
		
//			l2.stream().filter(n->!seen.add(n)).forEach(System.out::println);

//frequency	
				
				List<Integer> nums = Arrays.asList(1, 2, 2, 3, 3, 3);
Map<Integer,Long> freqlist=		nums.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
System.out.println("freqlist"+freqlist);

// if null in list get nullpointerexception 


//18.11.2025		30 sec target		
//anymatch If at least one element in the stream satisfies the condition
				int value=3;
			boolean anymatch=	l2.stream().anyMatch(n->n==value);
			System.out.println("anymatch"+anymatch);
		
			boolean allmatch= l2.stream().allMatch(n->n==value);
				System.out.println("allmatch"+ allmatch);	
				
//allmatch				You want to check if all element in the stream satisfies the condition
				
//Find first element greater than 3
				int  first=		l2.stream().filter(n->n>3).findFirst().orElse(-1);
				System.out.println(first);	
				
				long  count=l2.stream().filter(n->n>3).count();
				System.out.println(count);
//square				 
		l2.stream().map(n->n*n).forEach(System.out::println);				//error filter(n->n)
		
//comma separated
		
		l2.stream().map(String::valueOf).collect(Collectors.joining(""));  //error map(n->n)
		
// Find product of all numbers
		int product =l2.stream().reduce(1,(a,b)->a*b);             //reduce combila all        //error  l2.stream().map(n->n*n+1).forEach(System.out::println);
		System.out.println(product);
		
//Count even and odd numbers using Streams		
		List<Integer> numbersoddeve = Arrays.asList(1, 2, 3, 4, 5, 6);
		
		Map<String, Long> result = numbersoddeve.stream()
		        .collect(Collectors.groupingBy(
		                n -> (n % 2 == 0) ? "Even" : "Odd",
		                Collectors.counting()
		        ));
//28.11.2025		
// Find the most repeated element in a list		
		 List<String> list = Arrays.asList("a", "b", "a", "c", "a", "b");
		
Map.Entry<String,Long> fmostrep=list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
System.out.println("fmostrep"+fmostrep);

				
				
//29.11.2025
//Find common values between 2 list 
			List<Integer> l1 = Arrays.asList(1, 2, 3, 4, 5,4,5);
			List<Integer> l4 = Arrays.asList(4, 5, 6, 7);
			//l1.addAll(l4);
			Set<Integer> commonset=new HashSet<>(l4);
			Set<Integer> commonset2=new HashSet<>();
			
//1st way to find common 	easy way 
			List<Integer> commonValues=	l1.stream().filter(l2::contains).collect(Collectors.toList());
			System.out.println("commonValues"+commonValues);
			
			List<Integer> common = 		l1.stream().filter(l4::contains).collect(Collectors.toList());
			
//2nd way 
			
			List<Integer> commonList=			l1.stream().filter(e->!commonset.add(e)).collect(Collectors.toList());
			System.out.println("commonset"+commonList);
			
//not to use list			
			List<Integer> commonlist=new ArrayList<>(l4);
			List<Integer> commonlist3=			l1.stream().filter(e->!commonlist.add(e)).collect(Collectors.toList());
			System.out.println("commonlist3"+commonlist3);
			  
			List<Integer> concat = 	Stream.concat(l1.stream(), l4.stream()).collect(Collectors.toList());
		System.out.println("concat"+concat);
		//1. What does commonset.add(e) return?

/*	.filter(e -> !commonset.add(e))
		In a Set, add(e) returns false if the element already exists.
		So !add(e) becomes true only for duplicate elements.
		And filter() keeps only the values where the condition is true.
		👉 Therefore, this expression filters and returns only duplicate (already existing) elements.
		It keeps only the elements that were already present in the Set — meaning it is used to find duplicates or common values.*/
		
		//if we add in list it will alwasy add element ! will return false. so use set 
		/*List<Integer> commonlist5=		Stream.concat(l1.stream(), l4.stream()).collect(Collectors.toList()).stream().filter(e->!commonlist.add(e)).collect(Collectors.toList());
		System.out.println("commonlist5"+commonlist5);*/
		
		//filter(e -> !commonset.add(e)) ✔ keeps only elements that already exist in the set ✔ used to detect duplicates or common elements
		
		//Why Approach 1 is not good - 2 list 
/*You first merge two lists Then collect them into a new list Then again stream that new list Unnecessary .distinct() on both lists
More GC, more overhead, more code This is over-engineering for a simple "find common values" problem.*/
		
		
		List<Integer> commonset3=		Stream.concat(l1.stream().distinct(), l4.stream().distinct()).collect(Collectors.toList()).stream().filter(e->!commonset2.add(e)).collect(Collectors.toList());
		System.out.println("commonset3"+commonset3);

//30.11.2025	//7:50 - 20 mins

		
		List<Integer> common2 = 	new ArrayList<>();
		int[] s1= {1, 2, 3, 4, 5};                                                   //int[] a1=new array[1, 2, 3, 4, 5];
		  int[] s2= {4, 5, 6, 7};
		  
	for(int i=0;i<s1.length;i++) {
		for(int j=0;j<s2.length;j++) {
			if(s1[i]==s2[j]) {
				common2.add(s1[i]);
			}
		}
	}
	System.out.println(common2);
	
//comma separated 
String commaseparated =	l1.stream().map(String::valueOf).collect(Collectors.joining(","));
	System.out.println("commaseparated"+commaseparated);
	
	
// 6pm recall  6:45pm -
	// error couldnt recall -because u didnt understand properly 
	

	
	l1.stream().mapToInt(Integer::intValue).min();
	l1.stream().mapToInt(Integer::intValue).max();
	l1.stream().mapToInt(Integer::intValue).sum();
	l1.stream().mapToInt(Integer::intValue).average();
	
	//l1.stream().mapToInt(mapper);
	
//	l1.stream();
	
	
//1-12-2025	
//peek
	Map<String,List<Integer>> resultpeak=	l1.stream().peek(e->System.out.println("Inside peek -> "+e)).collect(Collectors.groupingBy(e->e%2==0 ?"Even": "odd"));
	System.out.println(resultpeak);
	
	//list to map -	
	
//	l1.stream().map(n->n).collect(Collectors.tom)

//2-12-2025		
	
Map<String,List<Integer>> resultoddeven=	l1.stream().collect(Collectors.groupingBy(e->e%2==0 ?"Even": "odd"));
	System.out.println(resultoddeven);
	
	
	
//kth largest	2nd
	int n=2;
int kth=	l1.stream().sorted(Comparator.reverseOrder()).skip(n-1).findFirst().get();
	System.out.println(kth);
//remove optional	
	
	
//String mostRepeated 
	
//others asked  	
	
//8-12-2025
	//second highest  //error inside sorted we need to comparator.reverse order -- boxed int to Integer -11min
	int[] arr = {10, 20, 30, 40, 50};
	
	int secondEl=Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(-1);
System.out.println("secondEl"+secondEl);
//List<Integer> ls=Arrays.asList(arr); -- error we cannot do it we need to use boxed
//int secondEl=ls.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(-1);
	System.out.println("secondEl"+secondEl);
	
//sort map-10 mins
	Map<String, Integer> map1 = new HashMap<>();
	map1.put("Apple",3);
	map1.put("Banana", 5);
	map1.put("Cherry", 2);
	map1.entrySet().stream()
    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
    .forEach(e -> System.out.println(e.getKey() + "=" + e.getValue()));

	map1.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
	.forEach(System.out::println);
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	


	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
