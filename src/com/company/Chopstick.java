package com.company;

import java.util.concurrent.Semaphore;

public class Chopstick {
    public Semaphore semaphore = new Semaphore(1);

    public synchronized void pick() throws InterruptedException {
        while (this.semaphore.availablePermits() == 0) {
            try {
                System.out.println("chopStick in use");
                wait(); //The calling thread waits until semaphore becomes free
            } catch (InterruptedException e) {
            }
        }
        this.semaphore.acquire();
    }


    public synchronized void leave(int ID, boolean isRight) {
        this.semaphore.release();
        notify();
        if (isRight)
            System.out.println("Philosopher" + ID + " releases the chopstick " + ID);
        else
            System.out.println("Philosopher" + ID + " releases the chopstick " + (ID + 1) % 5);
    }


//
//
//        void grabChopstick(int p ,int i) throws InterruptedException {
//
//            if (i == 0)
//                System.out.println("Philosopher" + p + " requested to picked up left chopstick");
//            else if (i == 1)
//                System.out.println("Philosopher" + p + " requested to picked up right chopstick");
//
//            if (semaphore.availablePermits() == 1) {
//                semaphore.acquire();
//                if (i == 0)
//                    System.out.println("Philosopher" + p + " picked up left chopstick");
//                else if (i == 1)
//                    System.out.println("Philosopher" + p + " picked up right chopstick");
//            } else if (semaphore.availablePermits() == 0){
//                if (i == 0)
//                    System.out.println("Philosopher" + p + " is in queue for left chopstick");
//                else if (i == 1)
//                    System.out.println("Philosopher" + p + " is in queue for right chopstick");
//            }
//        }
//
//
//        void dropChopstick(int p,int i) {
//            semaphore.release();
//            if (i == 0)
//                System.out.println("Philosopher " + p + " released left chopstick");
//            else if (i == 1)
//                System.out.println("Philosopher " + p + " released right chopstick");
//
//        }
}

