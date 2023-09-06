package com.sixTwoThree;

import com.sixTwoThree.exception.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class ParkingLotAttendantTest {
    private ParkingLotAttendant attendant;
    private ParkingLot parkingLotOne;
    private ParkingLot parkingLotTwo;
    private ParkingLot parkingLotThree;
    static Parkable carOne;
    static Parkable carTwo;
    static Parkable carThree;
    static Parkable carFour;
    static Parkable carFive;
    static Parkable carSix;
    static Parkable carSeven;

    @BeforeEach
    void beforeEach(){
        attendant = new ParkingLotAttendant();
        parkingLotOne = new ParkingLot(1);
        parkingLotTwo = new ParkingLot(2);
        parkingLotThree = new ParkingLot(3);
    }

    @BeforeAll
    static void beforeAll()
    {
        carOne = mock(Parkable.class);
        carTwo = mock(Parkable.class);
        carThree = mock(Parkable.class);
        carFour = mock(Parkable.class);
        carFive = mock(Parkable.class);
        carSix = mock(Parkable.class);
        carSeven = mock(Parkable.class);
    }

    @Nested
    class Parking{
        @Test
        void toParkTheCarInTheOnlyManagedLot() throws ParkingLotFullException, AlreadyParkedException, AllLotsFullException {
            attendant.isResponsibleFor(parkingLotOne);

            attendant.directs(carOne);

            assertThrows(ParkingLotFullException.class, () -> parkingLotOne.park(carTwo));
        }

        @Test
        void toParkTheCarInTheFirstlyManagedLot() throws AllLotsFullException, ParkingLotFullException, AlreadyParkedException {
            attendant.isResponsibleFor(parkingLotTwo);
            attendant.isResponsibleFor(parkingLotThree);

            attendant.directs(carOne);

            assertThrows(AlreadyParkedException.class, ()-> parkingLotTwo.park(carOne));
        }

        @Test
        void toParkTheCarInTheNextAvailableLot() throws AllLotsFullException, ParkingLotFullException, AlreadyParkedException {
            attendant.isResponsibleFor(parkingLotTwo);
            attendant.isResponsibleFor(parkingLotOne);
            attendant.isResponsibleFor(parkingLotThree);

            attendant.directs(carOne);
            attendant.directs(carTwo);
            attendant.directs(carThree);

            assertThrows(ParkingLotFullException.class, () -> parkingLotOne.park(carFour));
        }

        @Test
        void toThrowExceptionWhenAllTheManagedLotsAreFull() throws AllLotsFullException, ParkingLotFullException, AlreadyParkedException {
            attendant.isResponsibleFor(parkingLotTwo);
            attendant.isResponsibleFor(parkingLotOne);

            attendant.directs(carOne);
            attendant.directs(carTwo);
            attendant.directs(carThree);

            assertThrows(AllLotsFullException.class, () -> attendant.directs(carFour));
        }

        @Test
        void toThrowExceptionWhenTriedToParkAnAlreadyParkedCar() throws AllLotsFullException, ParkingLotFullException, AlreadyParkedException {
            attendant.isResponsibleFor(parkingLotTwo);

            attendant.directs(carOne);

            assertThrows(AlreadyParkedException.class, () -> attendant.directs(carOne));
        }
    }

    @Nested
    class Unparking
    {
        @Test
        void toParkAnUnparkCarFromTheOnlyManagedLot() throws AllLotsFullException, ParkingLotFullException, AlreadyParkedException, NotParkedException, ParkingLotEmptyException {
            attendant.isResponsibleFor(parkingLotTwo);

            attendant.directs(carOne);
            attendant.unparks(carOne);
            attendant.directs(carOne);

            assertThrows(AlreadyParkedException.class, ()-> parkingLotTwo.park(carOne));
        }

        @Test
        void toParkACarAfterTheFullLotBecameAvailable() throws AllLotsFullException, ParkingLotFullException, AlreadyParkedException, NotParkedException, ParkingLotEmptyException {
            attendant.isResponsibleFor(parkingLotTwo);
            attendant.isResponsibleFor(parkingLotThree);

            attendant.directs(carOne);
            attendant.directs(carTwo);
            attendant.directs(carThree);
            attendant.directs(carFour);
            attendant.directs(carFive);

            attendant.unparks(carTwo);
            attendant.directs(carSix);
            attendant.unparks(carFive);

            assertThrows(ParkingLotFullException.class, ()-> parkingLotTwo.park(carSeven));
        }

        @Test
        void toThrowExceptionWhenAttendantUnparksANotParkedCar(){
            attendant.isResponsibleFor(parkingLotOne);

            assertThrows(NotParkedException.class, () -> attendant.unparks(carOne));
        }
    }


}
