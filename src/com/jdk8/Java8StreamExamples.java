package com.jdk8;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.*;

class Employee { private String name, department; private double salary; private List<String> skills;
    public Employee(String n, String d, double s, List<String> sk){this.name=n;this.department=d;this.salary=s;this.skills=sk;}
    public String getName(){return name;} public String getDepartment(){return department;} public double getSalary(){return salary;} public List<String> getSkills(){return skills;}
    public String toString(){return name+"("+department+", "+salary+")";}
}
public class Java8StreamExamples {
    public static void main(String[] args){
        List<Employee> employees = Arrays.asList(
            new Employee("Alice","IT",7000,Arrays.asList("Java","Spring","Docker")),
            new Employee("Bob","HR",5000,Arrays.asList("Communication","Recruitment")),
            new Employee("Charlie","Finance",6500,Arrays.asList("Excel","Accounting")),
            new Employee("David","IT",8000,Arrays.asList("Java","Spring","Kubernetes")),
            new Employee("Eve","Finance",6000,Arrays.asList("Excel","Finance")),
            new Employee("Frank","IT",4000,Arrays.asList("Python","Flask"))
        );
        
      //22.5.2026   
     Employee thirdSal=   employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary)).skip(2).findFirst().orElse(null);
        System.out.println("thirdSal"+thirdSal);
        
     System.out.println( "group salary Name"+  employees.stream().collect(Collectors.groupingBy(Employee::getSalary,Collectors.mapping(Employee::getDepartment,Collectors.toList()))));
        //16.5.2026
        //18.5.2026
        
        List<String> empName=  employees.stream().map(emp->emp.getName()).collect(Collectors.toList());
        System.out.println("empName"+empName);
        
        employees.stream().filter(e->e.getDepartment().equals("IT")).collect(Collectors.toList());
        
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary))).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getValue).orElse(0.0);
        employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(3).collect(Collectors.toList());
        employees.stream().mapToDouble(Employee::getSalary).max().orElse(0.0);
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
        Map<Boolean, List<Employee>> emp=employees.stream().collect(Collectors.partitioningBy(e->e.getSalary()>6000));
        System.out.println("emp"+emp);
        Map<String, Long> freqskill=employees.stream().flatMap(e->e.getSkills().stream()).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
      String maxfreqskill=  freqskill.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
        System.out.println("maxfreqskill"+maxfreqskill);
        
        
        
        
        
        //11.4.2026. 8:30 AM
        
        List<String> emplList=  employees.stream().map(n->n.getName()).collect(Collectors.toList());
        System.out.println("emplList"+emplList);
        
        List<Employee> empIT=  employees.stream().filter(n->n.getDepartment().equalsIgnoreCase("IT")).collect(Collectors.toList());
        System.out.println("empIT"+empIT);
        
       // How do you find the maximum salary among employees?

        double maxSal=  employees.stream().mapToDouble(Employee::getSalary).max().orElse(0);
        System.out.println("maxSal"+maxSal);
        
        Employee maxsalEmpmax=  employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).orElse(null);
        System.out.println("maxsalEmpmax"+maxsalEmpmax);
        
        Optional<Employee> maxsalEmp=   employees.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
        
        System.out.println("maxsalEmp"+maxsalEmp);
        
        employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary)).collect(Collectors.toList());
        
        //min sal
        
        double minSal=   employees.stream().mapToDouble(Employee::getSalary).min().orElse(0.0);
        System.out.println("minSal"+minSal);
        
        double avgSal= employees.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);
        System.out.println("avgSal"+avgSal);
        
        Map<String, List<Employee>>  groupDep=employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("groupDep"+groupDep);
        Map<String, Long> Deptcountemp=employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
        System.out.println("Deptcountemp"+Deptcountemp);
        
        List<String> empskillList=employees.stream().flatMap(e->e.getSkills().stream()).distinct().collect(Collectors.toList());
        System.out.println("empskillList"+empskillList);
        
        List<Employee> empSaldescord=employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).collect(Collectors.toList());
        System.out.println("empSaldescord"+empSaldescord);
        
        Employee secondhighest =employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst().orElse(null);
        System.out.println("secondhighest"+secondhighest);
        
        Map<Boolean, List<Employee>>  empPart=employees.stream().collect(Collectors.partitioningBy(e->e.getSalary()>6000));
        System.out.println("empPart"+empPart);
        
        List<Employee> empSalTop3 =employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(3).collect(Collectors.toList());
        System.out.println("empSalTop3"+empSalTop3);
        
        Map<String, Double> avgsalDep=employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("avgsalDep"+avgsalDep);
        
        double maxavgsaldep=employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary))).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getValue).orElse(0.0);
        System.out.println("maxavgsaldep"+maxavgsaldep);
        
       //inside partition getsalary not coming automaticatically why  
        
      //  import java.util.*;import java.util.stream.*;class Employee{private String name;private String department;private double salary;private List<String> skills;public Employee(String name,String department,double salary,List<String> skills){this.name=name;this.department=department;this.salary=salary;this.skills=skills;}public String getName(){return name;}public String getDepartment(){return department;}public double getSalary(){return salary;}public List<String> getSkills(){return skills;}public String toString(){return String.format("%s(%s,%.2f,%s)",name,department,salary,skills);}}
      //  public class StreamPracticeCompact{public static void main(String[] args){List<Employee> employees=Arrays.asList(new Employee("Alice","IT",7000,Arrays.asList("Java","Spring")),new Employee("Bob","HR",5000,Arrays.asList("Recruitment","Excel")),new Employee("Charlie","Finance",8000,Arrays.asList("Excel","SQL")),new Employee("David","IT",9000,Arrays.asList("Java","Kubernetes")),new Employee("Eve","Finance",6000,Arrays.asList("SQL","Python")),new Employee("Frank","IT",6500,Arrays.asList("Java","Spring","Docker")));
       /* System.out.println("1. Employee Names: "+employees.stream().map(Employee::getName).collect(Collectors.toList()));
        System.out.println("2. IT Employees: "+employees.stream().filter(e->"IT".equals(e.getDepartment())).collect(Collectors.toList()));
        System.out.println("3. Max salary: "+employees.stream().mapToDouble(Employee::getSalary).max().orElse(0.0));
        System.out.println("3.2 Min salary: "+employees.stream().mapToDouble(Employee::getSalary).min().orElse(0.0));
        System.out.println("4. Average Salary: "+employees.stream().mapToDouble(Employee::getSalary).average().orElse(0.0));
        System.out.println("5. Group by Department: "+employees.stream().collect(Collectors.groupingBy(Employee::getDepartment)));
        System.out.println("6. Employee Count by Department: "+employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting())));
        System.out.println("7. All Unique Skills: "+employees.stream().flatMap(e->e.getSkills().stream()).collect(Collectors.toSet()));
        System.out.println("8. Employees Sorted by Salary: "+employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).collect(Collectors.toList()));
        System.out.println("9. Second Highest Paid Employee: "+employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst().orElse(null));
        System.out.println("10. Partition (Salary > 6000): "+employees.stream().collect(Collectors.partitioningBy(e->e.getSalary()>6000)));
        System.out.println("11. Top 3 Paid Employees: "+employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).limit(3).collect(Collectors.toList()));
        System.out.println("12. Average Salary by Department: "+employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary))));
        Map<String,Double> avgSalaryByDept=employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("13. Department with Highest Avg Salary: "+avgSalaryByDept.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey());
        System.out.println("14. All from IT? "+employees.stream().allMatch(e->"IT".equals(e.getDepartment())));
        System.out.println("15. Any Employee knows Kubernetes? "+employees.stream().anyMatch(e->e.getSkills().contains("Kubernetes")));
        System.out.println("16. All Names Joined: "+employees.stream().map(Employee::getName).collect(Collectors.joining(", ")));
        System.out.println("17. Employee → Skills Map: "+employees.stream().collect(Collectors.toMap(Employee::getName,Employee::getSkills)));
        Map<String,Long> skillFrequency=employees.stream().flatMap(e->e.getSkills().stream()).collect(Collectors.groupingBy(s->s,Collectors.counting()));
        long maxFreq=skillFrequency.values().stream().max(Long::compare).get();
        System.out.println("18. Most Common Skills: "+skillFrequency.entrySet().stream().filter(e->e.getValue()==maxFreq).map(Map.Entry::getKey).collect(Collectors.toList()));
        System.out.println("19. Employees with Java & Spring: "+employees.stream().filter(e->e.getSkills().containsAll(Arrays.asList("Java","Spring"))).collect(Collectors.toList()));
     
        
        List<String> employeeNames =  employees.stream().map(Employee::getName).collect(Collectors.toList());
        System.out.println("1. Employee Names: " + employeeNames);

        List<Employee> itEmployees = employees.stream().filter(e -> "IT".equals(e.getDepartment())).collect(Collectors.toList());
        System.out.println("2. IT Employees: " + itEmployees);

        double maxSalary = employees.stream().mapToDouble(Employee::getSalary).max().orElse(0.0);
        System.out.println("3. Max Salary: " + maxSalary);

        double minSalary = employees.stream().mapToDouble(Employee::getSalary).min().orElse(0.0);
        System.out.println("3.2 Min Salary: " + minSalary);

        double avgSalary = employees.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);
        System.out.println("4. Average Salary: " + avgSalary);

        Map<String, List<Employee>> groupByDept =  employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("5. Group by Department: " + groupByDept);

        Map<String, Long> countByDept =  employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println("6. Employee Count by Department: " + countByDept);

        Set<String> allSkills =employees.stream().flatMap(e -> e.getSkills().stream()).collect(Collectors.toSet());
        System.out.println("7. All Unique Skills: " + allSkills);

        List<Employee> sortedBySalary = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                        .collect(Collectors.toList());
        System.out.println("8. Employees Sorted by Salary: " + sortedBySalary);

        Employee secondHighestPaid =   employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                        .skip(1).findFirst().orElse(null);
        System.out.println("9. Second Highest Paid Employee: " + secondHighestPaid);

        Map<Boolean, List<Employee>> partitionSalary = employees.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 6000));
        System.out.println("10. Partition (Salary > 6000): " + partitionSalary);

        List<Employee> top3Paid =   employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                        .limit(3).collect(Collectors.toList());
        System.out.println("11. Top 3 Paid Employees: " + top3Paid);

        Map<String, Double> avgSalaryByDept1 =employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("12. Average Salary by Department1: " + avgSalaryByDept1);

        String deptWithHighestAvgSalary = avgSalaryByDept.entrySet().stream()
                        .max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println("13. Department with Highest Avg Salary: " + deptWithHighestAvgSalary);

        boolean allFromIT = employees.stream().allMatch(e -> "IT".equals(e.getDepartment()));
        System.out.println("14. All from IT?: " + allFromIT);

        boolean anyKnowsKubernetes = employees.stream().anyMatch(e -> e.getSkills().contains("Kubernetes"));
        System.out.println("15. Any Employee knows Kubernetes?: " + anyKnowsKubernetes);

        String allNamesJoined =  employees.stream().map(Employee::getName).collect(Collectors.joining(", "));
        System.out.println("16. All Names Joined: " + allNamesJoined);

        Map<String, List<String>> employeeSkillMap = employees.stream().collect(Collectors.toMap(Employee::getName, Employee::getSkills));
        System.out.println("17. Employee → Skills Map: " + employeeSkillMap);

        Map<String, Long> skillFrequency1 =employees.stream().flatMap(e -> e.getSkills().stream())
                        .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        long maxFreq1 = skillFrequency1.values().stream().max(Long::compare).get();

        List<String> mostCommonSkills =  skillFrequency.entrySet().stream()
                        .filter(e -> e.getValue() == maxFreq)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
        System.out.println("18. Most Common Skills: " + mostCommonSkills);

        List<Employee> javaSpringEmployees =employees.stream()
                        .filter(e -> e.getSkills().containsAll(Arrays.asList("Java", "Spring")))
                        .collect(Collectors.toList());
        System.out.println("19. Employees with Java & Spring: " + javaSpringEmployees);

        
        
        
        
//9-12-2025        
        Double maxaverage=  employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary))).entrySet().stream().max(Entry.comparingByValue()).get().getValue();
        System.out.println("max"+maxaverage); 
        
        boolean allIT =      employees.stream().allMatch(e->e.getDepartment().equals("IT"));
        System.out.println("allIT"+allIT);
        
        boolean anyKubernetes1 =  employees.stream().anyMatch(e->e.getSkills().contains("Kubernetes"));
        System.out.println("anyKubernetes1"+anyKubernetes1); // getskills list - so contains works not equals
        
        
        
    System.out.println("freq"+ employees.stream().flatMap(e->e.getSkills().stream()).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream().max(Entry.comparingByValue()).get());*/
        
