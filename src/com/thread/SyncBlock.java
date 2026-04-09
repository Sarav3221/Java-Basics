package com.thread;

public class SyncBlock {
	
	private int count=0;
	private Object ob=new Object();
	 
	 public void increment() {
		 synchronized (ob) {
				counter++;
			}
	 }
	

}
