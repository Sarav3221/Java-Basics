package com.thread;

public class MyThread extends Thread{
	
	
	public synchronized void run() {
		System.out.println("Inside Run"+ Thread.currentThread().getName());
	}
	
	public static void main(String[] args) throws Exception {
		MyThread m1=new MyThread();
		m1.run();   // Normal method call
		m1.sleep(20000);
		m1.start();
		
	}
}


class A {};
class B {}

A a = new A();
B b = new B();

Thread t1 = new Thread(() -> {
    synchronized(a) {
        synchronized(b) { }
    }
});

Thread t2 = new Thread(() -> {
    synchronized(b) {
        synchronized(a) { }
    }
})
