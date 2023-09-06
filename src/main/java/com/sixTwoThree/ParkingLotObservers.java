package com.sixTwoThree;

import java.util.ArrayList;

public class ParkingLotObservers extends ArrayList<ParkingLotObserver> {


    public void notifyAllObserver(ParkingLot parkingLot) {
        for(ParkingLotObserver parkingLotObserver : this){
            parkingLotObserver.notifyWhenParkingLotIsFull(parkingLot);
        }
    }

    public void notifyWhenParkingLotIsAvailable(ParkingLot parkingLot) {
        for(ParkingLotObserver parkingLotObserver : this){
            parkingLotObserver.notifyWhenParkingLotIsAvailable(parkingLot);
        }
    }
}
