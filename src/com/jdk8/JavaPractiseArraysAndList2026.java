package com.jdk8;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaPractiseArraysAndList2026 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//20-5-2026- 3:50 PM
//reverse an array 
/*		int[] reverse= {1,4,3,6,8,7,2};
		
			int left=0;
			int right=reverse.length-1;
			
			while(left<right) {
				int temp=reverse[left];
				reverse[left]=reverse[right];
				reverse[right]=temp;
				
				left++;
				right--;
				
			}
				for (int i : reverse) {
					System.out.println(i);
				}
		System.out.println(reverse.toString());*/
		
//Move all even numbers to the front and odd numbers to the back
//You are swapping left1 and right1, but you should swap the current element with the left1 position when you find an even number.		
		/*int[] evenoddarr= {1,4,3,6,8,7,2};
		
		int left1=0;
		int right1=evenoddarr.length-1;
		
		for (int i = 0; i < evenoddarr.length; i++) {
			
			if(evenoddarr[i]%2==0) {
				int temp=evenoddarr[i];
				evenoddarr[i]=evenoddarr[left1];
				evenoddarr[left1]=temp;
				left1++;
				
				System.out.println("Even"+Arrays.toString(evenoddarr));
			}
			System.out.println("Add"+Arrays.toString(evenoddarr));
		}*/
		
	
		
//Move all zeros to the end (keep order of non-zero elements)		3 min11sec

	/*	int[] zerotoend= {1,0,2,0,3,4,0};
		
		
		int left=0;
		int right=zerotoend.length-1;
		
		for(int i=0;i<zerotoend.length-1;i++) {
			if(zerotoend[i]!=0) {
				int temp=zerotoend[left];
				zerotoend[left]=zerotoend[i];
				zerotoend[i]=temp;
				
				left++;
			}
		}
		System.out.println(Arrays.toString(zerotoend));*/
		

//Separate negative and positive numbers
/*
		int[] positivenegative = {-2,4,-1,7,-6,3};
		
		
		
		int left=0;
		int right=positivenegative.length-1;
		
		for(int i=0;i<positivenegative.length-1;i++) {
			if(positivenegative[i]<0) {
				int temp=positivenegative[left];
				positivenegative[left]=positivenegative[i];
				positivenegative[i]=temp;
				left++;
			}
		}
		System.out.println(Arrays.toString(positivenegative));
		*/

//21-5-2026
//how to rotate a array 
		
//sort array 	
		/*int[] arr = {-2,4,-1,7,-6,3};
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));*/
		
		// only Integer have Arrays.sort(arr,Collections.reverseOrder());
		
		
/*int[] arr = {-2,4,-1,7,-6,3};
		
		
		
		int left=0;
		int right=arr.length-1;
		
		for(int i=0;i<arr.length-1;i++) {
			if(arr[i+1]<arr[i]) {
				int temp=arr[i];
				arr[i]=arr[i+1];
				arr[i+1]=temp;
				
			}
		}
		
		System.out.println(Arrays.toString(arr));*/
		
//max min		
		
		/*int[] arr = {-2,4,-1,7,-6,3};	
		int max=0;
		for(int i=0;i<arr.length-1;i++) {
			if(arr[i]>max) {
				max=arr[i];
			}
		}
		System.out.println(max);
		*/
		
		
		// Reverse an array

		int[] arr1 = {1, 2, 3, 4, 5};



		// Find max/min element

		int[] arr2 = {12, 45, 7, 89, 23};



		// Find second largest element

		int[] arr3 = {10, 5, 20, 8, 15};



		// Find duplicates in array

		int[] arr4 = {1, 2, 3, 2, 4, 5, 1,5};
		
	/*	Map<Integer,Integer> dupmap=new HashMap<>();
		int count=0;
		
for (int i = 0; i < arr4.length; i++) {
	if(dupmap.containsKey(arr4[i])) {
		dupmap.put(arr4[i], count++);
		
	}
	
}*/

//2nd approach ---------------------------

/*Set<Integer> set= new HashSet<>();
Set<Integer>  duplicate=new HashSet<>();

for (int i : arr4) {
	if(!set.add(i)) {
		duplicate.add(i);
	}
}
System.out.println(duplicate);*/

