package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DinningPhilosophers {
    public static Chopstick[] chopsticks;
    public static void main(String[] args) throws InterruptedException {
        Philosopher philosopher[];

        //Create an array of five Semaphores object reference Handles
        chopsticks=new Chopstick[5];

        //Create five Semaphore objects and assign to the array
        for (int i=0; i<5;i++){
            chopsticks[i] =new Chopstick(); //Semaphore initial value=1

        }
        //Create an array of five philosopher thread object reference handles
        philosopher = new Philosopher[5];

        //Create and initiate five philosopher Thread objects
        for(int i=0;i<5;i++)
        {
            philosopher[i] = new Philosopher(i,chopsticks);
            philosopher[i].start();
        }
        for(int i=0;i<5;i++)
        {
            philosopher[i].join();
            System.out.println("Philosopher Number " + i + "has eaten " + philosopher[i].times_eaten);
        }
    }
}