package com.sixTwoThree;

import exception.AlreadyParkedException;
import exception.ParkingLotFullException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ParkingLotTest {

    private ParkingLot parkingLotOne ;

    /** Everytime when we're testing the parking lot must be considered to be empty,
     * so we are using beforeEach to reassigning the object */
    @BeforeEach
    void beforeEach(){
        parkingLotOne = new ParkingLot(1);
    }
    Parkable carOne;
    Parkable carTwo;
    @BeforeAll
    void beforeAll(){
        carOne = new Car();
        carTwo = new Car();
    }

    @Test
    void ToParkACarWhenTheParkingLotHasSpace(){
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
         parkingLotOne.park(carOne);

         assertThrows(ParkingLotFullException.class,()-> parkingLotOne.park(carTwo));
    }

    @Test
    void toThrowExceptionWhenTheCarIsAlreadyParked() throws ParkingLotFullException, AlreadyParkedException {
        parkingLotOne.park(carOne);

        assertThrows(AlreadyParkedException.class, ()-> parkingLotOne.park(carOne));
    }
}
