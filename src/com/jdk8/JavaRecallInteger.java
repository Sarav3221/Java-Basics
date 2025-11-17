package com.jdk8;

import java.util.Arrays;
import java.util.List;

public class JavaRecallInteger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> l2=Arrays.asList(1,9,5,5,2,1,9,8);
		List<Integer> l3=Arrays.asList(3,4,5,7,2,1,9,8);
		
		l2.stream().mapToInt(Integer::intValue).sum();
		l2.stream().mapToInt(Integer::intValue).min().getAsInt();
		l2.stream().mapToInt(Integer::intValue).max().getAsInt();
		

	}

}
