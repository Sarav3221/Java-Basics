package com.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.constant.Employee;

public class Java8Practise {
	//1
	//2 Group employees by department and list employee names per department.	
//21-4-2025
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//collect(Collectors.toList());
		//.forEach(System.out::println)
		
		//List<Integer> ls1 = Arrays.asList(4,6,78,2,100);
		//How To sort ascending ,decending
		//	List a= ls1.stream().forEach(e->e.);
		
//1-5-2025
		//
		//1. Return value >50 	
		//List<Integer> fin=
		// ls1.stream().filter(e->e>50).forEach(System.out::println);
		 
		 //List<Integer> ls2=ls1.stream().filter(e->e>50).collect(Collectors.toList());
		 
		 List<Integer> ls2 = Arrays.asList(10,15,8,49,25,98,32);
		 
		// ls2.stream().filter(num-> num%2==0).forEach(System.out::println); 
		 
		 int[] arr = {10,15,8,49,25,98,32};
//https://blog.devgenius.io/java-8-coding-and-programming-interview-questions-and-answers-62512c44f062		 
		 //Map<Boolean,List<Integer>> list= Arrays.stream(arr).boxed().collect(Collectors.partitioningBy(num->num%2==0));
		 
		// System.out.println(list);
		 
		 //2. Given a list of integers, find out all the numbers starting with 1 using Stream functions?
		// List<Integer> myList = Arrays.asList(10,15,8,49,25,98,32);
		// myList.stream().map(s->s+"").filter(s->s.startsWith("1")).forEach(System.out::println);
		 
		// 3. How to find duplicate elements in a given integers list in java using Stream functions?
		 List<Integer> myList2 = Arrays.asList(10,15,8,10,25,98,15);
		// myList2.stream().distinct().forEach(System.out::println);
		 
		// myList2.stream().filter(n->set.add(n)).forEach(System.out::println);
		 
		 
		 
		// myList2.stream().findFirst().ifPresent(System.out::println);
		 
		Long l1= myList2.stream().count();
		//System.out.println(l1);
		 
		
		//System.out.println(myList2.stream().findFirst().get());
		
		//System.out.println(myList2.stream().max(Comparator.naturalOrder());
		

		Integer y=	myList2.stream().max(Integer::compare).get();
	//	System.out.println(i);
	 
	 
		String input = "Java articles are Awesome";
	 
		//Character c= input.chars().
		//3-5-2025		 
		
	//	myList2.stream().sorted().forEach(System.out::println);
		
		//myList2.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);
		
		
		List<String> list1 = Arrays.asList("Java", "8");
        List<String> list2 = Arrays.asList("explained", "through", "programs");
		
		
       // Stream<String> str=
		
		// Stream.concat(list1.stream(),list2.stream()).forEach(System.out::println);;
		
		
		// myList2.stream().map(x->x*x).filter(j->j>500).forEach(System.out::println);
		 
	 
		/* int arr1[] = { 99, 55, 203, 99, 4, 91 };
		 Integer arr2[] = { 99, 55, 203, 99, 4, 91 };
		 
		 Arrays.sort(arr1);
		 
		 for(int i=0;i<arr1.length/2;i++) {
			 int temp=arr1[i];
			 arr1[i]=arr1[arr1.length-1-i];
			 arr1[arr1.length-1-i]=temp;
			 
			
		 }*/
		// System.out.println(Arrays.toString(arr1));
		// Arrays.sort(arr2, Collections.reverseOrder());
	//	 Arrays.stream(arr1).forEach(System.out::println);
		 
		 
		// Map<Integer,Long> list2=myList2.stream().collect(Collectors.groupingBy(n->n,Collectors.counting()));
		 
//********************

			
//10:30Am
//3-5-2025 12:30PM 
	List<Employee> employees = Arrays.asList(
			    new Employee(1, "Alice", "HR", 5000, Arrays.asList("Communication", "Recruitment")),
			    new Employee(2, "Bob", "IT", 7000, Arrays.asList("Java", "Spring", "AWS")),
			    new Employee(3, "Charlie", "IT", 8000, Arrays.asList("Java", "Docker")),
			    new Employee(4, "Diana", "Finance", 6000, Arrays.asList("Excel", "Accounting")),
			    new Employee(5, "Eve", "IT", 7000, Arrays.asList("Java", "Spring", "Kubernetes")));

		// 1. Group employees by department and list employee names per department.
	
	/*Map<String,List<String>> grpEmp=employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
			Collectors.mapping(Employee::getName, Collectors.toList())));
	
	
	grpEmp.forEach((dep,names)->
	{
		System.out.println(dep);
		
		names.forEach(name->System.out.println(" "+name));
	});*/

		/// 2. Find the highest-paid employee per department.
	
	//employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.maxBy(Employee::getSalary), ))
	 
