package com.thread;

import java.lang.reflect.Constructor;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

public class ThreadingMaster {

    // -----------------------
    // Utility helpers & common resources
    // -----------------------
    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    // -----------------------
    // 1. Race condition (unsafe counter)
    // -----------------------
    static class UnsafeCounter { int count = 0; void increment() { count++; } }
    static void scenario1_raceCondition() throws Exception {
        System.out.println("\n=== SCENARIO 1: RACE CONDITION ===");
        UnsafeCounter c = new UnsafeCounter();
        Thread t1 = new Thread(() -> { for (int i = 0; i < 5000; i++) c.increment(); }, "R-T1");
        Thread t2 = new Thread(() -> { for (int i = 0; i < 5000; i++) c.increment(); }, "R-T2");
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println("Expected=10000 Actual=" + c.count + " (likely <10000 due to race)");
    }

    // -----------------------
    // 2. Synchronized fix
    // -----------------------
    static class SafeCounter { int count=0; synchronized void increment(){count++;} }
    static void scenario2_synchronized() throws Exception {
        System.out.println("\n=== SCENARIO 2: SYNCHRONIZED FIX ===");
        SafeCounter c = new SafeCounter();
        Thread t1 = new Thread(() -> { for (int i=0;i<5000;i++) c.increment(); });
        Thread t2 = new Thread(() -> { for (int i=0;i<5000;i++) c.increment(); });
        t1.start(); t2.start(); t1.join(); t2.join();
        System.out.println("Expected=10000 Actual=" + c.count + " (synchronized ensures correctness)");
    }

    // -----------------------
    // 3. ExecutorService with Runnable tasks
    // -----------------------
    static void scenario3_executorService() throws Exception {
        System.out.println("\n=== SCENARIO 3: EXECUTOR SERVICE ===");
        ExecutorService ex = Executors.   newFixedThreadPool(4);
        SafeCounter c = new SafeCounter();
        for (int i=0;i<10;i++) {
            ex.submit(() -> { for (int j=0;j<1000;j++) c.increment(); });
        }
        ex.shutdown();
        ex.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Expected=10000 Actual=" + c.count);
    }

    // -----------------------
    // 4. Deadlock demonstration (but safe — we detect)
    // -----------------------
    static final Object DLOCK_A = new Object();
    static final Object DLOCK_B = new Object();
    static void scenario4_deadlockDetect() throws Exception {
        System.out.println("\n=== SCENARIO 4: DEADLOCK (DETECTION, no hang) ===");
        Thread t1 = new Thread(() -> {
            synchronized (DLOCK_A) {
                System.out.println("T1 acquired A");
                sleep(200);
                synchronized (DLOCK_B) {
                    System.out.println("T1 acquired B");
                }
            }
        }, "DL-T1");

        Thread t2 = new Thread(() -> {
            synchronized (DLOCK_B) {
                System.out.println("T2 acquired B");
                sleep(200);
                synchronized (DLOCK_A) {
                    System.out.println("T2 acquired A");
                }
            }
        }, "DL-T2");

        t1.start(); t2.start();
        // wait briefly to see if both are alive (possible deadlock)
        t1.join(800); t2.join(800);
        if (t1.isAlive() && t2.isAlive()) {
            System.out.println("POSSIBLE DEADLOCK DETECTED: both threads still alive after timeout");
        } else {
            System.out.println("No deadlock (threads finished or one completed).");
        }
    }

