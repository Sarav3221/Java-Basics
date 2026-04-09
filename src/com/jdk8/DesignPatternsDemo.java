package com.jdk8;

import java.util.*;

//========================================================================
//DESIGN PATTERNS DEMO – RUN EVERYTHING WITH ONE MENU, ONE CLICK
//========================================================================

public class DesignPatternsDemo {

 public static void main(String[] args) {

     Scanner sc = new Scanner(System.in);

     while (true) {
         System.out.println("\n=========== DESIGN PATTERNS MENU ===========");
         System.out.println("1. Singleton");
         System.out.println("2. Factory Method");
         System.out.println("3. Builder Pattern");
         System.out.println("4. Strategy Pattern");
         System.out.println("5. Observer Pattern");
         System.out.println("6. Decorator Pattern");
         System.out.println("7. Adapter Pattern");
         System.out.println("8. Template Method Pattern");
         System.out.println("9. Proxy Pattern");
         System.out.println("10. Chain Of Responsibility");
         System.out.println("99. Run All Patterns");
         System.out.println("0. Exit");

         System.out.print("Enter option: ");
         int option = sc.nextInt();

         switch (option) {
             case 1: singletonDemo(); break;
             case 2: factoryDemo(); break;
             case 3: builderDemo(); break;
             case 4: strategyDemo(); break;
             case 5: observerDemo(); break;
             case 6: decoratorDemo(); break;
             case 7: adapterDemo(); break;
             case 8: templateDemo(); break;
             case 9: proxyDemo(); break;
             case 10: chainDemo(); break;
             case 99:
                 singletonDemo();
                 factoryDemo();
                 builderDemo();
                 strategyDemo();
                 observerDemo();
                 decoratorDemo();
                 adapterDemo();
                 templateDemo();
                 proxyDemo();
                 chainDemo();
                 break;
             case 0: System.exit(0);
         }
     }
 }

 // --------------------------------------------------------------------
 // 1. SINGLETON PATTERN
 // --------------------------------------------------------------------
 private static void singletonDemo() {
     System.out.println("\n=== SINGLETON PATTERN ===");

     System.out.println("Problem before Singleton: Multiple objects cause inconsistent configuration.");
     System.out.println("Solution: Only ONE instance for whole application.");

     Singleton s1 = Singleton.getInstance();
     Singleton s2 = Singleton.getInstance();

     System.out.println("Are both objects same? " + (s1 == s2));
     System.out.println("Interview Answer (High-level): Ensures global access point, thread-safe, avoids memory waste.");
 }

 static class Singleton {
     private static volatile Singleton instance;

     private Singleton() {}

     public static Singleton getInstance() {
         if (instance == null) {
             synchronized (Singleton.class) {
                 if (instance == null) instance = new Singleton();
             }
         }
         return instance;
     }
 }

 // --------------------------------------------------------------------
 // 2. FACTORY PATTERN
 // --------------------------------------------------------------------
 private static void factoryDemo() {
     System.out.println("\n=== FACTORY METHOD PATTERN ===");

     System.out.println("Problem before Factory: Tight coupling with 'new' keyword everywhere.");
     System.out.println("Solution: Factory hides object creation.");

     Shape shape1 = ShapeFactory.getShape("CIRCLE");
     shape1.draw();

     Shape shape2 = ShapeFactory.getShape("SQUARE");
     shape2.draw();

     System.out.println("Medium-level Interview Answer: Factory increases loose coupling and testability.");
 }

 interface Shape { void draw(); }

 static class Circle implements Shape {
     public void draw() { System.out.println("Drawing circle..."); }
 }
 static class Square implements Shape {
     public void draw() { System.out.println("Drawing square..."); }
 }

 static class ShapeFactory {
     public static Shape getShape(String type) {
         if ("CIRCLE".equalsIgnoreCase(type)) return new Circle();
         if ("SQUARE".equalsIgnoreCase(type)) return new Square();
         return null;
     }
 }

 // --------------------------------------------------------------------
 // 3. BUILDER PATTERN
 // --------------------------------------------------------------------
 private static void builderDemo() {
     System.out.println("\n=== BUILDER PATTERN ===");

     System.out.println("Problem before Builder: Constructor telescoping (too many params).");
     System.out.println("Solution: Builder creates readable object creation.");

     User user = new User.Builder()
             .setId(101)
             .setName("Saravanan")
             .setEmail("saravanan@example.com")
             .build();

     System.out.println("Created User using Builder: " + user);
     System.out.println("High-level answer: Helps in immutability + complex object creation.");
 }

