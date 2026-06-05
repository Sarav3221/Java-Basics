package com.jdk8;

import java.util.Arrays;

public class JavaPractiseArraysAndList2026Recall1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//5-6-2026 12pm	3 min
		//reverse an array 
		/*	int[] reverse= {1,4,3,6,8,7,2};
			
		int left=0;
		int right=reverse.length-1;
		
		while(left<right) {
			int temp=reverse[left];
			reverse[left]=reverse[right];
			reverse[right]=temp;
			
			left++;
			right--;
			
		}
		//How to Print 
		System.out.println(Arrays.toString(reverse));*/
		
		//Move all even numbers to the front and odd numbers to the back -5 mins
		/*		int[] evenoddarr= {1,4,3,6,8,7,2};
				
				int left=0;
				
				for (int i = 0; i < evenoddarr.length-1; i++) {
					if(evenoddarr[i]%2==0) {
						int temp=evenoddarr[i];
						evenoddarr[i]=evenoddarr[left];
						evenoddarr[left]=temp;
						
						left++;
					}
				}
				
				System.out.println(Arrays.toString(evenoddarr));
	}*/
	
	//Move all zeros to the end (keep order of non-zero elements)	2 mins	
/*
			int[] zerotoend= {1,0,2,0,3,4,0};
			
			int left=0;
			
			for (int i = 0; i < zerotoend.length-1; i++) {
				if(zerotoend[i]!=0) {
					int temp=zerotoend[i];
					zerotoend[i]=zerotoend[left];
					zerotoend[left]=temp;
					
					left++;
				}
			}
			
			System.out.println(Arrays.toString(zerotoend));*/
		
		

		
		//Separate negative and positive numbers
		
		/*int[] positivenegative = {-2,4,-1,7,-6,3};
		
		int left=0;
		
		for (int i = 0; i < positivenegative.length; i++) {
			if(positivenegative[i]>=0) {
				int temp=positivenegative[i];
				positivenegative[i]=positivenegative[left];
				positivenegative[left]=temp;
				
				left++;
			}
		}
		System.out.println(Arrays.toString(positivenegative));*/
		
		
	
		//sort array 	
				int[] arr = {-2,4,-1,7,-6,3};	
				
				
				for (int i = 0; i < arr.length-1; i++) {
					if(arr[i]>arr[i+1]) {
						int temp=arr[i];
						arr[i]=arr[i+1];
						arr[i+1]=temp;
					}
				}
				System.out.println(Arrays.toString(arr));
				
		//how to rotate a array 
				
	
				
		
}
}
