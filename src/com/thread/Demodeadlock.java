package com.thread;

public class Demodeadlock {

	public static void main(String[] args) throws InterruptedException {
		
		Object obj1= new Object();
		Object obj2= new Object();
		
		Thread t1=new Thread(()-> {
			synchronized(obj1) {
				System.out.println("T1 acquired obj1");
				try { Thread.sleep(100); } catch (Exception e) {}
                // Pause so T2 gets obj2 before T1 tries to acquire obj2
                System.out.println("T1 trying to acquire obj2...");
				synchronized(obj2) {
					System.out.println("T1 acquired obj2");
				}
			}
		});
		

		Thread t2=new Thread(()-> {
			synchronized(obj2) {
				 System.out.println("T1 acquired obj2");
				synchronized(obj1) {
					System.out.println("T1 acquired obj1");
				}
			}
		});
		
		t1.start(); t2.start();
		t1.join(); t2.join();
		
}}
