package com.company.NotRealWorldApplication;

import java.util.concurrent.Semaphore;

public class Chopstick {
    public Semaphore semaphore = new Semaphore(1);

    public synchronized void pick() throws InterruptedException {
        while (this.semaphore.availablePermits() == 0) {
            try {
                System.out.println("chopStick in use ");
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
            System.out.println("Philosopher " + ID + " releases the chopstick: " + ID);
        else
            System.out.println("Philosopher " + ID + " releases the chopstick: " + (ID + 1) % 5);
    }

}

