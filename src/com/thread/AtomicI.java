package com.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AtomicInteger count = new AtomicInteger(0);

		count.incrementAndGet();  // atomic increment
		count.get();              // read
		count.addAndGet(5);       // atomic add

	}

}
