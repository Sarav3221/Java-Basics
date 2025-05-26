package com.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Employee {
	
	    private int id;
	    private String name;
	    private String department;
	    private double salary;
	    private List<String> skills;

	    // constructors, getters, setters


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Employee> emplist=Arrays.asList(
			    new Employee(1, "Alice", "HR", 5000, Arrays.asList("Communication", "Recruitment")),
			    new Employee(2, "Bob", "IT", 7000, Arrays.asList("Java", "Spring", "AWS")),
			    new Employee(3, "Charlie", "IT", 8000, Arrays.asList("Java", "Docker")),
			    new Employee(4, "Diana", "Finance", 6000, Arrays.asList("Excel", "Accounting")),
			    new Employee(5, "Eve", "IT", 7000, Arrays.asList("Java", "Spring", "Kubernetes")));
		
	//Group employees by department and list employee names per department.	
		
		//List<String> listSkill=emplist.stream().collect(Collectors.groupingBy());
				
		
	//	Array[] a= new Array[4];

	}

	public Employee(int id, String name, String department, double salary, List<String> skills) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.salary = salary;
		this.skills = skills;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

}
