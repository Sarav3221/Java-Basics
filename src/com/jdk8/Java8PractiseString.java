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
import java.util.Map.Entry;
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
	
	Map<Character,Long> charCount=	input.chars().filter(Character::isLetter).mapToObj(C-> (char) C).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(charCount);
	//charCount.entrySet().forEach(System.out.println());
	//String 		
//5.11.2025 3:50PM	
	String input2 = "Java Interview Question";
	
	Arrays.stream(input.split(" ")).map(e-> new StringBuffer(e).reverse());
	
	
	
	/*Arrays.stream(input.split(" ")).map(e->new StringBuilder(e).reverse()).collect(Collectors.toList());
	//avaJweivretnInoitseuQ
	Arrays.stream(input.split(" ")).map(e->new StringBuilder(e).reverse().toString()).collect(Collectors.joining(" "));*/
		//2
	
	
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
	
	
	Map<Character,Long> charCount2 =palin.chars().mapToObj(c->(char) c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	
	//Arrays.stream(input.split(" ")).map(e->new stringBuffer(e).reverse());
	
	String rev =s1.chars().mapToObj(c->(char) c).reduce("",(c,s)->s+c,String::concat);
	System.out.println("rev "+rev);
	
	/*.    reduce() is used to combine all characters into one string.*/
	
	
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
	
	
//occurance
			/*String input = "Java Interview Ques";
			
		Map<Character,Long> finalval=	input.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
			finalval.entrySet().forEach(System.out::println);
//reverse			
			input.chars().mapToObj(c->(char) c).reduce("",(s,c) ->c+s,String::concat);*/
	
	/*String s = "ABCDE";
	s.chara90+ newstrinbuffer(s.subsring(1).resevert.tostrin)
	String result = s.charAt(0) + new StringBuilder(s.substring(1)).reverse().toString();
	System.out.println(result);*/
	
	String rot="abcde";
	int n=2;
    /*int	len=rot.length();
    int n=0;
	 n =n % len;*/
	/*
	String rotOutput=rot.substring(n)+rot.substring(0, n);
	System.out.println("rotOutput "+rotOutput);*/
	
	String rotOutput=rot.substring(0, n)+rot.substring(n);
	System.out.println("rotOutput "+rotOutput);
	
//8-12-25 
// find non repeating element	-33 mins
	String input1="aabbcddee";
	
Map<String,Long> nonrepeating=	Arrays.asList(input1.split("")).stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
String strNonRep= nonrepeating.entrySet().stream().filter(e->e.getValue()==1).map(Map.Entry::getKey).findFirst().orElse(null);
System.out.println("strNonRep"+strNonRep);

//occurence
input1.chars().mapToObj(c-> (char) c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().forEach(System.out::println);

// min false
System.out.println("nonrepeating "+nonrepeating.entrySet().stream().min(Entry.comparingByValue()).orElse(null));
nonrepeating.entrySet().stream().min(Entry.comparingByValue()).orElse(null);

//freq word 6min
String freq="Java Java Spring Boot Boot";
List<String> fres=Arrays.asList(freq.split(" "));
	
//Map<String,Long> freqOut=	fres.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
Arrays.stream(freq.split(" ")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
Map<String,Long> freqOut=	fres.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	System.out.println("freqOut"+freqOut);
	

}}
