package com.jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.constant.Employee;

public class Java8Practise2 {
	
	//14.6.2025 recal- Not remember 
	public static void main(String[] args) {
		
		List<Employee> employees = Arrays.asList(
			    new Employee(1, "Alice", "HR", 5000, Arrays.asList("Communication", "Recruitment")),
			    new Employee(2, "Bob", "IT", 7000, Arrays.asList("Java", "Spring", "AWS")),
			    new Employee(3, "Charlie", "IT", 8000, Arrays.asList("Java", "Docker")),
			    new Employee(4, "Diana", "Finance", 6000, Arrays.asList("Excel", "Accounting")),
			    new Employee(5, "Eve", "IT", 7000, Arrays.asList("Java", "Spring", "Kubernetes")));
		
		//How would you group employees by department and list the employee names for each department?
		//{} missed 
		/*Map<String, List<String>> emplist=	employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.mapping(Employee::getName,Collectors.toList())));
		
		
		emplist.forEach((dep,emp1)->{
			System.out.println("Department "+ dep);
			System.out.println(emp1);
			//emp1.forEach(emp->System.out.println(emp));
		});*/
				
				
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		//How can you find the highest-paid employee in each department?
		/*Map<String, Optional<Employee>> empSal= employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.maxBy(Comparator.comparing(Employee::getSalary) )));
		empSal.forEach((dep,empopt)->{
			System.out.println("Department"+ dep);
			empopt.ifPresent(emp-> System.out.println(emp.getName()+ "" +emp.getSalary()));
		});*/
		
		//out1
		/*DepartmentFinancesalaryOptional[com.constant.Employee@b4c966a]
				DepartmentHRsalaryOptional[com.constant.Employee@2f4d3709]
				DepartmentITsalaryOptional[com.constant.Employee@4e50df2e]*/

		//15.6.2025 8AM :9AM 1hour 
		//How do you extract a unique, sorted list of all skills across all employees?
		//Not knowing flatmap
		//You're trying to use flatMap() incorrectly here — it expects a stream to be returned, not a System.out.println() call.
		//Problem 1: flatMap(...).forEach(...) needs proper closing of method calls.
		//Problem 2: You're trying to call .forEach(...) inside the flatMap() method — this is incorrect.
		//You're using .distinct() on the Stream<Employee>, but that won’t remove duplicate skills — it only removes duplicate Employee objects, not skills inside their List<String>.
		
		/*List<List<String>> listSkill=employees.stream().distinct().collect(Collectors.mapping(Employee::getSkills, Collectors.toList()));
		listSkill.stream().flatMap((List::stream)).forEach(System.out::println);
*/
		
	//	employees.stream().flatMap(e->e.getSkills().stream()).distinct().sorted().collect(Collectors.toList()).forEach(System.out::println);;

		//How can you identify the most common skill(s) among all employees based on frequency?
//Pending

		//How do you calculate the average salary for each department?
	//	employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary))).forEach((dept,sal)->System.out.println("Depart "+dept+" sal "+sal));


		//How would you find employees who possess both “Java” and “Spring” skills?
		//e.getSkills() returns a List<String>, not a String.
        //== compares object references, not contents — use .contains(...) for checking elements inside a list.
		
		//employees.stream().filter(e->e.getSkills().contains("Java") && e.getSkills().contains("Spring")).map(Employee::getName).forEach(System.out::println);
//9:30
		//How can you generate a comma-separated string of employee names who earn more than 6000?
      System.out.println(employees.stream().filter(emp->emp.getSalary()>6000).map(Employee::getName).collect(Collectors.joining(",")));

		//How would you map each skill to a list of employee names who have that skill?
      //groupingBy doesn’t accept two arguments this way directly unless wrapped in a downstream collector
   //   employees.stream().collect(Collectors.groupingBy(Employee::getSkills),Collectors.mapping(Employee::getName, Collectors.toList()));


		//How do you determine which department has the highest total salary budget? Explain in detail.


		
	}

}
