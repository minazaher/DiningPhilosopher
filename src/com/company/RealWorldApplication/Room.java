package com.company.RealWorldApplication;

public class Room extends Resource{
    private int NumberOfBeds;


    public Room(int numberOfBeds) {
        super();
        NumberOfBeds = numberOfBeds;
    }

    public Room() {
        super();
    }

    public int getNumberOfBeds() {
        return NumberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        NumberOfBeds = numberOfBeds;
    }
}
