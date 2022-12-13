package com.company.NotRealWorldApplication;

import java.util.Random;

public class Philosopher extends Thread {
    private final int ID;
    private final Chopstick[] chopsticks;
    boolean IsEven = false;
    private int times_eaten = 0;


    public Philosopher(int ID, Chopstick [] chopsticks, boolean isEven){
        this.ID = ID;
        this.chopsticks = chopsticks;
        this.IsEven = isEven;
    }

    public int getTimes_eaten() {
        return times_eaten;
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
            if (canEat()) {
                if(IsEven) {
                    try {
                        chopsticks[ID].pick();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Philosopher " + ID + "  takes the chopstick: " + ID);
                    try {
                        chopsticks[(ID + 1) % 5].pick();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Philosopher " + ID + "  takes the chopstick: " + (ID + 1) % 5);
                }
                else{
                    try {
                        chopsticks[(ID + 1) % 5].pick();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Philosopher " + ID + "  takes the chopstick: " + (ID + 1) % 5);

                    try {
                        chopsticks[ID].pick();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Philosopher " + ID + "  takes the chopstick: " + ID);
                }

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
                System.out.println("Chopstick for philosopher "+ ID + " is in use");
        }
        }


        public Chopstick getRight ( int id){
            return chopsticks[id];
        }

        public Chopstick getLeft ( int id){
            return chopsticks[(id + 1) % 5];
        }

        public boolean canEat(){
            return (this.getRight(ID).semaphore.availablePermits() == 1 && this.getLeft(ID).semaphore.availablePermits() == 1);
        }
}


