package com.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeePartitioningExample {
	
	public static void main(String[] args) {
        List<Employee3> employees = Arrays.asList(
                new Employee3("Alice", 60000),
                new Employee3("Bob", 45000),
                new Employee3("Charlie", 75000),
                new Employee3("David", 30000),
                new Employee3("Eve", 50000)
        );

        // Partition Employees based on salary > 50000
        Map<Boolean, List<Employee3>> partitionedBySalary = employees.stream()
                .collect(Collectors.partitioningBy(Employee3 -> Employee3.getSalary() > 50000));
        
     Map<Boolean,List<Employee3>> partSal=   employees.stream().collect(Collectors.partitioningBy(emp->emp.getSalary()>50000));
    
     System.out.println("Employees with >50000");
     partSal.get(true).forEach(System.out::println);
     
     System.out.println("Employees <=50000");
     partSal.get(false).forEach(System.out::println);
     
     System.out.println("partSal"+partSal);

        System.out.println("Employees with salary > 50000:");
        partitionedBySalary.get(true).forEach(System.out::println);

        System.out.println("\nEmployees with salary <= 50000:");
        partitionedBySalary.get(false).forEach(System.out::println);
    }

}
