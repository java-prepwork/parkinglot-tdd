package com.sixTwoThree;

import exception.AlreadyParkedException;
import exception.ParkingLotFullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ParkingLotTest {

    ParkingLot parkingLotOne = new ParkingLot(1);
    @Test
    void ToParkACarWhenTheParkingLotHasSpace(){
        Parkable carOne = new Car();
        try{
            parkingLotOne.park(carOne);
        }
        catch(Exception exception){
            /** instead of assertThrow */
            fail("");
        }
    }

    @Test
    void toThrowExceptionWhenTheParkingLotIsFull() throws ParkingLotFullException, AlreadyParkedException {
         Parkable carOne = new Car();
         Parkable carTwo = new Car();
         parkingLotOne.park(carOne);

         assertThrows(ParkingLotFullException.class,()-> parkingLotOne.park(carTwo));
    }

    @Test
    void toThrowExceptionWhenTheCarIsAlreadyParked() throws ParkingLotFullException, AlreadyParkedException {
        Parkable carOne = new Car();
        parkingLotOne.park(carOne);

        assertThrows(AlreadyParkedException.class, ()-> parkingLotOne.park(carOne));
    }
}
