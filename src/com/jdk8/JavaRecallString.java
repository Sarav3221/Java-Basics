package com.jdk8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JavaRecallString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//4-6-2026 Target 30 Q -  Recall - 1hour 
		//Reverse a string 
		String input1 = "Java Interview Question";
		
	String rev1=	input1.chars().mapToObj(c-> (char) c).reduce("",(c,s)->s+c,String::concat);
		System.out.println("rev1 "+rev1);
		
		
	StringBuilder sb=new StringBuilder(input1);
	sb.reverse();
	System.out.println("sb "+sb);
		
		
		
	// remove duplicate char  -50 mins 
	String input2 = "programming";	
	
	String inp=input2.chars().distinct().mapToObj(c-> String.valueOf((char) c)).collect(Collectors.joining());
	System.out.println("");
	//But Collectors.joining() works only with a Stream<String>, not a Stream<Character>.
	
	
	// find duplicate char 
	String input3 = "programming";	
	Set st=new HashSet<>();
	 List<Character> duplicate=	input3.chars().mapToObj(c-> (char) c).filter(c->!st.add(c)).collect(Collectors.toList());
	 System.out.println("st"+st);
	 System.out.println("duplicate"+duplicate);
	input3.chars().mapToObj(c-> (char) c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream().filter(e->e.getValue()>1).map(Map.Entry::getKey).forEach(System.out::println);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Rev string 
		
		String input4 = "Java Interview Question";
		
	String rev=	input4.chars().mapToObj(c->(char) c).reduce("",(c,s)->s+c,String::concat);
	System.out.println(rev);
	
	Arrays.stream(input2.split(" ")).map(e-> new StringBuffer(e).reverse());
	
	}

}
