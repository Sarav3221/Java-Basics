package com.thread;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ThreadingPlayground {

    // =============================================================
    //  COMMON CLASSES
    // =============================================================

    static class UnsafeCounter {
        int count = 0;
        void increment() { count++; }
    }

    static class SafeCounter {
        int count = 0;
        synchronized void increment() { count++; }
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (Exception ignored) {}
    }

    // =============================================================
    // 1. RACE CONDITION
    // =============================================================
    static void scenarioRaceCondition() throws Exception {
        System.out.println("\n===== SCENARIO 1: RACE CONDITION =====");

        UnsafeCounter counter = new UnsafeCounter();

        Thread t1 = new Thread(() -> { for (int i = 0; i < 5000; i++) counter.increment(); });
        Thread t2 = new Thread(() -> { for (int i = 0; i < 5000; i++) counter.increment(); });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Expected = 10000, Actual = " + counter.count);
    }

    // =============================================================
    // 2. SYNCHRONIZED (FIX RACE CONDITION)
    // =============================================================
    static void scenarioSynchronizedFix() throws Exception {
        System.out.println("\n===== SCENARIO 2: SYNCHRONIZED FIX =====");

        SafeCounter counter = new SafeCounter();

        Thread t1 = new Thread(() -> { for (int i = 0; i < 5000; i++) counter.increment(); });
        Thread t2 = new Thread(() -> { for (int i = 0; i < 5000; i++) counter.increment(); });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Expected = 10000, Actual = " + counter.count);
    }

    // =============================================================
    // 3. EXECUTOR SERVICE
    // =============================================================
    static void scenarioExecutorService() throws Exception {
        System.out.println("\n===== SCENARIO 3: EXECUTOR SERVICE =====");

        ExecutorService executor = Executors.newFixedThreadPool(3);
        SafeCounter counter = new SafeCounter();

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> { for (int j = 0; j < 1000; j++) counter.increment(); });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Expected = 10000, Actual = " + counter.count);
    }

    // =============================================================
    // 4. DEADLOCK
    // =============================================================
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    static void scenarioDeadlock() {
        System.out.println("\n===== SCENARIO 4: DEADLOCK (DEMONSTRATION) =====");

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("T1 acquired lock1");
                sleep(100);
                synchronized (lock2) {
                    System.out.println("T1 acquired lock2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("T2 acquired lock2");
                sleep(100);
                synchronized (lock1) {
                    System.out.println("T2 acquired lock1");
                }
            }
        });

        t1.start();
        t2.start();

        sleep(500);
        System.out.println("Deadlock occurred!");
    }

    // =============================================================
    // 5. DEADLOCK FIX (LOCK ORDERING)
    // =============================================================
    static Object L1 = new Object();
    static Object L2 = new Object();

    static void scenarioDeadlockFix() throws Exception {
        System.out.println("\n===== SCENARIO 5: DEADLOCK FIX =====");

        Thread t1 = new Thread(() -> {
            synchronized (L1) {
                System.out.println("T1 acquired L1");
                sleep(100);
                synchronized (L2) {
                    System.out.println("T1 acquired L2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (L1) { // Same order avoids deadlock
                System.out.println("T2 acquired L1");
                sleep(100);
                synchronized (L2) {
                    System.out.println("T2 acquired L2");
                }
            }
        });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Deadlock prevented!");
    }

    // =============================================================
    // 6. REENTRANT LOCK (FAIR & UNFAIR)
    // =============================================================
    static void scenarioReentrantLock() throws Exception {
        System.out.println("\n===== SCENARIO 6: REENTRANT LOCK =====");

        Lock lock = new ReentrantLock(true);  // FAIR LOCK

        Runnable task = () -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " acquired lock");
                sleep(200);
            } finally {
                lock.unlock();
            }
        };

        new Thread(task, "T1").start();
        new Thread(task, "T2").start();
        new Thread(task, "T3").start();

        sleep(1000);
    }

    // =============================================================
    // 7. READ-WRITE LOCK
    // =============================================================
    static void scenarioReadWriteLock() throws Exception {
        System.out.println("\n===== SCENARIO 7: READ-WRITE LOCK =====");

        ReadWriteLock rwLock = new ReentrantReadWriteLock();
        int[] data = {0};

        Thread writer = new Thread(() -> {
            rwLock.writeLock().lock();
            try {
                System.out.println("Writer updating data...");
                data[0] += 10;
                sleep(300);
            } finally {
                rwLock.writeLock().unlock();
            }
        });

        Thread reader = new Thread(() -> {
            rwLock.readLock().lock();
            try {
                System.out.println("Reader reads: " + data[0]);
            } finally {
                rwLock.readLock().unlock();
            }
        });

        writer.start();
        sleep(100);
        reader.start();

        writer.join();
        reader.join();
    }

    // =============================================================
    // 8. SEMAPHORE
    // =============================================================
    static void scenarioSemaphore() throws Exception {
        System.out.println("\n===== SCENARIO 8: SEMAPHORE =====");

        Semaphore semaphore = new Semaphore(2); // Only 2 threads allowed

        Runnable task = () -> {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " entered semaphore zone");
                sleep(300);
            } catch (Exception ignored) {}
            finally {
                semaphore.release();
            }
        };

        for (int i = 1; i <= 5; i++) {
            new Thread(task, "Thread-" + i).start();
        }

        sleep(2000);
    }

    // =============================================================
    // 9. COUNTDOWN LATCH
    // =============================================================
    static void scenarioCountDownLatch() throws Exception {
        System.out.println("\n===== SCENARIO 9: COUNTDOWN LATCH =====");

        CountDownLatch latch = new CountDownLatch(3);

        Runnable worker = () -> {
            System.out.println(Thread.currentThread().getName() + " finished work.");
            latch.countDown();
        };

        new Thread(worker).start();
        new Thread(worker).start();
        new Thread(worker).start();

        latch.await();
        System.out.println("Main thread continues after all workers done.");
    }

    // =============================================================
    // 10. CYCLIC BARRIER
    // =============================================================
    static void scenarioCyclicBarrier() throws Exception {
        System.out.println("\n===== SCENARIO 10: CYCLIC BARRIER =====");

        CyclicBarrier barrier = new CyclicBarrier(3, 
            () -> System.out.println("All threads reached barrier."));

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " waiting...");
            try { barrier.await(); }
            catch (Exception ignored) {}
        };

        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();

        sleep(1000);
    }

    // =============================================================
    // 11. PRODUCER-CONSUMER WITH BLOCKING QUEUE
    // =============================================================
    static void scenarioProducerConsumer() throws Exception {
        System.out.println("\n===== SCENARIO 11: PRODUCER-CONSUMER =====");

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Producing " + i);
                    queue.put(i);
                }
            } catch (Exception ignored) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Consuming " + queue.take());
                }
            } catch (Exception ignored) {}
        });

        producer.start(); consumer.start();
        producer.join(); consumer.join();
    }

    // =============================================================
    // 12. COMPLETABLE FUTURE
    // =============================================================
    static void scenarioCompletableFuture() throws Exception {
        System.out.println("\n===== SCENARIO 12: COMPLETABLE FUTURE =====");

        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() -> {
                    sleep(300);
                    return 100;
                });

        System.out.println("Result = " + future.get());
    }

    // =============================================================
    // 13. THREADLOCAL
    // =============================================================
    static void scenarioThreadLocal() throws Exception {
        System.out.println("\n===== SCENARIO 13: THREADLOCAL =====");

        ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 0);

        Runnable task = () -> {
            int val = local.get();
            local.set(val + 1);
            System.out.println(Thread.currentThread().getName() + " => " + local.get());
        };

        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();

        sleep(500);
    }

    // =============================================================
    // 14. CALLABLE + FUTURE
    // =============================================================
    static void scenarioCallableFuture() throws Exception {
        System.out.println("\n===== SCENARIO 14: CALLABLE + FUTURE =====");

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> task = () -> {
            sleep(300);
            return 10 * 10;
        };

        Future<Integer> result = executor.submit(task);

        System.out.println("Future result = " + result.get());
        executor.shutdown();
    }

    // =============================================================
    // MAIN
    // =============================================================
    public static void main(String[] args) throws Exception {

        scenarioRaceCondition();
        scenarioSynchronizedFix();
        scenarioExecutorService();
        // scenarioDeadlock();  // Uncomment when you want to test Deadlock
        scenarioDeadlockFix();
        scenarioReentrantLock();
        scenarioReadWriteLock();
        scenarioSemaphore();
        scenarioCountDownLatch();
        scenarioCyclicBarrier();
        scenarioProducerConsumer();
        scenarioCompletableFuture();
        scenarioThreadLocal();
        scenarioCallableFuture();

        System.out.println("\n===== ALL SCENARIOS EXECUTED SUCCESSFULLY =====\n");
    }
}
