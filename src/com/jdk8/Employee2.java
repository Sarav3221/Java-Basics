package com.jdk8;

public class Employee2 {
	

	    private int id;
	    private String name;
	    private String department;
	    private double salary;
	   

	    public Employee2(int i, String name,  String department,double salary) {
			super();
			this.id = i;
			this.name = name;
			this.salary = salary;
			this.department = department;
		}

		public Employee2() {}

		
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

		public double getSalary() {
			return salary;
		}

		public void setSalary(double salary) {
			this.salary = salary;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		@Override
		public String toString() {
			return "Employee2 [id=" + id + ", name=" + name + ", department=" + department + ", salary=" + salary + "]";
		}

	   
}
