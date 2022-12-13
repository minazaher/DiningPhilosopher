package com.company.RealWorldApplication;

import java.util.Random;

public class Customer extends Thread{

    private int ID;
    private int type;
    private Resource [] resources;

    public Customer(int ID, int type, Resource[] resources) {
        this.ID = ID;
        this.type = type;
        this.resources = resources;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++)
        {
            System.out.println("Customer " + ID + " entered the website");
            try {
                Thread.sleep(new Random().nextInt(100) + 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Customer " + ID + " wants to reserve.");
            if (canReserve()) {
                    try {
                        resources[ID].reserve();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Customer " + ID + "  reserved the : " + resources[ID].getClass().getSimpleName());
                    try {
                        resources[(ID + 1) % Main.no_of_customers].reserve();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Customer " + ID + "  reserved the resource : " + resources[(ID + 1) % Main.no_of_customers].getClass().getSimpleName());
                System.out.println("Customer " + ID + " reservation done");
                try {
                    Thread.sleep(new Random().nextInt(100) + 50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resources[ID].leave(ID, true, resources[ID].getClass().getSimpleName());// release right chopstick
                resources[(ID + 1) % Main.no_of_customers].leave(ID, false, resources[(ID + 1) % Main.no_of_customers].getClass().getSimpleName());//release left chopstick
                try {
                    Thread.sleep(new Random().nextInt(100) + 50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else
                System.out.println("Resources for Customer "+ ID + " are reserved");
        }
    }


    public Resource getRight ( int id){
        return resources[id];
    }

    public Resource getLeft ( int id){
        return resources[(id + 1) % Main.no_of_customers];
    }

    public boolean canReserve(){
        return (this.getRight(ID).semaphore.availablePermits() == 1 && this.getLeft(ID).semaphore.availablePermits() == 1);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Resource[] getResources() {
        return resources;
    }

    public void setResources(Resource[] resources) {
        this.resources = resources;
    }

}
