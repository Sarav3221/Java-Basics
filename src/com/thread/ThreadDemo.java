package com.thread;

public class ThreadDemo extends Thread{
	
	static int count=0;
	
	public synchronized void run() {
		System.out.println("Run1" + Thread.currentThread().getName());
	}
	public static void increment() {
		count++;
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ThreadDemo t3=new ThreadDemo();
		ThreadDemo t4=new ThreadDemo();
		t3.start();
		t4.start();
		
		t3.join();  // main thread wait
		t4.join();
		
		
		Thread t1 = new Thread(() -> { for(int i=0;i<1000;i++) increment(); });
        Thread t2 = new Thread(() -> { for(int i=0;i<1000;i++) increment(); });

        t1.start(); t2.start();
        t1.join(); t2.join();


	}

}

public class SyncBlock {
    private int counter = 0;
    private final Object lock = new Object();

    public void increment() {
        synchronized(lock) {
            counter++;
        }
    }
}

