package com.sixTwoThree;

import exception.AlreadyParkedException;
import exception.NotParkedException;
import exception.ParkingLotEmptyException;
import exception.ParkingLotFullException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

public class ParkingLotTest {

    private ParkingLot parkingLotOne;
    private ParkingLot parkingLotTwo;


    /** Everytime when we're testing the parking lot must be considered to be empty,
     * so we are using beforeEach to reassigning the object */
    @BeforeEach
    void beforeEach(){
        parkingLotOne = new ParkingLot(1);
        parkingLotTwo = new ParkingLot(2);
    }
    static Parkable carOne;
    static Parkable carTwo;
    /** --@BeforeAll is static
     *  --because, */
    @BeforeAll
    static void beforeAll(){
        /** --to achieve Fewest Element and Reveals Intention
         *  --we're using mock() class from mockito framework
         *  --here, parkable interface is mocked as a class
         *  --so, we didn't need class car, because car doesn't have any attributes or behaviours*/

        carOne = mock(Parkable.class);
        carTwo = mock(Parkable.class);
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

    @Test
    void toUnParkACarWhenTheCarIsAlreadyParked(){
        try{
            parkingLotOne.park(carOne);
            parkingLotOne.unpark(carOne);
        }
        catch(Exception exception)
        {
            fail("car can't be un parked");
        }
    }

    @Test
    void toThrowExceptionWhenTheCarIsNotParked() throws ParkingLotFullException, AlreadyParkedException, NotParkedException, ParkingLotEmptyException {
        parkingLotTwo.park(carOne);
        parkingLotTwo.park(carTwo);
        parkingLotTwo.unpark(carOne);
        assertThrows(NotParkedException.class,()-> parkingLotTwo.unpark(carOne));
    }

    @Test
    void toThrowExceptionWhenTheParkingLotIsEmpty(){
        assertThrows(ParkingLotEmptyException.class,()->parkingLotOne.unpark(carOne));
    }
}
