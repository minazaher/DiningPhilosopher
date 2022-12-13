package com.company.RealWorldApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static int no_of_customers = 4;
    public static Resource[] resources;
    public static Customer[] customers;
    public static int TYPE_FAMILY = 0;
    public static int TYPE_FRIENDS = 1;
    public static int TYPE_SINGLE = 2;
    public static int TYPE_COUPLE = 3;
    static ExecutorService executor = Executors.newFixedThreadPool(no_of_customers);


    public static void main(String[] args) throws InterruptedException {
        customers = new Customer[no_of_customers];
        resources =new Resource[no_of_customers];

        for (int i=0; i<no_of_customers;i++){
            if (i % 2 == 0)
                resources[i] =new Room();
            else
                resources[i] =new Car();

        }

        for(int i=0;i<no_of_customers ;i++)
        {
            switch (i) {
                case 0 -> customers[i] = new Customer(i, TYPE_FAMILY, resources);
                case 1 -> customers[i] = new Customer(i, TYPE_FRIENDS, resources);
                case 2 -> customers[i] = new Customer(i, TYPE_SINGLE, resources);
                case 3 -> customers[i] = new Customer(i, TYPE_COUPLE, resources);
                default -> System.out.println("Can't Allocate User Type");
            }
            executor.execute(customers[i]);

        }

        executor.shutdown();

    }
}