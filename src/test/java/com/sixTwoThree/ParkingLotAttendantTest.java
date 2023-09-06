package com.sixTwoThree;

import com.sixTwoThree.exception.AllParkingLotFullException;
import com.sixTwoThree.exception.AlreadyParkedException;
import com.sixTwoThree.exception.ParkingLotFullException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ParkingLotAttendantTest {

    static Parkable carOne;
    static Parkable carTwo;
    static Parkable carThree;
    static Parkable carFour;
    ParkingLotAttendant parkingLotAttendant;
    ParkingLot parkingLotOne;
    ParkingLot parkingLotTwo;
    ParkingLot parkingLotThree;

    @BeforeAll
    static void beforeAll(){
        carOne = mock(Parkable.class);
        carTwo = mock(Parkable.class);
        carThree = mock(Parkable.class);
        carFour = mock(Parkable.class);
    }

    @BeforeEach
    void beforeEach(){
       parkingLotAttendant = new ParkingLotAttendant();
       parkingLotOne = new ParkingLot(1);
       parkingLotTwo = new ParkingLot(2);
       parkingLotThree = new ParkingLot(3);
    }

    @Nested
    class Parking{
        @Test
        void toThrowParkingLotFullException() throws ParkingLotFullException, AlreadyParkedException, AllParkingLotFullException {
            parkingLotAttendant.isResponsibleFor(parkingLotOne);
            parkingLotAttendant.isResponsibleFor(parkingLotTwo);
            parkingLotAttendant.directs(carOne);

            assertThrows(ParkingLotFullException.class, ()-> parkingLotOne.park(carTwo));
        }

        @Test
        void toThrowExceptionWhenAllTheParkingLotIsFull() throws ParkingLotFullException, AlreadyParkedException, AllParkingLotFullException {
            parkingLotAttendant.isResponsibleFor(parkingLotOne);
            parkingLotAttendant.isResponsibleFor(parkingLotTwo);
            parkingLotAttendant.directs(carOne);
            parkingLotAttendant.directs(carTwo);
            parkingLotAttendant.directs(carThree);


            assertThrows(AllParkingLotFullException.class,()-> parkingLotAttendant.directs(carFour));
        }

        @Test
        void toThrowExceptionWhenTheAlreadyParkedCarIsAgainParked() throws ParkingLotFullException, AlreadyParkedException, AllParkingLotFullException {
            parkingLotAttendant.isResponsibleFor(parkingLotTwo);
            parkingLotAttendant.directs(carOne);

            assertThrows(AlreadyParkedException.class, ()-> parkingLotTwo.park(carOne));
        }

        @Test
        void toParkTheCarInTheNextAvailableLot() throws ParkingLotFullException, AlreadyParkedException, AllParkingLotFullException {
            parkingLotAttendant.isResponsibleFor(parkingLotTwo);
            parkingLotAttendant.isResponsibleFor(parkingLotOne);
            parkingLotAttendant.isResponsibleFor(parkingLotThree);

            parkingLotAttendant.directs(carOne);
            parkingLotAttendant.directs(carTwo);
            parkingLotAttendant.directs(carThree);

            assertThrows(ParkingLotFullException.class, ()-> parkingLotOne.park(carFour));
        }

        @Test
        void toParkTheCarInTheFirstlyManagedLot() throws ParkingLotFullException, AlreadyParkedException, AllParkingLotFullException {
            parkingLotAttendant.isResponsibleFor(parkingLotTwo);
            parkingLotAttendant.isResponsibleFor(parkingLotThree);

            parkingLotAttendant.directs(carOne);

            assertThrows(AlreadyParkedException.class, ()-> parkingLotTwo.park(carOne));
        }
    }


}
