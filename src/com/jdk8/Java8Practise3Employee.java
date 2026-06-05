package com.jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.omg.CORBA.OMGVMCID;

import com.constant.Employee;

public class Java8Practise3Employee {
	
	//31.8.2025 recal- Not remember 
	
	public static void main(String[] args) {
		
		List<Employee> employees = Arrays.asList(
			    new Employee(1, "Alice", "HR", 5000, Arrays.asList("Communication", "Recruitment")),
			    new Employee(2, "Bob", "IT", 7000, Arrays.asList("Java", "Spring", "AWS")),
			    new Employee(3, "Charlie", "IT", 8000, Arrays.asList("Java", "Docker")),
			    new Employee(4, "Diana", "Finance", 6000, Arrays.asList("Excel", "Accounting")),
			    new Employee(5, "Eve", "IT", 7500, Arrays.asList("Java", "Spring", "Kubernetes")));
		
		//10.9.2025 - 7AM 
	
		//How do you fetch the list of all employee names using streams? 
		/*employees.stream().map(emp->emp.getName()).collect(Collectors.toList()).forEach(e->System.out.println(e));*/
		
		//How do you get all employees from the IT department? 
	
		/*employees.stream().filter(e->e.getDepartment().equals("IT")).map(emp->emp.getName()).collect(Collectors.toList()).forEach(e->System.out.println(e));*/
		
		
		//How do you find the employee with the maximum salary? 
		/*employees.stream().max(Comparator.comparingDouble(Employee::getSalary));*/
		
		
		//How do you calculate the average salary of all employees? 
		
		///employees.stream().max(Comparator.a(Employee::getSalary));
		
		//How do you group employees by department? 
		//11.9.2025
		//Map<String,List<Employee>> listemp=
		/*Map<String,List<Employee>> listemp= employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
		listemp.forEach((dep,emp)-> {
		System.out.println(dep+"->"+ emp.stream().map(Employee::getName).collect(Collectors.toList()));	
		});*/
		
		//.toList() — that method was introduced in Java 16.If you’re on Java 8 / 11 (common in Spring Boot projects), .toList() does not exist on streams.You need to use Collectors.toList() instead.
		 
	
		
		//How do you calculate the average salary per department? 
		
		//How do you count the number of employees in each department? 
		/*Map<String,Long> empcount= employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
		empcount.forEach((dep,emp)-> {
		System.out.println(dep+"->"+ emp);	
		});*/
		
		//How do you fetch the list of all unique skills across employees? 
		
		// wrong employees.stream().map(emp->emp.getSkills()).collect(Collectors.toList()).forEach(e->System.out.println(e));
		
		/* employees.stream().flatMap(emp->emp.getSkills().stream()).collect(Collectors.toSet());*/
		
		//How do you find all employees who have Java skill? 
		
		/*List <String> emplist=employees.stream().filter(e->e.getSkills().contains("Java")).map(emp->emp.getName()).collect(Collectors.toList());
		emplist.stream().forEach(e->System.out.println(e));*/
		
		//Note **Dont Use equals -> use contains 
		
		//How do you sort employees by salary in descending order? 
		
	/*List<Employee>	emplist2 =employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).collect(Collectors.toList());
	emplist2.forEach(emp->System.out.println(emp.getName()));*/
	
		//How do you find the second highest salary employee? 
		
	Employee emp=	employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).skip(1).findFirst().get();
		System.out.println(emp.getName());
		//How do you partition employees based on salary > 6000 and <= 6000? 
		//How do you fetch the top 3 highest-paid employees? 
		//How do you find the employee with the lowest salary in each department? 
		//How do you check if all employees belong to IT department? 
		//How do you check if any employee has Kubernetes skill? 
		//How do you get a comma-separated string of all employee names? 
		//How do you find the department with the highest average salary? 
		//How do you get the distinct list of departments? 
		//How do you map employee names to their list of skills using streams?
		
		
		
		//******************************************************************//
		//How would you group employees by department and list the employee names for each department?
		
			
				
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		//How can you find the highest-paid employee in each department?
		
		

		//How can you identify the most common skill(s) among all employees based on frequency?

		//How do you calculate the average salary for each department?


		//How would you find employees who possess both “Java” and “Spring” skills?
		
		//How can you generate a comma-separated string of employee names who earn more than 6000?

		//How would you map each skill to a list of employee names who have that skill?


		//How do you determine which department has the highest total salary budget? Explain in detail.


		
	}

}
