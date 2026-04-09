package com.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Threadsampl {
	
	public static void main(String[] args) {
//Create Runnable helps 		
		Runnable task = () -> {
            System.out.println("Thread is running!");
        };

        // Create a Thread with the Runnable
        Thread thread = new Thread(task);

        // Start the thread
        thread.start();

        // Main thread continues to run
        System.out.println("Main thread is running.");
    }
	
	//volatile -  keyword-  when one Thread update variable that change immediately visibile to all other thread -prevents thread cache value not see latest - status flag - read write -Always read value from memory	
	//Atomic integer - calss lets us  update an integer safely without using synchronized - It solves the race condition that happens when multiple threads try to update a shared counter at the same time. 
	// diff- volatie- visible - flag++ ,Atomicity - 
	//Synchronized -Keyworks allows only one thread to run a block or method at a time-prevents inconsistent data by stopping multiple threads from modifying the same resource simultaneously
	//Executorservice -  -manages and reuses a pool of threads so we don’t create threads manually. - solves the problem of creating too many threads, which slows the system and wastes memory
					//newFixedthreadpoolsize(3),newsinglethreadexecutor
					 //-Methods  -submit(callable) -Return Future  ,execute (runnable_ -no return - ,shutdown -
	//ReentrantLocks- same thread acquire the lock multiple times without getting blocked -more advanced lock that gives better control than synchronized,It solves issues where we need timeout, fairness,
	  //or trying to lock without waiting, which synchronized cannot do.
	//Parallel stream - automatically splits work across many threads to make processing faster- It solves the problem of slow large data processing by running operations in parallel.
					 
	
		AtomicInteger counter = new AtomicInteger(0);

		public void increment() {
		    counter.incrementAndGet();  // ✔ lock-free, thread-safe
		}
		
	}