 static class User {
     private int id;
     private String name;
     private String email;

     public static class Builder {
         private int id;
         private String name;
         private String email;

         public Builder setId(int id) { this.id = id; return this; }
         public Builder setName(String name) { this.name = name; return this; }
         public Builder setEmail(String email) { this.email = email; return this; }

         public User build() {
             User u = new User();
             u.id = id;
             u.name = name;
             u.email = email;
             return u;
         }
     }

     public String toString() { return id + ", " + name + ", " + email; }
 }

 // --------------------------------------------------------------------
 // 4. STRATEGY PATTERN
 // --------------------------------------------------------------------
 private static void strategyDemo() {
     System.out.println("\n=== STRATEGY PATTERN ===");

     System.out.println("Problem: If-else or switch logic everywhere.");
     System.out.println("Solution: Inject algorithm at runtime.");

     PaymentContext ctx = new PaymentContext(new UpiPayment());
     ctx.pay(500);

     ctx = new PaymentContext(new CardPayment());
     ctx.pay(1000);

     System.out.println("High-level answer: Strategy removes conditional logic + supports runtime changes.");
 }

 interface PaymentStrategy {
     void pay(int amount);
 }
 static class UpiPayment implements PaymentStrategy {
     public void pay(int amt) { System.out.println("Paid using UPI: " + amt); }
 }
 static class CardPayment implements PaymentStrategy {
     public void pay(int amt) { System.out.println("Paid using Card: " + amt); }
 }
 static class PaymentContext {
     private PaymentStrategy strategy;
     PaymentContext(PaymentStrategy s) { this.strategy = s; }
     public void pay(int amt) { strategy.pay(amt); }
 }

 // --------------------------------------------------------------------
 // 5. OBSERVER PATTERN
 // --------------------------------------------------------------------
 private static void observerDemo() {
     System.out.println("\n=== OBSERVER PATTERN ===");

     System.out.println("Problem: Need automatic notification to multiple subscribers.");
     System.out.println("Solution: Observer makes push-based updates.");

     NewsAgency agency = new NewsAgency();
     agency.addObserver(new MobileClient());
     agency.addObserver(new WebClient());

     agency.setNews("Breaking News: Design Patterns mastered!");

     System.out.println("High-level answer: Useful in event-driven systems (Kafka-like).");
 }

 interface Observer {
     void update(String news);
 }
 static class MobileClient implements Observer {
     public void update(String news) { System.out.println("Mobile client received: " + news); }
 }
 static class WebClient implements Observer {
     public void update(String news) { System.out.println("Web client received: " + news); }
 }

 static class NewsAgency {
     List<Observer> observers = new ArrayList<>();
     String news;

     void addObserver(Observer o) { observers.add(o); }

     void setNews(String news) {
         this.news = news;
         for (Observer o : observers) o.update(news);
     }
 }

 // --------------------------------------------------------------------
 // 6. DECORATOR PATTERN
 // --------------------------------------------------------------------
 private static void decoratorDemo() {
     System.out.println("\n=== DECORATOR PATTERN ===");

     System.out.println("Problem: Extending behavior using inheritance leads to class explosion.");
     System.out.println("Solution: Decorator adds behavior dynamically.");

     Coffee coffee = new MilkDecorator(new BasicCoffee());
     System.out.println("Cost with Milk: " + coffee.cost());

     coffee = new SugarDecorator(coffee);
     System.out.println("Cost with Milk + Sugar: " + coffee.cost());

     System.out.println("High-level answer: Flexible extension without touching existing code.");
 }

 interface Coffee { int cost(); }

 static class BasicCoffee implements Coffee {
     public int cost() { return 10; }
 }

 static class MilkDecorator implements Coffee {
     private Coffee base;
     MilkDecorator(Coffee c) { base = c; }
     public int cost() { return base.cost() + 5; }
 }

 static class SugarDecorator implements Coffee {
     private Coffee base;
     SugarDecorator(Coffee c) { base = c; }
     public int cost() { return base.cost() + 2; }
 }

 // --------------------------------------------------------------------
 // 7. ADAPTER PATTERN
 // --------------------------------------------------------------------
 private static void adapterDemo() {
     System.out.println("\n=== ADAPTER PATTERN ===");

     System.out.println("Problem: Client expects X but existing class gives Y.");
     System.out.println("Solution: Adapter converts old API to new API.");

     NewPaymentSystem newPayment = new PaymentAdapter(new OldBankApi());
     newPayment.newPay(250);

     System.out.println("High-level: Useful in migrations, legacy system integrations.");
 }

