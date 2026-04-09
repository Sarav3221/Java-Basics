package com.jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.RowFilter.Entry;

public class InterviewSample {

	public static void main(String[] args) {
		
		List<Employee2> employeeslist = Arrays.asList(
				new Employee2(3,"Charles", "Finance", 140000.0),
			    new Employee2(1,"Arun", "IT", 90000.0),
			    new Employee2(2,"Beena", "IT", 110000.0),
			    
			    new Employee2(4,"Divya", "Finance", 120000.0),
			    new Employee2(5,"Eshan", "HR", 50000.0),
			    new Employee2(6,"Farah", "HR", 75000.0)
			);	
		
//cust sorted by name and map id-name
	Map<Integer,String> resultIdName	=employeeslist.stream().sorted(Comparator.comparing(Employee2::getName)).collect(Collectors.toMap(Employee2::getId, Employee2::getName,(a,b)->a,LinkedHashMap::new));
System.out.println("resultIdName "+resultIdName);
/*Because after sorting, we want the final map to maintain sorted order.
A normal HashMap will lose the order.

🔍 Explanation
Step	Operation
1	Stream the list
2	Sort by name
3	Convert to Map<Id, Name>
4	Use merge function (a,b)->a to avoid key conflicts - If a duplicate key comes, keep the first value (a) and ignore the new value (b).
5	Use LinkedHashMap to preserve order Default is HashMap, but HashMap does NOT maintain order Store results in LinkedHashMap to maintain sorted order.*/

	//part 8min		
		List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50,55,23,11);
		Map<Boolean, List<Integer>> part=numbers.stream().collect(Collectors.partitioningBy(e->e>30));
		System.out.println("part"+part);
	Map<Object, List<Integer>> partList=	numbers.stream().collect(Collectors.groupingBy(e->e%2==0?"odd":"even"));
	System.out.println("partList"+partList);
	
		//employeeslist.stream().collect(Collectors.partitioningBy(e->e.))
	
	
		//12:15Pm - 1 hours- 1:18pm
		//Choose strategy - optimised way 
		//When to use Which method
		//Comparator-When to use Comparator  - reveresed in comparator 
		//Collectors When to use collectors 
		//How do you find the employee with the maximum salary? 
//Type mismatch: cannot convert from Optional<Employee2> to Map<Employee,Long>	,	- wrong not reverse order done,
Employee2 empmax=	employeeslist.stream().sorted(Comparator.comparing(Employee2::getSalary).reversed()).findFirst().orElse(null);
//Optimised code -o(n)
String maxemp=employeeslist.stream().max(Comparator.comparing(Employee2::getSalary)).map(e->e.getName()+" "+e.getSalary()).orElse(null);
System.out.println("maxemp "+maxemp);

//3 min
double sal=employeeslist.stream().min(Comparator.comparing(Employee2::getSalary)).get().getSalary();

System.out.println("sal"+sal);
//.entrySet().stream().min(java.util.Map.Entry.comparingByValue());
//how to remove optional(employee) -10min
Map<String, Employee2> minEmp=employeeslist.stream().collect(Collectors.groupingBy(Employee2::getDepartment,Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingDouble(Employee2::getSalary)), Optional::get)));
System.out.println("minEmp"+minEmp);

//ma- 5 mins
Map<String,Employee2> maxemp1=employeeslist.stream().collect(Collectors.groupingBy(Employee2::getDepartment,Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee2::getSalary)),Optional::get)));
System.out.println("maxemp1"+maxemp1);

//max average sal by dep 5
Map.Entry<String,Double> empAvg=employeeslist.stream().collect(Collectors.groupingBy(Employee2::getDepartment,Collectors.averagingDouble(Employee2::getSalary))).entrySet().stream().max(java.util.Map.Entry.comparingByValue()).orElse(null);
System.out.println("maxAvg sal "+empAvg);

//partition 
//employeeslist.stream().collect(Collectors.partitioningBy(e->e.))
//no need to compara for average
//2:15pm- 5 3:38 hours 
//How do you calculate the average salary of all employees? 
 Double avgSal=employeeslist.stream().collect(Collectors.averagingDouble(Employee2::getSalary));
