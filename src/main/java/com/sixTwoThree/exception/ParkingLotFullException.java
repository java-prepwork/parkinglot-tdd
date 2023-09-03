package com.sixTwoThree.exception;

import com.sixTwoThree.Parkable;
import com.sixTwoThree.ParkingLot;

public class ParkingLotFullException extends Exception{
    public ParkingLotFullException(String message)
    {
        super(message);
    }

}
