package com.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {
	ReentrantLock lock=new ReentrantLock();
	
	public void	methodA() {
		
		lock.lock();
		try {
			methodB();
		} finally {
			lock.unlock();
		}
		try {
			
		} finally {
			// TODO: handle finally clause
		}
		
		 
	 }
	
	public void	methodB() {
		
		lock.lock();
		try {
			methodA();
			 System.out.println("In method B");
		} finally {
			lock.unlock();
		}
		 
	 }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
	
	}

}
