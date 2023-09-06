package com.sixTwoThree;

import com.sixTwoThree.exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotAttendant implements ParkingLotObserver{

    private final List<ParkingLot> availableLots = new ArrayList<>();
    private final Map<Parkable, ParkingLot> parkingLotAllocation = new HashMap<>();

    public void isResponsibleFor(ParkingLot parkingLot) {
        parkingLot.assignObserver(this);
        if (!parkingLot.isFull())
        {
            availableLots.add(parkingLot);
        }

    }

    public void directs(Parkable carToBeParked) throws ParkingLotFullException, AlreadyParkedException, AllLotsFullException {
        if (parkingLotAllocation.containsKey(carToBeParked))
        {
            throw new AlreadyParkedException("Already parked");
        }

        if (availableLots.isEmpty())
        {
            throw new AllLotsFullException("All managed lots are full");
        }

        ParkingLot lotToBeParked = availableLots.get(0);
        lotToBeParked.park(carToBeParked);
        parkingLotAllocation.put(carToBeParked, lotToBeParked);
    }

    public void unparks(Parkable carToBeUnParked) throws NotParkedException, ParkingLotEmptyException {
        if(!parkingLotAllocation.containsKey(carToBeUnParked)) {
            throw new NotParkedException("The car is not parked");
        }

        ParkingLot lotToBeUnparkedFrom = parkingLotAllocation.get(carToBeUnParked);
        lotToBeUnparkedFrom.unpark(carToBeUnParked);
        parkingLotAllocation.remove(carToBeUnParked);
    }

    @Override
    public void notifyWhenParkingLotIsFull(ParkingLot parkingLot) {
        availableLots.remove(parkingLot);
    }

    @Override
    public void notifyWhenParkingLotIsAvailable(ParkingLot parkingLot) {
        availableLots.add(parkingLot);
    }


}
