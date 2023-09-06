package com.sixTwoThree;

import com.sixTwoThree.exception.AllParkingLotFullException;
import com.sixTwoThree.exception.AlreadyParkedException;
import com.sixTwoThree.exception.ParkingLotFullException;

import java.util.*;

public class ParkingLotAttendant implements ParkingLotObserver {

    List<ParkingLot> availableLots = new ArrayList<>();

    public void isResponsibleFor(ParkingLot lotToBeAttend) {
        lotToBeAttend.assign(this);
        availableLots.add(lotToBeAttend);
    }

    public void directs(Parkable carToDirect) throws ParkingLotFullException, AlreadyParkedException, AllParkingLotFullException {

        if(availableLots.isEmpty())
        {
            throw new AllParkingLotFullException("All parking lots are full");
        }
        availableLots.get(0).park(carToDirect);


    }

    @Override
    public void notifyWhenParkingLotIsFull(ParkingLot parkingLot) {
        availableLots.remove(parkingLot);
    }

    @Override
    public void notifyWhenParkingLotGetsFree(ParkingLot parkingLot) {

    }
}