//

/*if(set.contains(i)) {
	System.out.println(i);
}
else {
	set.add(i);
}
*/

		// Remove duplicates

		int[] arr5 = {1, 2, 2, 3, 4, 4, 5};



		// Move all zeros to end

		int[] arr6 = {0, 1, 0, 3, 12};



		// Separate positive and negative numbers

		int[] arr7 = {-2, 4, -1, 7, -6, 3};



// Rotate array left/right

		/*int[] arr8 = {1, 2, 3, 4, 5};
		int k = 2;*/
		
		// Rotate array right by k positions

	/*	int[] arr8 = {1, 2, 3, 4, 5};
		//op           4 5 1 2 3 
		int k = 2;

		k = k % arr8.length; // handles k > length

		// Reverse full array
		reverse(arr8, 0, arr8.length - 1);

		// Reverse first k elements
		reverse(arr8, 0, k - 1);

		// Reverse remaining elements
		reverse(arr8, k, arr8.length - 1);

		// Output
		for (int num : arr8) {
		    System.out.print(num + " ");
		}
*/
		// Method
		/*public static void reverse(int[] arr, int left, int right) {
		    while (left < right) {
		        int temp = arr[left];
		        arr[left] = arr[right];
		        arr[right] = temp;

		        left++;
		        right--;
		    }
		}*/
		
		//*******************************************

// Rotate array left by k positions
		/*int[] arr8 = {1, 2, 3, 4, 5};
		//o/p         3 4 5 1 2 
        int k = 2;

        k = k % arr8.length;

        // Reverse first k elements
        reverse(arr8, 0, k - 1);

        // Reverse remaining elements
        reverse(arr8, k, arr8.length - 1);

        // Reverse full array
        reverse(arr8, 0, arr8.length - 1);

        // Output
        for (int num : arr8) {
            System.out.print(num + " ");
        }
    }

    public static void reverse(int[] arr, int left, int right) {

        while (left < right) {

            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }*/

// Check if array is sorted

		/*int[] arr9 = {1, 2, 3, 4, 5};
		boolean issorted=true;
		for (int i = 0; i < arr9.length-1; i++) {
			if(arr9[i]>arr9[i+1]) {
				issorted=false;
				break;
			}
		}
		
		if(issorted==true) {
			System.out.println("Its sorted");
		}
		else {
			System.out.println("Its not sorted");
		}*/



// Merge two arrays

		int[] arr10 = {1, 3, 5};
		int[] arr11 = {2, 4, 6};
		
	int[] mergedarray=new int[arr10.length+arr11.length];
	for (int i = 0; i < arr10.length; i++) {
		mergedarray[i]=arr10[i];
	}
	for (int i = 0; i < arr11.length; i++) {
		mergedarray[arr10.length+i]=arr11[i];
	}
	System.out.println(Arrays.toString(mergedarray));



		// Find missing number

		int[] arr12 = {1, 2, 4, 5}; // Missing number = 3
		int n=5;
		int expectedSum=n*(n+1)/2;
		int actualsum=0;
		for (int i = 0; i < arr12.length; i++) {
			actualsum+=arr12[i];
		}
		
		int missingNum=expectedSum-actualsum;
System.out.println("missingNum "+missingNum);


// Find common elements between arrays

		int[] arr13 = {1, 2, 3, 4, 5};
		int[] arr14 = {3, 4, 5, 6, 7};
		
		
Set<Integer>  commonSet= new HashSet<>();

for (Integer integer : arr13) {
	commonSet.add(integer);
}
for (Integer integer : arr14) {
	
	if(commonSet.contains(integer)){
		System.out.println(integer);
	}
	
}


// Count frequency of elements

		int[] arr15 = {1, 2, 2, 3, 1, 4, 2};
		int count=0;
		Map<Integer,Integer> frqmap=new HashMap<>();
		
		for (int i : arr15) {
			if(frqmap.containsKey(i)) {
				frqmap.put(i,frqmap.get(i)+1);
			}
			else {
				frqmap.put(i,1);
			}
		}
		
		for (Map.Entry<Integer,Integer> i : frqmap.entrySet()) {
			System.out.println(i.getKey()+" "+i.getValue());
		}
