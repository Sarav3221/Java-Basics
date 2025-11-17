package com.basics;

public class StringImmutableExample {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = s1;

        s1 = s1.concat(" World");

        System.out.println("s1: " + s1); // Hello World
        System.out.println("s2: " + s2); // Hello
        
        
        String s3 = new String ("Hello");
        s3=s3.concat(" World");
        System.out.println("s3: " + s3);
        
        System.out.println(s1==s3);
        System.out.println(s1.equals(s3));
        
        
        
        
    }
}