	/*Map<String,Optional<Employee>> paiddep = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
	
	paiddep.forEach((dep,emp)->{
		System.out.println(dep);
		System.out.println(emp.get().getName());
	});*/
	//employees.stream().collect(Collectors.averagingDouble(Employee:: getDepartment,Employee:: getSalary));

		// 3. Get a unique, sorted list of all skills across all employees.
	
	
	// Map<List<String>,List<Employee>> skilllist=employees.stream().distinct().sorted().collect(Collectors.groupingBy(Employee::getSkills));
/*List<String> skillList=	employees.stream().flatMap(e->e.getSkills().stream()).distinct().sorted().collect(Collectors.toList());
	
skillList.forEach((skils)->{
		System.out.println(skils);
	});*/

// 4. Find the most common skill(s) among employees (by frequency).//1 hour to solve even after copy 	

	//You're very close, but the issue is that you're trying to call .collect(Collectors.toList()) inside the flatMap(...), which is incorrect. flatMap expects a Stream, not a List.
       /*List<String>  allskill=employees.stream().flatMap(e->e.getSkills().stream()).collect(Collectors.toList());
              allskill.forEach(skill->System.out.println(skill));*/
//The result of Collectors.groupingBy(..., Collectors.counting()) is a Map, not a List<String>.	
	/*Map<String,Long>  allskill=employees.stream().flatMap(e->e.getSkills().stream()).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
  
	Long maxfreq=allskill.values().stream().max(Long::compare).orElse(0L);
	System.out.println(maxfreq);
	
List<String> mostcommonskillS=	allskill.entrySet().stream().filter(entry->entry.getValue()==maxfreq).map(Map.Entry::getKey).collect(Collectors.toList());
mostcommonskillS.forEach(System.out::println);*/
	//allskill.forEach((skill,skillcount)->System.out.println(skill+ " " +skillcount));

    /*Stream.of("Java", "Spring", "Java")
    .collect(Collectors.groupingBy(Function.identity())).forEach((skill,count)-> System.err.println(skill+ " "+ count));*/

		

// 5. Compute the average salary per department.- 5 min
//12:11pm
	//Syntax error, insert "Dimensions" to complete ReferenceType
/*Map<String,Double> avgsal=	employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
avgsal.forEach((dep,sal)-> System.out.println(dep+" "+ sal));;*/

		// 6. List names of employees who have both “Java” and “Spring” skills. //12:25pm - 12 mins
	
	//employees.stream().filter(skill-> skill.getSkills().contains("Java") && skill.getSkills().contains("Spring")).map(Employee::getName).forEach(System.out::println);
	//Cannot infer type argument(s) for <R> map(Function<? super T,? extends R>)
	//Comparing a List<String> to a String with .equals() will always return false
	
	
	// 7. Build a comma-separated string of all employee names who earn more than 6000. - 30 sec- wrong -10 min
	
System.out.println(employees.stream().filter(sal-> sal.getSalary()>6000).map(Employee::getName).collect(Collectors.joining(",")));	

		// 8. Create a map of skill to list of employee names having that skill.

		// 9. Find the department with the highest total salary budget.

	}
	


	
	/*// Wipro Interview 
	//program to print Max/Min employee salary from the given collection
			Optional<Employee> higestEmployee = employeeList.stream()
			.collect(Collectors.maxBy(Comparator.
					comparingDouble(Employee::getSalary)));
			System.out.println(higestEmployee);
			
			Optional<Employee> lowestEmployee = employeeList.stream()
					.collect(Collectors.minBy(Comparator.
							comparingDouble(Employee::getSalary)));
					System.out.println(lowestEmployee);
					System.out.println("=============================");
			//program to print the max salary of an employee from each department
					Map<String, Optional<Employee>> output = employeeList.stream()
					.collect(Collectors.groupingBy(Employee::getDepartment,
							Collectors.reducing(BinaryOperator.maxBy(Comparator.
									comparing(Employee::getSalary)))));
					output.forEach((key,value)-> System.out.println(key + value));
					System.out.println("=============================");
			//program to print active and inactive employees in the given collection
					Map<String, Long> activeInactiveCount = employeeList.stream()
					.collect(Collectors.
							groupingBy(Employee::getActive,Collectors.counting()));
					activeInactiveCount.forEach((key,value)-> System.out.println(key + value));
			//program to print employee details working in each department
					System.out.println("=============================");
					Map<String, List<Employee>> empDept = employeeList.stream()
							.collect(Collectors.groupingBy(Employee::getDepartment));
					empDept.forEach((key,value)-> System.out.println(key + value));
			//program to print employees count working in each department
					System.out.println("=============================");
							Map<String, Long> empDeptCOunt = employeeList.stream()
									.collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
									empDeptCOunt.forEach((key,value)-> System.out.println(key + value));*/
	
	

} 