//how to print 
		



		// Sort array without using built-in sort

		int[] arr16 = {5, 2, 8, 1, 9};
		
		for (int i = 0; i < arr16.length-1; i++) {
			if(arr16[i+1]<arr16[i]) {
				int temp=arr16[i];
				arr16[i]=arr16[i+1];
				arr16[i+1]=temp;
			}
		}
		System.out.println(Arrays.toString(arr16));



		// Find pair with given sum

		int[] arr17 = {2, 7, 11, 15};
		int target = 9;
		
		Set<Integer> si=new HashSet<>();
		for (int i : arr17) {
			int compliment=target-i;
			if(si.contains(compliment)) {
				System.out.println("compliment "+compliment+","+i);
				break;
			}
			si.add(i);
			
		}
		
//***********************************************************************
//22.5.2026		8AM
				// Reverse a List 

				List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
				

				Collections.reverse(list1);
				System.out.println("Reverse"+ list1);

// Remove duplicates from List

				List<Integer> list2 = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
				
				List<Integer>list22=	list2.stream().distinct().collect(Collectors.toList());
				//How to print list
				System.out.println("remove duplicate "+list22.toString());

// Find duplicates from List

				List<Integer> list222 = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
				
				Set<Integer> set2222=new HashSet<>();
				Set<Integer> duplilist=new HashSet<>();
				
				
				
				/*for (Integer integer : list222) {
					if(set2222.contains(integer)) {
						duplilist.add(integer);
					}
					set2222.add(integer);
				}*/
				System.out.println("duplilist"+duplilist);
				
				// Convert List to Map

				List<String> list3 = Arrays.asList("Java", "Spring", "Microservices");
				
				 Map<String, Object> listTomap=	list3.stream().collect(Collectors.toMap(i->i, i->i.length()));
				 System.out.println("listTomap "+listTomap);


				// Find frequency of elements

				List<String> list4 = Arrays.asList("Java", "Python", "Java", "C++", "Python", "Java");
				Map<String, Long>freqel=	list4.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
System.out.println("freqel"+freqel.toString());

/*
				// Sort employees by salary/name

				List<Employee> employees1 = Arrays.asList(
				    new Employee(1, "John", 50000),
				    new Employee(2, "David", 70000),
				    new Employee(3, "Alice", 60000)
				);



				// Find nth highest salary

				List<Employee> employees2 = Arrays.asList(
				    new Employee(1, "John", 50000),
				    new Employee(2, "David", 70000),
				    new Employee(3, "Alice", 60000),
				    new Employee(4, "Sam", 90000)
				);

				int n = 2;



				// Group employees by department

				List<Employee> employees3 = Arrays.asList(
				    new Employee(1, "John", "IT"),
				    new Employee(2, "David", "HR"),
				    new Employee(3, "Alice", "IT"),
				    new Employee(4, "Sam", "Finance")
				);
*/


				// Flatten nested lists

				List<List<Integer>> nestedList = Arrays.asList(
				    Arrays.asList(1, 2),
				    Arrays.asList(3, 4),
				    Arrays.asList(5, 6)
				);



				// Merge two sorted lists

				List<Integer> sortedList1 = Arrays.asList(1, 3, 5, 7);
				List<Integer> sortedList2 = Arrays.asList(2, 4, 6, 8);

				 List<Integer> concat=	Stream.concat(sortedList1.stream(), sortedList2.stream()).collect(Collectors.toList());
				 System.out.println("concat"+concat.toString());

				// Detect cycle in linked list

				/*ListNode head = new ListNode(1);
				head.next = new ListNode(2);
				head.next.next = new ListNode(3);
				head.next.next.next = head; // cycle created
				```
*/
		
		
		
		
	}
	
	public static void reverse(int[] arr, int left, int right) {
	    while (left < right) {
	        int temp = arr[left];
	        arr[left] = arr[right];
	        arr[right] = temp;

	        left++;
	        right--;
	    }
	}
}
