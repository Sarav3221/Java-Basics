package com.basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BasicsPractise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		 int[] nums = {10, 20, 30};

	     char[] chars = {'J', 'A', 'V', 'A'};

	     String[] words = {"Java", "SpringBoot", "Microservices"};
	        
		int[] arr= {4,3,5,7};
		
		String[] strarr= {"sara","veera","mani"};
		
		for (int i = 0; i < strarr.length; i++) {
			System.out.println(strarr[i]);
		}
		System.out.println(strarr.toString());
		// Array[] a1= new Array[14,6,3]; wrong way of initilize array
		
		List<Integer> ls = new ArrayList<>();
		ls.add(5);
		ls.add(7);
		ls.add(3);
		ls.add(9);
		
		//System.out.println(ls.toString());
		
		/*Map<String,Integer> m1=new HashMap<>();
		m1.put("A",1);
		m1.put("B",2);
		System.out.println("m1.size() Before"+m1.size());
		
		for (Map.Entry<String,Integer> ent : m1.entrySet()) {
			m1.remove("A");
			m1.remove("B");
		}
		System.out.println("m1.size() After"+m1.size());*/
		
		
		Map<String,Integer> m2=new ConcurrentHashMap<>();
		m2.put("A",1);
		m2.put("B",2);
		System.out.println("m2.size() Before"+m2.size());
		
		for(Map.Entry<String,Integer> map: m2.entrySet()) {
			m2.remove("A");
			m2.remove("B");
			m2.put("C",2);
		}
		System.out.println("m2.size() After"+m2.size());
		
		
		
		List<String> list = Arrays.asList("a", "b");
		list.add("c");

		

		
	}
	
	//concurrent modification exception happens

}