System.out.println("avgSal "+avgSal);
//How do you group employees by department? -ok
Map<String, List<Employee2>>  grpde=employeeslist.stream().collect(Collectors.groupingBy(Employee2::getDepartment));
System.out.println("grpde "+grpde.toString());
//How do you calculate the average salary per department? 
Map<String, Double> avgSalDep=employeeslist.stream().collect(Collectors.groupingBy(Employee2::getDepartment,Collectors.averagingDouble(Employee2::getSalary)));
System.out.println("avgSalDep "+avgSalDep);
//How do you count the number of employees in each department? 
Map<String, Long> countEmpDep= employeeslist.stream().collect(Collectors.groupingBy(Employee2::getDepartment,Collectors.counting()));
System.out.println("countEmpDep "+countEmpDep);
//How do you fetch the list of all unique skills across employees? 
List<String> uniqueDep=employeeslist.stream().map(Employee2::getDepartment).distinct().collect(Collectors.toList());
System.out.println("uniqueDep "+uniqueDep);
//How do you find all employees who have Java skill? 
//How do you sort employees by salary in descending order? 
List<Employee2> sortEmp=  employeeslist.stream().sorted(Comparator.comparingDouble(Employee2::getSalary).reversed()).collect(Collectors.toList());
System.out.println("sortEmp "+sortEmp.toString());
//How do you find the second highest salary employee? 
Employee2 secodEMp=employeeslist.stream().sorted(Comparator.comparingDouble(Employee2::getSalary).reversed()).skip(1).findFirst().orElse(null);
System.out.println("secodEMp "+secodEMp.toString());
//How do you partition employees based on salary > 6000 and <= 9000? 
Map<Boolean, List<Employee2>> partitioned =
employeeslist.stream()
             .collect(Collectors.partitioningBy(
                 e -> e.getSalary() > 6000 && e.getSalary() <= 9000
             ));
//employeeslist.stream().collect(Collectors.partitioningBy(e -> e.)
//How do you fetch the top 3 highest-paid employees? 
List<Employee2> top3Sal=employeeslist.stream().sorted(Comparator.comparingDouble(Employee2::getSalary).reversed()).limit(3).collect(Collectors.toList());
System.out.println("top3Sal "+top3Sal);

//How do you find the employee with the lowest salary in each department? 
//min
/*Map<String, String> empLowestSalDept=  employeeslist.stream().collect(Collectors.groupingBy(Employee2::getDepartment,Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingDouble(Employee2::getSalary)),opt->opt.map(e->e.getName()+" "+e.getSalary()).orElse("No Employee"))));
System.out.println("empLowestSalDept"+empLowestSalDept.toString());

//max
Map<String, String> empMaxSalDept=  employeeslist.stream().collect(Collectors.groupingBy(Employee2::getDepartment,Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee2::getSalary)),opt->opt.map(e->e.getName()+" "+e.getSalary()).orElse("No Employee"))));
System.out.println("empMaxSalDept"+empMaxSalDept.toString());

//ave

Map<String, Double> empAvgsalDep=  employeeslist.stream().collect(Collectors.groupingBy(Employee2::getDepartment,Collectors.averagingDouble(Employee2::getSalary)),opt->opt.map(e->e.getName()+" "+e.getSalary()).orElse("No Employee"))));
System.out.println("empAvgsalDep"+empAvgsalDep.toString());*/

//Map<String,Employee2> lowSal=employeeslist.stream().collect(Collectors.groupingBy(Employee2::getDepartment,Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingDouble(Employee2::getSalary), Optional::get))));
//System.out.println("lowSal "+lowSal.toString());
//How do you check if all employees belong to IT department? 
boolean allMatch=employeeslist.stream().allMatch(e->e.getDepartment().equals("IT"));
System.out.println("allMatch "+allMatch);

//How do you check if any employee has Kubernetes skill? 
boolean anymatch=employeeslist.stream().anyMatch(e->e.getDepartment().equals("IT"));
System.out.println("anymatch "+anymatch);

//How do you get a comma-separated string of all employee names? 
String empl=employeeslist.stream().map(String::valueOf).collect(Collectors.joining(","));
System.out.println("empl "+empl.toString());

//How do you find the department with the highest average salary? 
employeeslist.stream().collect(Collectors.groupingBy(Employee2::getDepartment,Collectors.averagingDouble(Employee2::getSalary))).entrySet().stream().max(java.util.Map.Entry.comparingByValue()).orElse(null);
System.out.println();

//How do you get the distinct list of departments? 
List<String> depList=employeeslist.stream().map(e->e.getDepartment()).distinct().collect(Collectors.toList());
System.out.println("depList "+depList);

//How do you map employee names to their list of skills using streams?
List<String> nameSkill=employeeslist.stream().map(e->e.getName()+" "+e.getDepartment()).collect(Collectors.toList());
System.out.println("nameSkill "+nameSkill);




}
}
