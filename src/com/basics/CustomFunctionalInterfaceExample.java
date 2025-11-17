package com.basics;

public class CustomFunctionalInterfaceExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Using a custom functional interface to perform a custom operation
		MyCustomFunctionalInterface xx=	x->	System.out.println(x*x);
		MyCustomFunctionalInterface2 xx2=	x->	System.out.println(x);
		
		xx.calculateSquare(2);
		
		xx2.calculateSquare(5);
		
		/*MyCustomFunctionalInterface is a functional interface with one abstract method.

		new MyCustomFunctionalInterface() { ... } is an anonymous inner class that provides the method body for calculateSquare(int x).

		You’re creating an object of a class that implements MyCustomFunctionalInterface.*/

}
}


/**


**/