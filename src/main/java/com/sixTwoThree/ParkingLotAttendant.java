package com.sixTwoThree;

import com.sixTwoThree.exception.*;

import java.util.*;

public class ParkingLotAttendant implements ParkingLotObserver {

    List<ParkingLot> availableLots = new ArrayList<>();

    private final Map<Parkable, ParkingLot> parkingLotAllocation = new HashMap<>();

    public void isResponsibleFor(ParkingLot lotToBeAttend) {
        lotToBeAttend.assign(this);
        availableLots.add(lotToBeAttend);
    }

    public void directs(Parkable carToDirect) throws ParkingLotFullException, AlreadyParkedException, AllParkingLotFullException {

        if(parkingLotAllocation.containsKey(carToDirect))
        {
            throw new AlreadyParkedException("Already Parked");
        }
        if(availableLots.isEmpty())
        {
            throw new AllParkingLotFullException("All parking lots are full");
        }
        ParkingLot lotToBeParked = availableLots.get(0);
        lotToBeParked.park(carToDirect);
        parkingLotAllocation.put(carToDirect,lotToBeParked);



    }

    public void redirects(Parkable carToUnpark) throws NotParkedException, ParkingLotEmptyException {
        if(!parkingLotAllocation.containsKey(carToUnpark)){
            throw new NotParkedException("The car is not parked");
        }
        ParkingLot lotToBeUnparked = parkingLotAllocation.get(carToUnpark);
        lotToBeUnparked.unpark(carToUnpark);
        parkingLotAllocation.remove(carToUnpark);

    }

    @Override
    public void notifyWhenParkingLotIsFull(ParkingLot parkingLot) {
        availableLots.remove(parkingLot);
    }

    @Override
    public void notifyWhenParkingLotGetsFree(ParkingLot parkingLot) {
        availableLots.add(parkingLot);
    }


}