 interface NewPaymentSystem { void newPay(int amount); }

 static class PaymentAdapter implements NewPaymentSystem {
     private OldBankApi api;
     PaymentAdapter(OldBankApi api) { this.api = api; }
     public void newPay(int amount) {
         api.oldPayMethod(amount);
     }
 }

 static class OldBankApi {
     public void oldPayMethod(int amount) {
         System.out.println("Old Bank API processed payment: " + amount);
     }
 }

 // --------------------------------------------------------------------
 // 8. TEMPLATE METHOD
 // --------------------------------------------------------------------
 private static void templateDemo() {
     System.out.println("\n=== TEMPLATE METHOD PATTERN ===");

     System.out.println("Problem: Duplicate algorithm steps everywhere.");
     System.out.println("Solution: Template defines fixed steps; subclasses override specific parts.");

     new CSVReport().generateReport();
     new PDFReport().generateReport();

     System.out.println("High-level: Achieves code reuse + enforces structure.");
 }

 static abstract class ReportTemplate {
     public final void generateReport() {
         fetchData();
         processData();
         printReport();
     }

     abstract void fetchData();
     abstract void processData();
     void printReport() { System.out.println("Report printed."); }
 }

 static class CSVReport extends ReportTemplate {
     void fetchData() { System.out.println("Fetching CSV data..."); }
     void processData() { System.out.println("Processing CSV data..."); }
 }

 static class PDFReport extends ReportTemplate {
     void fetchData() { System.out.println("Fetching PDF data..."); }
     void processData() { System.out.println("Processing PDF data..."); }
 }

 // --------------------------------------------------------------------
 // 9. PROXY PATTERN
 // --------------------------------------------------------------------
 private static void proxyDemo() {
     System.out.println("\n=== PROXY PATTERN ===");

     System.out.println("Problem: Expensive object creation or need security.");
     System.out.println("Solution: Proxy controls access.");

     Image img = new ProxyImage("sample.jpg");
     img.display(); // Loads image
     img.display(); // From cache

     System.out.println("High-level: Used in caching, lazy loading, AOP, Spring Proxies.");
 }

 interface Image { void display(); }

 static class RealImage implements Image {
     String filename;

     RealImage(String filename) {
         this.filename = filename;
         load();
     }

     private void load() { System.out.println("Loading image from disk: " + filename); }

     public void display() { System.out.println("Displaying: " + filename); }
 }

 static class ProxyImage implements Image {
     private RealImage real;
     private String filename;

     ProxyImage(String filename) { this.filename = filename; }

     public void display() {
         if (real == null) real = new RealImage(filename);
         System.out.println("Proxy delegating to real object...");
         real.display();
     }
 }

 // --------------------------------------------------------------------
 // 10. CHAIN OF RESPONSIBILITY
 // --------------------------------------------------------------------
 private static void chainDemo() {
     System.out.println("\n=== CHAIN OF RESPONSIBILITY PATTERN ===");

     System.out.println("Problem: If-else chain for request processing.");
     System.out.println("Solution: Chain of objects handles request sequentially.");

     Handler h1 = new InfoLogger();
     Handler h2 = new DebugLogger();
     Handler h3 = new ErrorLogger();

     h1.setNext(h2);
     h2.setNext(h3);

     h1.handle("INFO: System running");
     h1.handle("ERROR: Crash!");

     System.out.println("High-level: Used in loggers, filters, middlewares.");
 }

 static abstract class Handler {
     protected Handler next;
     public void setNext(Handler n) { next = n; }

     public void handle(String msg) {
         if (next != null) next.handle(msg);
     }
 }

 static class InfoLogger extends Handler {
     public void handle(String msg) {
         if (msg.startsWith("INFO")) System.out.println("INFO Logger: " + msg);
         super.handle(msg);
     }
 }

 static class DebugLogger extends Handler {
     public void handle(String msg) {
         if (msg.startsWith("DEBUG")) System.out.println("DEBUG Logger: " + msg);
         super.handle(msg);
     }
 }

 static class ErrorLogger extends Handler {
     public void handle(String msg) {
         if (msg.startsWith("ERROR")) System.out.println("ERROR Logger: " + msg);
         super.handle(msg);
     }
 }
}

