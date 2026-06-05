import java.util.Arrays;
import java.util.List;

import com.constant.Employee;

public class JavaPractiseArraysAndList2026Qns {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// ======================
// ARRAY QUESTIONS
		// ======================

		// Reverse an array
		int[] reverse = {1,4,3,6,8,7,2};

		// Find max element
		int[] maxArr = {10,45,23,89,12,67};

		// Find min element
		int[] minArr = {10,45,23,89,12,67};

		// Find second largest element
		int[] secondLargest = {10,45,23,89,12,67};

		// Find duplicate elements
		int[] duplicates = {1,2,3,2,4,5,3,6,1};

		// Remove duplicates
		int[] removeDuplicates = {1,2,3,2,4,5,3,6,1};

		// Move all zeros to end
		int[] zeroToEnd = {1,0,2,0,3,4,0,5};

		// Separate positive and negative numbers
		int[] positiveNegative = {4,-2,7,-8,1,-5,9};

		// Rotate array left by 2 positions
		int[] rotateLeft = {1,2,3,4,5,6,7};

		// Rotate array right by 2 positions
		int[] rotateRight = {1,2,3,4,5,6,7};

		// Check if array is sorted
		int[] sortedCheck = {1,2,3,4,5,6,7};

		// Merge two arrays
		int[] arr1 = {1,3,5,7};
		int[] arr2 = {2,4,6,8};

		// Find missing number (1 to n)
		int[] missingNumber = {1,2,3,4,6,7,8};

		// Find common elements between arrays
		int[] common1 = {1,2,3,4,5};
		int[] common2 = {3,4,5,6,7};

		// Count frequency of elements
		int[] frequency = {1,2,2,3,3,3,4,4,4,4};

		// Sort array without using Arrays.sort()
		int[] sortArray = {7,2,9,1,5,3};

		// Find pair with given sum
		int[] pairSum = {2,7,11,15,3,6};
		int target = 9;
		
		//*********************************************
		
		// ======================
// LIST / COLLECTION QUESTIONS
		// ======================

		// Reverse a List
		List<Integer> reverseList =
		        Arrays.asList(10,20,30,40,50);

		// Remove duplicates from List
		List<Integer> duplicateList =
		        Arrays.asList(1,2,3,2,4,5,3,1);

		// Convert List to Map
		List<String> names =
		        Arrays.asList("John","David","Alex","Steve");

		// Find frequency of elements
		List<Integer> frequencyList =
		        Arrays.asList(1,2,2,3,3,3,4,4,4,4);

/*		// Sort employees by salary/name
		List<Employee> employees = Arrays.asList(
		        new Employee(101,"John",50000,"IT"),
		        new Employee(102,"David",70000,"HR"),
		        new Employee(103,"Alex",60000,"IT"),
		        new Employee(104,"Steve",90000,"Finance")
		);

		// Find 3rd highest salary
		List<Employee> salaryList = Arrays.asList(
		        new Employee(101,"John",50000,"IT"),
		        new Employee(102,"David",70000,"HR"),
		        new Employee(103,"Alex",60000,"IT"),
		        new Employee(104,"Steve",90000,"Finance"),
		        new Employee(105,"Sam",80000,"IT")
		);

		// Group employees by department
		List<Employee> departmentList = Arrays.asList(
		        new Employee(101,"John",50000,"IT"),
		        new Employee(102,"David",70000,"HR"),
		        new Employee(103,"Alex",60000,"IT"),
		        new Employee(104,"Steve",90000,"Finance"),
		        new Employee(105,"Sam",80000,"HR")
		);*/

		// Flatten nested lists
		List<List<Integer>> nestedList = Arrays.asList(
		        Arrays.asList(1,2,3),
		        Arrays.asList(4,5),
		        Arrays.asList(6,7,8)
		);

		// Merge two sorted lists
		List<Integer> sortedList1 =
		        Arrays.asList(1,3,5,7);

		List<Integer> sortedList2 =
		        Arrays.asList(2,4,6,8);
		
		
		// ======================
// STRING QUESTIONS
		// ======================

		// Reverse String
		String reverseString = "JavaProgramming";

		// Check Palindrome
		String palindrome = "madam";

		// Reverse words in sentence
		String reverseWords =
		        "Java Spring Boot Microservices";

		// Remove duplicate characters
		String removeDuplicateChars =
		        "programming";

		// Count vowels and consonants
		String vowelsConsonants =
		        "JavaProgramming";

		// Find first non-repeating character
		String firstNonRepeating =
		        "swiss";

		// Check Anagram
		String str1 = "listen";
		String str2 = "silent";

		// String compression
		String compression =
		        "aaabbccccdd";

		// Rotate String
		String rotateString = "abcdef";

		// Convert uppercase to lowercase manually
		String upperCase = "JAVAINTERVIEW";

		// Check if two strings are rotations
		String rotation1 = "ABCD";
		String rotation2 = "CDAB";

		// Longest substring without repeating characters
		String longestSubstring =
		        "abcabcbb";
		
		
		// ======================
// CHARACTER ARRAY QUESTIONS
		// ======================

		// Reverse character array
		char[] reverseChar =
		        {'J','A','V','A'};

		// Check palindrome
		char[] palindromeChar =
		        {'M','A','D','A','M'};

		// Count vowels/consonants
		char[] vowelsChar =
		        {'J','A','V','A'};

		// Find duplicate characters
		char[] duplicateChar =
		        {'p','r','o','g','r','a','m','m','i','n','g'};

		// Remove duplicate characters
		char[] removeDuplicateChar =
		        {'p','r','o','g','r','a','m','m','i','n','g'};
		
		
		/*class Employee {

		    private int id;
		    private String name;
		    private double salary;
		    private String department;

		    public Employee(int id, String name,
		                    double salary, String department) {
		        this.id = id;
		        this.name = name;
		        this.salary = salary;
		        this.department = department;
		    }

		    public int getId() {
		        return id;
		    }

		    public String getName() {
		        return name;
		    }

		    public double getSalary() {
		        return salary;
		    }

		    public String getDepartment() {
		        return department;
		    }

		    @Override
		    public String toString() {
		        return id + " " + name + " " +
		               salary + " " + department;
		    }*/
		}

	}

