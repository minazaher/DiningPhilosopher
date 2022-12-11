package com.company;

import java.util.concurrent.*;

public class DinningPhilosophers {
    static int no_of_philosophers = 5;
    static Philosopher[] philosophers = new Philosopher[no_of_philosophers];
    static Chopstick[] chopsticks = new Chopstick[no_of_philosophers];
    public static ExecutorService executor = Executors.newFixedThreadPool(no_of_philosophers);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < no_of_philosophers; i++) {
            chopsticks[i] = new Chopstick();
        }
        for (int i = 0; i < no_of_philosophers; i++) {
            if (i % 2 == 0)
                philosophers[i] = new Philosopher(true,i, chopsticks[i],chopsticks[(i + 1) % no_of_philosophers]);
            else
                philosophers[i] = new Philosopher(false,i, chopsticks[i], chopsticks[(i + 1) % no_of_philosophers]);
            executor.execute(philosophers[i]);
        }

        while (executor.isShutdown()) {
            for (Philosopher p : philosophers) {
                System.out.println(p.times_eaten);
            }
        }
    }


}


