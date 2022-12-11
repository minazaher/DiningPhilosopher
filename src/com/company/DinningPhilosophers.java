package com.company;

import java.util.concurrent.*;

public class DinningPhilosophers {
    static int no_of_philosophers = 5;
    public static int counter = 0;
    static Philosopher[] philosophers = new Philosopher[no_of_philosophers];
    static Chopstick[] chopsticks = new Chopstick[no_of_philosophers];
    public static ExecutorService executor = Executors.newFixedThreadPool(no_of_philosophers);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < no_of_philosophers; i++) {
            chopsticks[i] = new Chopstick();
        }
        for (counter = 0; counter < no_of_philosophers; counter++) {
            if (counter % 2 == 0)
                philosophers[counter] = new Philosopher(counter, chopsticks[(counter + 1) % no_of_philosophers], chopsticks[counter]);
            else
                philosophers[counter] = new Philosopher(counter, chopsticks[counter], chopsticks[(counter + 1) % no_of_philosophers]);
            executor.execute(philosophers[counter]);
        }
    }
    
    private static class Chopstick {
        public Semaphore semaphore = new Semaphore(1);

        void grabChopstick(int i) throws InterruptedException {
            if (semaphore.availablePermits() == 1) {
                semaphore.acquire();
                if (i == 0)
                    System.out.println("Philosopher" + Thread.currentThread().getId() + " picked up left chopstick");
                else if (i == 1)
                    System.out.println("Philosopher" + Thread.currentThread().getId() + " picked up right chopstick");
            } else if (semaphore.availablePermits() == 0){
                if (i == 0)
                    System.out.println("Philosopher" + Thread.currentThread().getId() + " requested to picked up left chopstick");
                else if (i == 1)
                    System.out.println("Philosopher" + Thread.currentThread().getId() + " requested to picked up right chopstick");
            }
        }


        void dropChopstick(int i) {
            if (semaphore.hasQueuedThreads())
            semaphore.release();
            if (i == 0)
                System.out.println("Philosopher" + Thread.currentThread().getId() + " released left chopstick");
            else if (i == 1)
                System.out.println("Philosopher" + Thread.currentThread().getId() + " released right chopstick");

        }
    }

    private static class Philosopher implements Runnable {
        Chopstick leftChopstick;
        Chopstick rightChopstick;
        private final int number;

        public Philosopher(int num, Chopstick left, Chopstick right) {
            number = num;
            leftChopstick = left;
            rightChopstick = right;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                performAction(" thinks for ");
                try {
                    leftChopstick.grabChopstick(0);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    rightChopstick.grabChopstick(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                performAction(" eats for ");
                leftChopstick.dropChopstick(0);
                rightChopstick.dropChopstick(0);
            }
        }

        void performAction(String action) {
            int waitTime = ThreadLocalRandom.current().nextInt(0,
                    1000);
            System.out.println("Philosopher " + Thread.currentThread().getId() + action
                    + waitTime + " ms");
        }
    }
    public void tryGet(Chopstick c1, Chopstick c2){
        c1.semaphore.tryAcquire();
        c2.semaphore.tryAcquire();
    }

}


