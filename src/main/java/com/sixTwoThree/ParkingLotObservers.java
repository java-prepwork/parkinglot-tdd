package com.sixTwoThree;

import java.util.ArrayList;

public class ParkingLotObservers extends ArrayList<ParkingLotObserver> {

    public void notifyAllObserverWhenParkingLotIsFull(ParkingLot parkingLot) {
        for(ParkingLotObserver parkingLotObserver : this){
            parkingLotObserver.notifyWhenParkingLotIsFull(parkingLot);
        }
    }

    public void notifyAllObserverWhenParkingLotGetsFree(ParkingLot parkingLot) {
        for(ParkingLotObserver parkingLotObserver : this)
        {
            parkingLotObserver.notifyWhenParkingLotGetsFree(parkingLot);
        }
    }
}
