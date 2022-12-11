package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class Philosopher implements Runnable {
        Chopstick leftChopstick;
        Chopstick rightChopstick;
        private final int number;
        boolean IsEven = false;

        int times_eaten = 0;

        public Philosopher(boolean IsEven,int num, Chopstick left, Chopstick right) {
            this.IsEven = IsEven;
            number = num;
            leftChopstick = left;
            rightChopstick = right;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1; i++) {
                performAction(" thinks for " , this.number);
                if (this.IsEven){
                    try {
                        rightChopstick.grabChopstick((this.number),1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        leftChopstick.grabChopstick((this.number),0);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    try {
                        leftChopstick.grabChopstick((this.number),0);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    rightChopstick.grabChopstick((this.number),1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                performAction(" eats for ", this.number);
                leftChopstick.dropChopstick((this.number),0);
                rightChopstick.dropChopstick((this.number),1);
                System.out.println("This philosopher "+ (this.number) +" has eaten for "+this.times_eaten +" times");
            }
        }


        void performAction(String action, int p) {
            int waitTime = ThreadLocalRandom.current().nextInt(0,
                    1000);
            if (action.equals(" eats for ")){
                this.times_eaten ++;
            }
            System.out.println("Philosopher " + (p) + action
                    + waitTime + " ms");
        }

    }