    // -----------------------
    // 5. Deadlock fix by consistent lock ordering
    // -----------------------
    static final Object ORDER_A = new Object();
    static final Object ORDER_B = new Object();
    static void scenario5_deadlockFix() throws Exception {
        System.out.println("\n=== SCENARIO 5: DEADLOCK FIX (LOCK ORDERING) ===");
        Thread t1 = new Thread(() -> {
            synchronized (ORDER_A) {
                System.out.println("T1 acquired ORDER_A");
                sleep(50);
                synchronized (ORDER_B) { System.out.println("T1 acquired ORDER_B"); }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (ORDER_A) { // same order as t1
                System.out.println("T2 acquired ORDER_A");
                sleep(50);
                synchronized (ORDER_B) { System.out.println("T2 acquired ORDER_B"); }
            }
        });
        t1.start(); t2.start(); t1.join(); t2.join();
        System.out.println("Consistent ordering avoided deadlock.");
    }

    // -----------------------
    // 6. ReentrantLock (fair vs unfair) & tryLock
    // -----------------------
    static void scenario6_reentrantLock() throws Exception {
        System.out.println("\n=== SCENARIO 6: REENTRANT LOCK (tryLock demo) ===");
        ReentrantLock lock = new ReentrantLock(true); // fair lock
        Runnable r = () -> {
            String name = Thread.currentThread().getName();
            try {
                if (lock.tryLock(300, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println(name + " got lock");
                        sleep(150);
                    } finally { lock.unlock(); }
                } else {
                    System.out.println(name + " could not get lock (tryLock timed out)");
                }
            } catch (InterruptedException ignored) {}
        };
        new Thread(r, "RL-T1").start();
        new Thread(r, "RL-T2").start();
        new Thread(r, "RL-T3").start();
        sleep(800);
    }

    // -----------------------
    // 7. ReadWriteLock
    // -----------------------
    static void scenario7_readWriteLock() throws Exception {
        System.out.println("\n=== SCENARIO 7: READ-WRITE LOCK ===");
        ReadWriteLock rw = new ReentrantReadWriteLock();
        final int[] data = {0};
        Thread writer = new Thread(() -> {
            rw.writeLock().lock();
            try {
                System.out.println("Writer updating...");
                sleep(200);
                data[0] += 10;
                System.out.println("Writer done. Data=" + data[0]);
            } finally { rw.writeLock().unlock(); }
        });
        Thread reader = new Thread(() -> {
            rw.readLock().lock();
            try {
                System.out.println("Reader read data=" + data[0]);
            } finally { rw.readLock().unlock(); }
        });
        writer.start(); sleep(50); reader.start(); writer.join(); reader.join();
    }

    // -----------------------
    // 8. Semaphore (limit concurrent access)
    // -----------------------
    static void scenario8_semaphore() throws Exception {
        System.out.println("\n=== SCENARIO 8: SEMAPHORE ===");
        Semaphore sem = new Semaphore(2);
        Runnable task = () -> {
            try {
                sem.acquire();
                System.out.println(Thread.currentThread().getName() + " in critical zone");
                sleep(200);
            } catch (InterruptedException ignored) {
            } finally { sem.release(); }
        };
        for (int i=1;i<=5;i++) new Thread(task, "S-"+i).start();
        sleep(1000);
    }

    // -----------------------
    // 9. CountDownLatch
    // -----------------------
    static void scenario9_countDownLatch() throws Exception {
        System.out.println("\n=== SCENARIO 9: COUNTDOWN LATCH ===");
        CountDownLatch latch = new CountDownLatch(3);
        Runnable worker = () -> { System.out.println(Thread.currentThread().getName()+" done"); latch.countDown(); };
        new Thread(worker, "W1").start();
        new Thread(worker, "W2").start();
        new Thread(worker, "W3").start();
        latch.await();
        System.out.println("Main continues after workers finished.");
    }

    // -----------------------
    // 10. CyclicBarrier
    // -----------------------
    static void scenario10_cyclicBarrier() throws Exception {
        System.out.println("\n=== SCENARIO 10: CYCLIC BARRIER ===");
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("Barrier action: all arrived"));
        Runnable r = () -> {
            try {
                System.out.println(Thread.currentThread().getName()+" waiting at barrier");
                barrier.await();
                System.out.println(Thread.currentThread().getName()+" passed barrier");
            } catch (Exception ignored) {}
        };
        new Thread(r, "B1").start();
        new Thread(r, "B2").start();
        new Thread(r, "B3").start();
        sleep(800);
    }

    // -----------------------
    // 11. Producer-Consumer with BlockingQueue
    // -----------------------
    static void scenario11_producerConsumer() throws Exception {
        System.out.println("\n=== SCENARIO 11: PRODUCER-CONSUMER (BlockingQueue) ===");
        BlockingQueue<Integer> q = new ArrayBlockingQueue<>(3);
        Thread producer = new Thread(() -> {
            try {
                for (int i=1;i<=5;i++) {
                    System.out.println("Producing " + i);
                    q.put(i);
                    sleep(100);
                }
            } catch (InterruptedException ignored) {}
        });
        Thread consumer = new Thread(() -> {
            try {
                for (int i=1;i<=5;i++) {
                    Integer v = q.take();
                    System.out.println("Consuming " + v);
                    sleep(200);
                }
            } catch (InterruptedException ignored) {}
        });
        producer.start(); consumer.start(); producer.join(); consumer.join();
    }

