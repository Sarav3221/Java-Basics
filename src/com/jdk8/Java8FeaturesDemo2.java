package com.jdk8;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.concurrent.*;

public class Java8FeaturesDemo2 {

    public static void main(String[] args) {

        System.out.println("\n======= JAVA 8 FEATURES — AUTO DEMO START =======\n");

        demoLambda();
        divider();

        demoFunctionalInterface();
        divider();

        demoDefaultMethods();
        divider();

        demoMethodReference();
        divider();

        demoStreamAPI();
        divider();

        demoOptional();
        divider();

        demoDateAPI();
        divider();

        demoCompletableFuture();
        divider();

        System.out.println("\n======= JAVA 8 FEATURES — AUTO DEMO END =======");
    }

    static void divider() {
        System.out.println("\n--------------------------------------------------\n");
    }

    // ---------------------------------------------
    // 1) LAMBDA EXPRESSIONS
    // ---------------------------------------------
    
    
    	
    	
    static void demoLambda() {
        System.out.println("1) LAMBDA EXPRESSIONS (Before vs After)\n");

        System.out.println("*** BEFORE JAVA 8 ***");
        Runnable r1 = new Runnable() { 
            @Override
            public void run() {
                System.out.println("Running using old anonymous class.");
            }
        };
        r1.run();

        System.out.println("\n*** AFTER JAVA 8 (Lambda) ***");
        Runnable r2 = () -> System.out.println("Running using lambda.");
        r2.run();
    }

    // ---------------------------------------------
    // 2) FUNCTIONAL INTERFACE
    // ---------------------------------------------
    @FunctionalInterface
    interface Calculator {
        int operation(int a, int b);
    }

    static void demoFunctionalInterface() {
        System.out.println("2) FUNCTIONAL INTERFACE (Before vs After)\n");

        System.out.println("*** BEFORE JAVA 8 ***");
        Calculator c1 = new Calculator() {
            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        };
        System.out.println("Old Add = " + c1.operation(10, 20));

        System.out.println("\n*** AFTER JAVA 8 (Lambda) ***");
        Calculator c2 = (a, b) -> a + b;
        System.out.println("Lambda Add = " + c2.operation(10, 20));
    }

    // ---------------------------------------------
    // 3) DEFAULT & STATIC METHODS
    // ---------------------------------------------
    interface Vehicle {
        void drive();

        default void start() {
            System.out.println("Default start() - Java 8");
        }

        static void info() {
            System.out.println("Static info() method in interface");
        }
    }

    static void demoDefaultMethods() {
        System.out.println("3) DEFAULT & STATIC METHODS IN INTERFACE\n");

        Vehicle car = () -> System.out.println("Driving car with lambda...");

        car.drive();  
        car.start();  
        Vehicle.info(); 
    }

    // ---------------------------------------------
    // 4) METHOD REFERENCE
    // ---------------------------------------------
    static void demoMethodReference() {
        System.out.println("4) METHOD REFERENCE (::)\n");

        System.out.println("*** BEFORE JAVA 8 ***");
        Consumer<String> c1 = new Consumer<String>() {
            @Override
            public void accept(String t) {
                System.out.println("Old way: " + t);
            }
        };
        c1.accept("Hello");

        System.out.println("\n*** AFTER JAVA 8 (Method Reference) ***");
        Consumer<String> c2 = System.out::println;  
        c2.accept("Hello from method reference");
    }

    // ---------------------------------------------
    // 5) STREAM API
    // ---------------------------------------------
    static void demoStreamAPI() {
        System.out.println("5) STREAM API (Before vs After)\n");

        List<Integer> nums = Arrays.asList(1,2,3,4,5,6);

        System.out.println("*** BEFORE JAVA 8 ***");
        List<Integer> even = new ArrayList<>();
        for (Integer n : nums) {
            if (n % 2 == 0) even.add(n);
        }
        System.out.println("Even numbers: " + even);

        System.out.println("\n*** AFTER JAVA 8 (Stream API) ***");
        List<Integer> result = nums.stream()
                                   .filter(n -> n % 2 == 0)
                                   .map(n -> n * 10)
                                   .collect(Collectors.toList());
        System.out.println("Even × 10 using stream: " + result);
    }

    // ---------------------------------------------
    // 6) OPTIONAL
    // ---------------------------------------------
    static void demoOptional() {
        System.out.println("6) OPTIONAL (Before vs After)\n");

        System.out.println("*** BEFORE JAVA 8 ***");
        String s = null;
        if (s != null) System.out.println(s.toUpperCase());
        else System.out.println("Value is null");

        System.out.println("\n*** AFTER JAVA 8 (Optional) ***");
        Optional<String> opt = Optional.ofNullable(s);
        System.out.println(opt.orElse("Default value"));
    }

    // ---------------------------------------------
    // 7) DATE / TIME API
    // ---------------------------------------------
    static void demoDateAPI() {
        System.out.println("7) NEW DATE/TIME API\n");

        System.out.println("*** BEFORE JAVA 8 ***");
        Date d = new Date();
        System.out.println("Old Date = " + d);

        System.out.println("\n*** AFTER JAVA 8 (java.time API) ***");
        System.out.println("LocalDate = " + java.time.LocalDate.now());
        System.out.println("LocalTime = "+java.time.LocalTime.now());
        System.out.println("LocalDateTime = " + java.time.LocalDateTime.now());
    }

    // ---------------------------------------------
    // 8) COMPLETABLE FUTURE
    // ---------------------------------------------
    static void demoCompletableFuture() {
        System.out.println("8) COMPLETABLE FUTURE\n");

        CompletableFuture.supplyAsync(() -> {
            sleep();
            return 10;
        })
        .thenApply(n -> n * 2)
        .thenAccept(res -> System.out.println("Result = " + res))
        .join();
    }

    static void sleep() {
        try { Thread.sleep(400); } catch (Exception e) {}
    }
}
