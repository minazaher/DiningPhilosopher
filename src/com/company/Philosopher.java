package com.company;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {

    boolean IsEven = false;
    int times_eaten = 0;
    private final Chopstick[] chopsticks;
    private final int ID;


    public Philosopher(int ID, Chopstick[] chopsticks) {
        this.ID = ID;
        this.chopsticks = chopsticks;
    }



    @Override
    public void run() {
        for (int i = 0; i < 50; i++)
        {
            System.out.println("philosopher " + ID + " thinking");
            try {
                Thread.sleep(new Random().nextInt(100) + 50);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Philosopher " + ID + " hungry.");
            if (this.getRight(ID).semaphore.availablePermits() == 1 && this.getLeft(ID).semaphore.availablePermits() == 1) {
                try {
                    chopsticks[ID].pick();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Philosopher" + ID + "  takes the chopstick: " + ID);
                try {
                    chopsticks[(ID + 1) % 5].pick();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Philosopher" + ID + "  takes the chopstick: " + (ID+1)%5);
                System.out.println("Philosophers " + ID + " eating");
                try {
                    Thread.sleep(new Random().nextInt(100) + 50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                chopsticks[ID].leave(ID, true);// release right chopstick
                chopsticks[(ID + 1) % 5].leave(ID, false);//release left chopstick
                this.times_eaten++;
                try {
                    Thread.sleep(new Random().nextInt(100) + 50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else
                System.out.println("chopStick for philosopher "+ ID + "is in use");
            }
        }

        public Chopstick getRight ( int myName){
            return chopsticks[myName];
        }

        public Chopstick getLeft ( int myName){
            return chopsticks[(myName + 1) % 5];
        }


    }


