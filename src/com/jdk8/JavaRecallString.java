package com.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JavaRecallString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input2 = "Java Interview Question";
		
	String rev=	input2.chars().mapToObj(c->(char) c).reduce("",(c,s)->s+c,String::concat);
	System.out.println(rev);
	
	Arrays.stream(input2.split(" ")).map(e-> new StringBuffer(e).reverse());
	
	}

}
