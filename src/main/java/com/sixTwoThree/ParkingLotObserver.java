package com.sixTwoThree;

public interface ParkingLotObserver {
    void notifyWhenParkingLotIsFull(ParkingLot parkingLot);
    void notifyWhenParkingLotGetsFree(ParkingLot parkingLot);
}
