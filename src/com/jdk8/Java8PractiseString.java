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

public class Java8PractiseString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//object - avoid null and res
		
		//s ql groub by age,name having age ?30
		// count(age) group by age having count (age)>1
		//delete fro user wher eid in (select id from where n>1)
		
//Remove Null from list		
	String[] strl=	{"a",null,"b","","h"};
		List<String> str= Arrays.asList("a",null,"b","","h");
		
		
		
		str.stream().map(n->Optional.ofNullable(n)).collect(Collectors.toList()).forEach(System.out::println);
		
		//str.stream().filter(Object::nonNull).collect(Collectors.toList());
		
		str.stream().filter(n->n!=null).collect(Collectors.toList()).forEach(System.out::println);
		
	
		/*str.stream().map(s->Optional.ofNullable(s)).toList();
		str.stream().filter(Object::nonNull).toList();
		str.stream().filter(e->e!=null).tolist();*/
		
		

//testing avaj program
		String input = "Java Interview Ques";
		List<String> ls=Arrays.asList(input.split(""));
	/*	String input = "Java Interview Ques";
		StringBuffer result=new StringBuffer();
		List<String> nstr=Arrays.asList(input.split(" "));
		for (String string : nstr) {
			if(string.length()==4) {
				result.append(new StringBuffer(string).reverse());
			}
			else {
				result.append(string);
			}
			result.append(" ");
		}
		System.out.println(result);*/
		
//occurance 
	
	Map<Character,Long> charCount=	input.chars().mapToObj(C-> (char) C).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(charCount);
	//charCount.entrySet().forEach(System.out.println());
	//String 		
//5.11.2025 3:50PM	
	/*String input = "Java Interview Question";
	Arrays.stream(input.split(" ")).map(e->new StringBuilder(e).reverse()).collect(Collectors.toList());
	//avaJweivretnInoitseuQ
	Arrays.stream(input.split(" ")).map(e->new StringBuilder(e).reverse().toString()).collect(Collectors.joining(" "));*/
		//2
	List<Integer> l1=Arrays.asList(1,3,5,5,6,9,8);
	
	//find first
	//	l1.stream().sorted(Comparator.reverseOrder()).findFirst().ifPresent(System.out::println);
	//5:50pm
	//remove duplicate
	//	l1.stream().distinct().forEach(System.out::println);
	
	//occurance of each value
	//Map<Integer,Long> occurence=l1.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	//System.out.println(occurence);
	
	
	String s1="successeu";
	
	Map<Character,Long> occurence1=s1.chars().mapToObj(c->(char) c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	
	/*s1.chars()
	Converts your string into an IntStream of Unicode values.
	Example:
	"aba".chars() → [97, 98, 97]
	2️⃣ .mapToObj(c -> (char) c)
	Converts each integer (Unicode value) back to a Character object.
	Because chars() returns int, we convert it to (char).
	So "aba" → ['a', 'b', 'a']
	3️⃣ .collect(...)
	You collect the stream into a Map<Character, Long>.*/

//Reverse	
	String palin="Malayalam";
	String outp="";
	
	/*
	for(int i=palin.length()-1;i>=0;i--) {
		char c = palin.charAt(i);
		outp=outp+c;
	}
	if(palin.equals(outp)) {
		System.out.println("Palindrome");
	}
	else {
		System.out.println("Not a Palindrome");
	}*/
	
	//s1.chars().mapToObj().
	}

}
