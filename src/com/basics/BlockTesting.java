package com.basics;

class Test {
    static { System.out.println("Static"); }
    { System.out.println("Instance"); }
    Test() { System.out.println("Constructor"); }
}

new Test();
new Test();

public class BlockTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}

class Counter {
    int count = 0;
    void increment() { count++; }
}

Counter c = new Counter();

Thread t1 = new Thread(() -> {
    for (int i = 0; i < 1000; i++) c.increment();
});

Thread t2 = new Thread(() -> {
    for (int i = 0; i < 1000; i++) c.increment();
});

t1.start();
t2.start();
t1.join(); t2.join();

System.out.println(c.count);
