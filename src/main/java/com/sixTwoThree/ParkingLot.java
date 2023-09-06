package com.sixTwoThree;

import com.sixTwoThree.exception.AlreadyParkedException;
import com.sixTwoThree.exception.NotParkedException;
import com.sixTwoThree.exception.ParkingLotEmptyException;
import com.sixTwoThree.exception.ParkingLotFullException;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private final int parkingLotCapacity;
    ParkingLotObservers parkingLotObservers = new ParkingLotObservers();
    TrafficPolice police;

    Set<Parkable> parkingLotStorage = new HashSet<>();
    public ParkingLot(int parkingLotCapacity) {
        this.parkingLotCapacity = parkingLotCapacity;
    }

    boolean isFull(){
        return parkingLotCapacity == parkingLotStorage.size();
    }
    private boolean fullBeforeUnpark(){
        return (parkingLotCapacity - 1) == parkingLotStorage.size();
    }

    public void park(Parkable carToBeParked) throws ParkingLotFullException, AlreadyParkedException {
        if(parkingLotStorage.contains(carToBeParked))
        {
            throw new AlreadyParkedException("Can't park an already parked car");
        }
        if(isFull())
        {
            throw new ParkingLotFullException("Parking Lot doesn't have enough space");
        }
        parkingLotStorage.add(carToBeParked);
        if(isFull() )
        {
            parkingLotObservers.notifyAllObserver(this);
        }
    }

    public void unpark(Parkable carToBeUnParked) throws NotParkedException, ParkingLotEmptyException {
        if(parkingLotStorage.isEmpty())
        {
            throw new ParkingLotEmptyException("The parking lot is already empty");
        }
        if(!parkingLotStorage.contains(carToBeUnParked)) {
            throw new NotParkedException("The car is not parked.");
        }
        parkingLotStorage.remove(carToBeUnParked);
        if (fullBeforeUnpark())
        {
            parkingLotObservers.notifyWhenParkingLotIsAvailable(this);
        }
    }

    public void assignObserver(ParkingLotObserver parkingLotObserver) {
        parkingLotObservers.add(parkingLotObserver);
    }


}




