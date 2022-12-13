package com.company.RealWorldApplication;

public class Car extends Resource{
    private String type;
    private int NumberOfDoors;

    public Car() {
        super();
    }

    public Car(String type, int numberOfDoors) {
        super();
        this.type = type;
        NumberOfDoors = numberOfDoors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfDoors() {
        return NumberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        NumberOfDoors = numberOfDoors;
    }


}