    // -----------------------
    // 12. CompletableFuture
    // -----------------------
    static void scenario12_completableFuture() throws Exception {
        System.out.println("\n=== SCENARIO 12: COMPLETABLE FUTURE ===");
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> {
            sleep(300);
            return 42;
        });
        System.out.println("Result from future: " + f.get());
    }

    // -----------------------
    // 13. ThreadLocal
    // -----------------------
    static void scenario13_threadLocal() throws Exception {
        System.out.println("\n=== SCENARIO 13: THREADLOCAL ===");
        ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 0);
        Runnable r = () -> {
            int val = local.get();
            local.set(val + 1);
            System.out.println(Thread.currentThread().getName() + " => " + local.get());
        };
        new Thread(r, "TL-1").start();
        new Thread(r, "TL-2").start();
        new Thread(r, "TL-3").start();
        sleep(400);
    }

    // -----------------------
    // 14. Callable + Future
    // -----------------------
    static void scenario14_callableFuture() throws Exception {
        System.out.println("\n=== SCENARIO 14: CALLABLE + FUTURE ===");
        ExecutorService ex = Executors.newSingleThreadExecutor();
        Future<Integer> f = ex.submit(() -> { sleep(200); return 7 * 7; });
        System.out.println("Future result = " + f.get());
        ex.shutdown();
    }

    // -----------------------
    // 15. AtomicInteger
    // -----------------------
    static void scenario15_atomicInteger() throws Exception {
        System.out.println("\n=== SCENARIO 15: ATOMIC INTEGER ===");
        AtomicInteger ai = new AtomicInteger(0);
        Thread t1 = new Thread(() -> { for (int i=0;i<5000;i++) ai.incrementAndGet(); });
        Thread t2 = new Thread(() -> { for (int i=0;i<5000;i++) ai.incrementAndGet(); });
        t1.start(); t2.start(); t1.join(); t2.join();
        System.out.println("AtomicInteger result = " + ai.get() + " (expected 10000)");
    }

    // -----------------------
    // 16. volatile visibility demonstration (no volatile)
    // -----------------------
    static class VisibilityNoVolatile {
        static boolean running = true;
    }
    static void scenario16_visibilityNoVolatile() throws Exception {
        System.out.println("\n=== SCENARIO 16: VISIBILITY (NO volatile) ===");
        Thread t = new Thread(() -> {
            int i=0;
            while (VisibilityNoVolatile.running) { i++; if (i % 1000000 ==0) Thread.yield(); }
            System.out.println("Worker loop exited (no-volatile).");
        });
        t.setDaemon(true);
        t.start();
        sleep(200);
        VisibilityNoVolatile.running = false;
        // wait briefly - if worker doesn't stop quickly we detect visibility problem
        t.join(300);
        if (t.isAlive()) {
            System.out.println("Worker still alive => visibility might be an issue without volatile (detected by timeout).");
        } else {
            System.out.println("Worker stopped (in this JVM the update became visible).");
        }
    }

    // -----------------------
    // 17. volatile fix
    // -----------------------
    static class VisibilityWithVolatile { static volatile boolean running = true; }
    static void scenario17_visibilityWithVolatile() throws Exception {
        System.out.println("\n=== SCENARIO 17: VISIBILITY (volatile) ===");
        Thread t = new Thread(() -> {
            while (VisibilityWithVolatile.running) { /* busy wait */ }
            System.out.println("Worker loop exited (volatile).");
        });
        t.start();
        sleep(200);
        VisibilityWithVolatile.running = false;
        t.join(500);
        if (!t.isAlive()) System.out.println("Worker stopped as expected due to volatile visibility.");
        else System.out.println("Worker still alive (unexpected).");
    }

    // -----------------------
    // 18. Reflection to create a Thread
    // -----------------------
    static void scenario18_reflectionThread() throws Exception {
        System.out.println("\n=== SCENARIO 18: REFLECTION (create Thread) ===");
        Class<?> cls = Class.forName("java.lang.Thread");
        Constructor<?> ctor = cls.getConstructor(Runnable.class, String.class);
        Runnable task = () -> System.out.println("Hello from reflected thread!");
        Thread t = (Thread) ctor.newInstance(task, "RefThread");
        t.start(); t.join();
        System.out.println("Reflection thread finished.");
    }

    // -----------------------
    // 19. Thread priority (note: platform dependent)
    // -----------------------
    static void scenario19_priority() throws Exception {
        System.out.println("\n=== SCENARIO 19: THREAD PRIORITY (platform-dependent) ===");
        Runnable r = () -> { for (int i=0;i<3;i++) System.out.println(Thread.currentThread().getName()); };
        Thread low = new Thread(r, "LOW"); low.setPriority(Thread.MIN_PRIORITY);
        Thread high = new Thread(r, "HIGH"); high.setPriority(Thread.MAX_PRIORITY);
        low.start(); high.start(); low.join(); high.join();
        System.out.println("Priority effect depends on OS/JVM scheduling.");
    }
    
 // ===== SCENARIO 20: DAEMON THREAD (SAFE SHUTDOWN) =====
    static void scenarioDaemonThread() throws Exception {
        System.out.println("\n===== SCENARIO 20: DAEMON THREAD (SAFE) =====");

        Thread daemon = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Daemon running...");
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                // Thread interrupted during sleep
            }
            System.out.println("Daemon stopped cleanly.");
        });

        daemon.setDaemon(true);
        daemon.start();

        System.out.println("Main thread working for 1 second...");
        Thread.sleep(1000);

        System.out.println("Interrupting daemon thread now...");
        daemon.interrupt();  // <-- clean shutdown trigger

        Thread.sleep(300); // Allow daemon to print final message
    }

    /*// -----------------------
    // 20. Daemon thread demonstration
    // -----------------------
    static void scenario20_daemon() throws Exception {
        System.out.println("\n=== SCENARIO 20: DAEMON THREAD ===");
        Thread daemon = new Thread(() -> {
            while (true) { System.out.println("Daemon alive..."); sleep(200); }
        }, "myDaemon");
        daemon.setDaemon(true);
        daemon.start();
        System.out.println("Main sleeps 700ms then exits; daemon will be terminated when JVM exits.");
        sleep(700);
    }*/

    // -----------------------
    // 21. Thread interruption example
    // -----------------------
    static void scenario21_interruption() throws Exception {
        System.out.println("\n=== SCENARIO 21: THREAD INTERRUPTION ===");
        Thread t = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Working...");
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted during sleep: " + e);
            }
            System.out.println("Thread exiting.");
        });
        t.start(); sleep(400); t.interrupt(); t.join();
    }

    // -----------------------
    // 22. ScheduledExecutorService
    // -----------------------
    static void scenario22_scheduledExecutor() throws Exception {
        System.out.println("\n=== SCENARIO 22: SCHEDULED EXECUTOR ===");
        ScheduledExecutorService sched = Executors.newScheduledThreadPool(1);
        sched.schedule(() -> System.out.println("Scheduled task ran after 500ms"), 500, TimeUnit.MILLISECONDS);
        sched.shutdown(); sched.awaitTermination(1, TimeUnit.SECONDS);
    }

    // -----------------------
    // 23. tryLock-based deadlock avoidance demo
    // -----------------------
    static void scenario23_tryLockAvoidDeadlock() throws Exception {
        System.out.println("\n=== SCENARIO 23: tryLock to avoid deadlock ===");
        ReentrantLock a = new ReentrantLock();
        ReentrantLock b = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                if (a.tryLock(200, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("T1 got A");
                        sleep(150);
                        if (b.tryLock(200, TimeUnit.MILLISECONDS)) {
                            try { System.out.println("T1 got B"); }
                            finally { b.unlock(); }
                        } else System.out.println("T1 couldn't get B");
                    } finally { a.unlock(); }
                }
            } catch (InterruptedException ignored) {}
        });

        Thread t2 = new Thread(() -> {
            try {
                if (b.tryLock(200, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("T2 got B");
                        sleep(150);
                        if (a.tryLock(200, TimeUnit.MILLISECONDS)) {
                            try { System.out.println("T2 got A"); }
                            finally { a.unlock(); }
                        } else System.out.println("T2 couldn't get A");
                    } finally { b.unlock(); }
                }
            } catch (InterruptedException ignored) {}
        });

        t1.start(); t2.start(); t1.join(); t2.join();
    }

    // -----------------------
    // 24. Phaser example
    // -----------------------
    static void scenario24_phaser() throws Exception {
        System.out.println("\n=== SCENARIO 24: PHASER ===");
        Phaser ph = new Phaser(1); // register main
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + " arrived");
            ph.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " passed phase");
        };
        new Thread(r, "P1").start();
        new Thread(r, "P2").start();
        sleep(300);
        ph.arriveAndDeregister(); // allow others to pass
        sleep(300);
    }

    // -----------------------
    // 25. Immutable object thread-safety demo
    // -----------------------
    static final class ImmutablePoint { final int x,y; ImmutablePoint(int x,int y){this.x=x;this.y=y;} }
    static void scenario25_immutable() throws Exception {
        System.out.println("\n=== SCENARIO 25: IMMUTABLE OBJECTS ===");
        ImmutablePoint p = new ImmutablePoint(5,10);
        Thread t1 = new Thread(() -> System.out.println("X=" + p.x));
        Thread t2 = new Thread(() -> System.out.println("Y=" + p.y));
        t1.start(); t2.start(); t1.join(); t2.join();
    }

    // -----------------------
    // Utility: run all scenarios in order (safe defaults)
    // -----------------------
    static void runAll() throws Exception {
        scenario1_raceCondition();
        scenario2_synchronized();
        scenario3_executorService();
        scenario4_deadlockDetect(); // safe detection
        scenario5_deadlockFix();
        scenario6_reentrantLock();
        scenario7_readWriteLock();
        scenario8_semaphore();
        scenario9_countDownLatch();
        scenario10_cyclicBarrier();
        scenario11_producerConsumer();
        scenario12_completableFuture();
        scenario13_threadLocal();
        scenario14_callableFuture();
        scenario15_atomicInteger();
        scenario16_visibilityNoVolatile(); // demonstrates possible issue (timeout detection)
        scenario17_visibilityWithVolatile();
        scenario18_reflectionThread();
        scenario19_priority();
        scenarioDaemonThread();
        scenario21_interruption();
        scenario22_scheduledExecutor();
        scenario23_tryLockAvoidDeadlock();
        scenario24_phaser();
        scenario25_immutable();
        System.out.println("\n=== ALL SELECTED SCENARIOS EXECUTED ===");
    }

    // -----------------------
    // Simple menu-driven main
    // -----------------------
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String menu = "\nThreadingMaster - choose scenario (0 = all)\n"
                + " 1 Race condition\n 2 Synchronized fix\n 3 ExecutorService\n 4 Deadlock detect\n 5 Deadlock fix (ordering)\n 6 ReentrantLock (tryLock)\n"
                + " 7 ReadWriteLock\n 8 Semaphore\n 9 CountDownLatch\n 10 CyclicBarrier\n 11 Producer-Consumer\n 12 CompletableFuture\n 13 ThreadLocal\n 14 Callable+Future\n 15 AtomicInteger\n 16 Visibility (no volatile)\n 17 volatile visibility fix\n 18 Reflection Thread\n 19 Thread Priority\n 20 Daemon thread\n 21 Thread Interruption\n 22 ScheduledExecutor\n 23 tryLock deadlock-avoid\n 24 Phaser\n 25 Immutable object\n 99 Exit\nEnter choice: ";

        while (true) {
            System.out.print(menu);
            int choice;
            try { choice = Integer.parseInt(sc.nextLine().trim()); }
            catch (Exception e) { System.out.println("Invalid input"); continue; }

            switch (choice) {
                case 0: runAll(); break;
                case 1: scenario1_raceCondition(); break;
                case 2: scenario2_synchronized(); break;
                case 3: scenario3_executorService(); break;
                case 4: scenario4_deadlockDetect(); break;
                case 5: scenario5_deadlockFix(); break;
                case 6: scenario6_reentrantLock(); break;
                case 7: scenario7_readWriteLock(); break;
                case 8: scenario8_semaphore(); break;
                case 9: scenario9_countDownLatch(); break;
                case 10: scenario10_cyclicBarrier(); break;
                case 11: scenario11_producerConsumer(); break;
                case 12: scenario12_completableFuture(); break;
                case 13: scenario13_threadLocal(); break;
                case 14: scenario14_callableFuture(); break;
                case 15: scenario15_atomicInteger(); break;
                case 16: scenario16_visibilityNoVolatile(); break;
                case 17: scenario17_visibilityWithVolatile(); break;
                case 18: scenario18_reflectionThread(); break;
                case 19: scenario19_priority(); break;
                case 20: scenarioDaemonThread(); break;
                case 21: scenario21_interruption(); break;
                case 22: scenario22_scheduledExecutor(); break;
                case 23: scenario23_tryLockAvoidDeadlock(); break;
                case 24: scenario24_phaser(); break;
                case 25: scenario25_immutable(); break;
                case 99: System.out.println("Exiting."); sc.close(); return;
                default: System.out.println("Unknown choice"); break;
            }
            System.out.println("\n-- Scenario finished --\n");
        }
    }
}

