package com.sixTwoThree;

import exception.AlreadyParkedException;
import exception.ParkingLotFullException;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private final int capacity;

    Set<Parkable> storage = new HashSet<>();
    public ParkingLot(int parkingLotCapacity) {
        this.capacity = parkingLotCapacity;
    }

    public void park(Parkable carToBeParked) throws ParkingLotFullException, AlreadyParkedException {
        if(storage.contains(carToBeParked))
        {
            throw new AlreadyParkedException("Can't park an already parked car");
        }
        if(capacity == storage.size())
        {
            throw new ParkingLotFullException("Parking Lot doesn't have enough space");
        }
        storage.add(carToBeParked);
    }
}




