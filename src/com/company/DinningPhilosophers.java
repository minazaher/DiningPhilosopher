package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DinningPhilosophers {
    public static Chopstick[] chopsticks;

    static ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException {
        Philosopher philosopher[];
        chopsticks=new Chopstick[5];

        for (int i=0; i<5;i++){
            chopsticks[i] =new Chopstick();
        }

        philosopher = new Philosopher[5];

        for(int i=0;i<5;i++)
        {
            if(i % 2 == 0 ){
                philosopher[i] = new Philosopher(i,chopsticks, true);
            }
            else{
                philosopher[i] = new Philosopher(i,chopsticks, false);

            }
            executor.execute(philosopher[i]);
        }

//        for(int i=0;i<5;i++)
//        {
//                System.out.println("Philosopher Number " + i + " has eaten " + philosopher[i].getTimes_eaten());
//        }
    }
}