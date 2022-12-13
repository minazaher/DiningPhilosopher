package com.company.RealWorldApplication;

import java.util.concurrent.Semaphore;

public abstract class Resource {
    public Semaphore semaphore = new Semaphore(1) ;

    public synchronized void reserve() throws InterruptedException {
        while (this.semaphore.availablePermits() == 0) {
            try {
                wait(); //The calling thread waits until semaphore becomes free
            } catch (InterruptedException e) {
            }
        }
        this.semaphore.acquire();
    }

    public synchronized void leave(int ID, boolean isRight, String c) {
        this.semaphore.release();
        notify();
        if (isRight)
            System.out.println("Customer " + ID + " leaves the resource: " + c);
        else
            System.out.println("Customer " + ID + " leaves the resource: " + c);
    }

}
