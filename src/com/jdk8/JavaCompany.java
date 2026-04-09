package com.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaCompany {

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
	
	
	//capgemini
	//prod issues worked how fixed
			//memory leak
			//heapdump
			//how u avoid -memory leak
			//scenario to explain how synchronise help memory lock
			//avoid thread lock 
			//method level synchronization, block level synchronization
			// query optimize-db
			//
			// two  threads lock each other - Need to use synchronise
			//use of method reference 
			//parall stream for multipl thread
			//stream use
			//diagnize- time -synchronize
			//how to avoid slow query db-index-hikari pool
			//how to avoid message-duplicate in queue -unique id
			//how to make data consistent in microservice-
			//nms - ems
			//linux basic commands-aws- golang -not specific to java  
			//internet flight -intern-aws
			
			Thread a =new Thread();
			Thread b =new Thread();
			
			/*List<Integer> l1 = Arrays.asList(2,5,7,9);
			l1.stream().filter(n->n>3).forEach(System.out::println);
			
			try{
				
				synchronized () {
					
				}
				
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
			
19-11-2025 hcl			
			//frequency of each character
		String str="JavaSpring";
		
Map<Character,Long>	frequency=	str.chars().mapToObj(c->(char) c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));		
		System.out.println(frequency);
		
		List<Integer> listOfIntegers = Arrays.asList(33, 12, 99, 15, 22, 66, 31, 81);
		
		//max
	List<Integer> max=	listOfIntegers.stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
	System.out.println(max);
	
		//min
	List<Integer> min=	listOfIntegers.stream().sorted().limit(3).collect(Collectors.toList());
	System.out.println(min);
	
	//duplicate character
	String strdup="JavaSpring";
	Set<Character> ch=new HashSet<>();
	
	strdup.chars().mapToObj(c->(char) c).map(n->!ch.add(n)).forEach(System.out::print);
	
	//String strdup = "JavaSpring";

	strdup.chars()
	        .mapToObj(c -> (char) c)
	        .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
	        .entrySet()
	        .stream()
	        .filter(e -> e.getValue() > 1)
	        .forEach(e -> System.out.println(e.getKey()));
	
	//Arrays.stream(strdup.split("")).distinct().collect(Collectors.toList()).forEach(System.out::println);

		}*/
			
// Mphasis 2 L1
			class Employees {
				
				String name;
				int salary;
				public String getName() {
					return name;
				}
				public void setName(String name) {
					this.name = name;
				}
				public int getSalary() {
					return salary;
				}
				public void setSalary(int salary) {
					this.salary = salary;
				}
				

			}
			
			// TODO Auto-generated method stub
			Employees emp1=new Employees();
			emp1.setName("saravanan");
			emp1.setSalary(3000);
			Employees emp2=new Employees();
			emp1.setName("saravanan2");
			emp1.setSalary(6000);
			
			List<Employees> emplist=new ArrayList<Employees>();
			emplist.add(emp1);
			emplist.add(emp2);
			
			/*Map<String,Employees> returnlmap=	emplist.stream().collect(Collectors.groupingBy(Employees::getName,Collectors.maxBy(Comparator.comparingDouble(Employees::getSalary)),Optional::get));
			System.out.println(returnlmap);*/
			
			//Exception in thread "main" java.lang.NullPointerException: element cannot be mapped to a null key
			
		class Users{
			int id;
			String name;
			double debit;
		}
//Mphasis 2 -L2			
			//top 3 users  - 30 days - highest total debit - user multiple debits - multilple transaction
			
			List<Users> userlist= new ArrayList<>();
				
		//	userList.stream().filter(e->e.getday()<30).sort(Comparator.reverseOrder()).Collect(Collectors.groupingBy(User::)Comparator.comparingDouble(User::getdebit)).limit(3).collect(Collectors.toList());
		//	userlist.stream().filter(e->e.getdays<30).sorted(Comparator.comparingDouble(User::debit).reversed()).limit(3).collect
			//corrected
		/*	List<User> top3 =
				    userList.stream()
				            .filter(e -> e.getDay() < 30)
				            .sorted(Comparator.comparingDouble(User::getDebit).reversed())
				            .limit(3)
				            .collect(Collectors.toList());
			*/
			
			
			/*Problems:
				.stream() used twice
				.sort() is wrong → should be .sorted()
				Comparator.reverseOrder() won’t work for Objects (User)
				GroupingBy syntax is broken
				Unclear grouping key
				Limit must be placed after sorting
				Collectors spelled incorrectly

				Missing method references*/
			//task management- api - auth access -security -implement - save to db -task have sub task - both (sub and both)- Prod ready code 
			
			// Pojo -> Controller -Service /Serviceimplementation //Repository 
			
//xforia
			/*List<Integer> l1=Arrays.asList(2,7,4,9,4);
			List<Integer> l2=Arrays.asList(2,6,4,7,4);
			
			List<Integer> finalList=Stream.concat(l1.stream(), l2.stream()).sorted().collect(Collectors.toList());
			System.out.println(finalList);*/
			
//Global 
			 /*List<Employee1> empList = new ArrayList<>();
			 Map<String, List<Employee1>> empList1 =		empList.stream().collect(Collectors.groupingBy(e->e.getAge()>25 ? "Age>25":"Age<25"));
			 System.out.println(empList1);
			 Map<Boolean, List<Employee1>> empList2= empList.stream().collect(Collectors.partitioningBy((e->e.getAge()>25)));
			 System.out.println(empList2);
			// empList.stream().filter(e->e.getAge()>25).collect(Collectors.toList());
*/		
			
			//Altimatrik L1-3-12-2025		
			//abc.text file
			
			//parallel - file count most frequest word used 
			List<String> freq=Arrays.asList("a","b","a","a","b","c","d");
			
	Map.Entry<String,Long>	fremap=	freq.parallelStream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream().max(Entry.comparingByValue()).orElse(null);
		System.out.println(fremap);
			
		}
	//GLobal Logic L1
	/* List<Employee1> empList = new ArrayList<>();
	 Map<String, List<Employee1>> empList1 =		empList.stream().collect(Collectors.groupingBy(e->e.getAge()>25 ? "Age>25":"Age<25"));
	 System.out.println(empList1);
	 Map<Boolean, List<Employee1>> empList2= empList.stream().collect(Collectors.partitioningBy((e->e.getAge()>25)));
	 System.out.println(empList2);
	 empList.stream().filter(e->e.getAge()>25).collect(Collectors.toList());*/
	
	
	//Global Logic L2
	/*List<Employee2> employeeslist = Arrays.asList(
		    new Employee2(1,"Arun", "IT", 90000.0),
		    new Employee2(2,"Beena", "IT", 110000.0),
		    new Employee2(3,"Charles", "Finance", 140000.0),
		    new Employee2(4,"Divya", "Finance", 120000.0),
		    new Employee2(5,"Eshan", "HR", 50000.0),
		    new Employee2(6,"Farah", "HR", 75000.0)
		);
//highest average salary	
	
Map.Entry<String,Double> mapList=employeeslist.stream().collect(Collectors.groupingBy(Employee2::getDepartment,
	Collectors.averagingDouble(Employee2::getSalary))).entrySet().stream().max(Entry.comparingByValue()).orElse(null);
System.out.println(mapList);*/
	//Comparator. compare value , Collectors - fin
        
	}