/*    Scenario	What to Use
    Only need number (avg/max/min)	mapToDouble()
    Need full employee (max/min)	Comparator.comparingDouble()
    Need max/min per group	Collectors.minBy / maxBy
    Need average per group	Collectors.averagingDouble*/
        
        
        
        // System.out.println("20. Skill → Employees Map: "+employees.stream().flatMap(e->e.getSkills().stream().map(skill->Map.entry(skill,e.getName()))).collect(Collectors.groupingBy(Map.Entry::getKey,Collectors.mapping(Map.Entry::getValue,Collectors.toList()))));}}

/*        How do you extract and print all employee names using Java Streams?
        		How do you filter employees belonging to a specific department such as "IT"?
        		How do you find the employee with the maximum salary using streams?
        		How do you calculate the average salary of all employees?
        		How do you group employees by department and print the grouped result?
        		How do you count the number of employees in each department using grouping?
        		How do you compute the average salary per department?
        		How do you get all unique skills of all employees using flatMap?
        		How do you print employees who have a specific skill such as "Java"?
        		How do you sort employees by salary in descending order?
        		How do you find the second highest salary employee using streams?
        		How do you partition employees into two groups based on salary being greater than 6000?
        		How do you print the top three highest-paid employees?
        		How do you find the minimum salary employee per department?
        		How do you find the maximum salary employee per department?
        		How do you check if all employees belong to the IT department?
        		How do you check if any employee knows Kubernetes?
        		How do you join all employee names into a comma-separated string?
        		How do you get all distinct department names?
        		How do you find employees who have both Java and Spring skills?
        		How do you calculate the total salary budget per department?
        		How do you find the department with the highest total salary budget?
        		How do you create a map of skill to the list of employee names who know that skill?
        //Max salary ->max methos
        //average,min,
        System.out.println("All employee names: "+employees.stream().map(Employee::getName).collect(Collectors.toList()));
        System.out.println("Employees from IT: "+employees.stream().filter(e->"IT".equals(e.getDepartment())).collect(Collectors.toList()));
        employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).ifPresent(e->System.out.println("Max salary: "+e));
        System.out.println("max salary: "+employees.stream().mapToDouble(Employee::getSalary).max().orElse(0.0));
        System.out.println("min salary: "+employees.stream().mapToDouble(Employee::getSalary).min().orElse(0.0));
        System.out.println("Average salary: "+employees.stream().mapToDouble(Employee::getSalary).average().orElse(0.0));
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment)).forEach((d,l)->System.out.println(d+" -> "+l));
        System.out.println("Count per dept: "+employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting())));
        System.out.println("Avg salary per dept: "+employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary))));
        System.out.println("All unique skills: "+employees.stream().flatMap(e->e.getSkills().stream()).collect(Collectors.toSet()));
        employees.stream().filter(e->e.getSkills().contains("Java")).forEach(e->System.out.println("Knows Java: "+e));
        employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).forEach(System.out::println);
        employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst().ifPresent(e->System.out.println("2nd highest: "+e));
        Map<Boolean,List<Employee>> partitioned = employees.stream().collect(Collectors.partitioningBy(e->e.getSalary()>6000)); System.out.println("Salary>6000: "+partitioned.get(true)); System.out.println("Salary<=6000: "+partitioned.get(false));
        employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(3).forEach(e->System.out.println("Top3: "+e));
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.minBy(Comparator.comparing(Employee::getSalary)))).forEach((d,e)->System.out.println("Min "+d+" -> "+e.get().getName()+"("+e.get().getSalary()+")"));
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.maxBy(Comparator.comparing(Employee::getSalary)))).forEach((d,e)->System.out.println("Max "+d+" -> "+e.get().getName()+"("+e.get().getSalary()+")"));
        System.out.println("All IT? "+employees.stream().allMatch(e->"IT".equals(e.getDepartment())));
        System.out.println("Has K8s? "+employees.stream().anyMatch(e->e.getSkills().contains("Kubernetes")));
        System.out.println("Comma names: "+employees.stream().map(Employee::getName).collect(Collectors.joining(", ")));
        System.out.println("Distinct depts: "+employees.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList()));
        employees.stream().filter(e->e.getSkills().containsAll(Arrays.asList("Java","Spring"))).forEach(e->System.out.println("Java+Spring: "+e));
        Map<String,Double> totalSalaryByDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.summingDouble(Employee::getSalary)));
        String topDept = totalSalaryByDept.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey(); System.out.println("Top budget dept: "+topDept+" -> "+totalSalaryByDept.get(topDept));
   //     System.out.println("Skill to Employees: "+employees.stream().flatMap(e->e.getSkills().stream().map(skill->new AbstractMap.SimpleEntry<>(skill,e.getName()))).collect(Collectors.groupingBy(Map.Entry::getKey,Collectors.mapping(Map.Entry::getValue,Collectors.toList()))));
*/     
     //practise2 - 28.11.2025
     
     employees.stream().map(Employee::getName).collect(Collectors.toList());
     employees.stream().filter(e->e.getDepartment().equals("IT")).collect(Collectors.toList());
     employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).map(Employee::getName).get();
     employees.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);
     employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
     employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
     employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.minBy(Comparator.comparingDouble(Employee::getSalary))));
     employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.minBy(Comparator.comparingDouble(Employee::getSalary))));
    // employees.stream().flatMap(e->e.getSkills().stream()).collect(Collectors.toSet());
    // employees.stream().filter(e->e.getSkills().equals("Java")).collect(Collectors.toList());
     employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).collect(Collectors.toList());     
     employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst();
     
     
  
     
   //map to double -converts a Stream of objects (like Employee) into a DoubleStream (a stream of primitive double values).This wouldn’t compile — because average() doesn’t exist for Stream<Double>.   It only exists for primitive streams.
   //comparing double 
   //(Comparator.comparing(Employee::getSalary)   
   //Comparator.comparingDouble(Employee::getSalary)        
       
        List<Integer> ltr=Arrays.asList(2,6,8,9,5);
        ltr.stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());

    //practise-1
      //  employees.stream().sorted(Comparator.reverseOrder());
        
        employees.stream().map(Employee::getName).collect(Collectors.toList());
       // employees.stream().filter(e->e.getDepartment().equals(""))
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
        employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).collect(Collectors.toList());
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
        employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).orElse(null);
       // employees.stream().
            
     
/*Map<String,Employee>  empsal=   employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.collectingAndThen(Collectors.minBy(Comparator.comparing(Employee::getSalary)), Optional::get)));
     
     System.out.println("empminsal"+empsal);*/
 //retuns optional     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    }
}
