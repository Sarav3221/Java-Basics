package com.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jdk8.Java8FeaturesDemo.Calculator;

public class Java8FeatureTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//fetch two seperate list out of this emplist based on age>25
		//List 1 = All employees having age greater than 25
		//List2 = All remaining emplyees
//lambda		
Runnable run= new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Run");
				
			}
		};
		run.run();
		
		Runnable run1=()->System.out.println("Run After");
		run1.run();

		//Before
		calculater c=new calculater() {
			
			@Override
			public int operation(int a, int b) {
				// TODO Auto-generated method stub
				return a+b;
			}
		};
		
		Calculator c1=(a,b)->a+b;

	}
	//Functional interface 	
	@FunctionalInterface
	interface calculater{
		int operation(int a,int b);		
	}
	
	interface Vehicle{
		default void start(){
			System.out.println();
		}
		static void info() {
			System.out.println();
		}
	}
	
	
	
	}
