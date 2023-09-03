package com.sixTwoThree;

import java.util.ArrayList;

public class ParkingLotObservers extends ArrayList<ParkingLotObserver> {

    public void notifyAllObserver() {
        for(ParkingLotObserver parkingLotObserver : this){
            parkingLotObserver.notifyWhenParkingLotIsFull();
        }
    }
}
