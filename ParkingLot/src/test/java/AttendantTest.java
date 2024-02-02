import org.example.*;
import org.example.coustomException.CarNotFoundException;
import org.example.coustomException.SlotIsOccupiedException;
import org.example.coustomException.SlotNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AttendantTest {
    @Test
    public void shouldParkCarUsingParkingManager() throws Exception {
        Attendant attendant = new Attendant();
        ParkingLot parkingLot = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        attendant.assign(parkingLot);
        attendant.assign(parkingLot2);
        Car car = new Car("PK89GF5678", "Red");

        when(parkingLot.park(any(Car.class), any(Strategy.class))).thenReturn("1");
        attendant.park(car);

        verify(parkingLot, times(1)).park(car, Strategy.NEAREST);
        verify(parkingLot2, never()).park(car);
    }

    @Test
    public void shouldNotParkCarInUnAvailableSlot() throws Exception {
        Attendant attendant = new Attendant();
        ParkingLot parkingLot = new ParkingLot(1);
        attendant.assign(parkingLot);
        Car car = new Car("UP89JJ8907", "Blue");

        attendant.park(car);

        assertThrows(SlotNotFoundException.class, () -> attendant.park(car));
    }

    @Test
    public void shouldUnParkTheCar() throws Exception {
        Attendant attendant = new Attendant();
        ParkingLot parkingLot = new ParkingLot(1);
        attendant.assign(parkingLot);
        Car car = new Car("PL87HJ8767", "Yellow");
        String id = attendant.park(car);

        Car expectedCar = attendant.unPark(id);

        assertEquals(expectedCar, car);
    }

    @Test
    public void shouldNotUnParkTheCarWithInvalidId() throws Exception {
        Attendant attendant = new Attendant();
        ParkingLot parkingLot = new ParkingLot(1);
        attendant.assign(parkingLot);
        Car car = new Car("KL87HJ7890", "Green");
        attendant.park(car);

        assertThrows(CarNotFoundException.class, () -> attendant.unPark("1"));
    }

    @Test
    public void shouldParkCarInFirstParkingLotWhenAllParkingLotIsAvailable() throws SlotNotFoundException {
        Attendant attendant = new Attendant();
        ParkingLot parkingLot = new ParkingLot(1);
        attendant.assign(parkingLot);
        ParkingLot parkingLot1 = new ParkingLot(1);
        attendant.assign(parkingLot1);
        Car car = new Car("OP78HJ7890", "Green");

        attendant.park(car);

        assertTrue(parkingLot.isFull());
    }

    @Test
    public void multipleAttendantCanBeAssignToSingleParkingLot() throws SlotNotFoundException, SlotIsOccupiedException {
        ParkingLot parkingLot = mock(ParkingLot.class);
        Attendant attendant = new Attendant();
        Attendant attendant1 = new Attendant();
        Car car = new Car("OP89JK8975", "Black");
        Car car1 = new Car("OP89JK8976", "Green");

        when(parkingLot.park(any(Car.class), any(Strategy.class))).thenReturn("1");
        attendant.assign(parkingLot);
        attendant1.assign(parkingLot);
        attendant.park(car);
        attendant1.park(car1);

        verify(parkingLot, times(1)).park(car, Strategy.NEAREST);
        verify(parkingLot, times(1)).park(car1, Strategy.NEAREST);
    }

    @Test
    public void oneAttendantCanParkInMultipleParkingLot() throws SlotNotFoundException {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        Attendant attendant = new Attendant();
        attendant.assign(parkingLot);
        attendant.assign(parkingLot2);
        Car car = new Car("OP89JK9876", "Blue");
        Car car1 = new Car("PO99JK0987", "Green");

        attendant.park(car);
        attendant.park(car1);

        assertTrue(parkingLot.isFull());
        assertTrue(parkingLot2.isFull());
    }


    @Test
    public void attendantCanUnparkCarWhichParkedByOtherAttendant() throws SlotNotFoundException, CarNotFoundException {
        ParkingLot parkingLot = new ParkingLot(2);
        Attendant attendant = new Attendant();
        Attendant attendant1 = new Attendant();
        attendant.assign(parkingLot);
        attendant1.assign(parkingLot);
        Car car = new Car("KA45GH7654", "Green");
        Car car2 = new Car("HR56GH6543", "Blue");

        String id = attendant.park(car);
        attendant1.park(car2);
        Car unParkedCar = attendant1.unPark(id);

        assertEquals(car, unParkedCar);
    }

    @Test
    public void makeParkingLotFullAndUnparkCarAndParkTheCar() throws SlotNotFoundException, CarNotFoundException {
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingLot parkingLot1 = new ParkingLot(2);
        Attendant attendant = new Attendant();
        attendant.assign(parkingLot);
        attendant.assign(parkingLot1);
        Car car = new Car("UL76GJ8763", "Red");
        Car car1 = new Car("DP76HJ8760", "Pink");
        Car car2 = new Car("NP76HJ8761", "Green");
        Car car3 = new Car("KP77HJ8765", "Yellow");

        String id = attendant.park(car);
        attendant.park(car1);
        attendant.park(car2);
        attendant.unPark(id);
        attendant.park(car3);

        assertTrue(parkingLot.isFull());
    }

    @Test
    public void parkCarUsingNearestStrategy() throws SlotNotFoundException {
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingLot parkingLot1 = new ParkingLot(2);
        Attendant attendant = new Attendant(Strategy.NEAREST);
        attendant.assign(parkingLot);
        attendant.assign(parkingLot1);
        Car car = new Car("UP76HJ8764", "Red");
        Car car1 = new Car("UP76HJ8761", "White");
        Car car2 = new Car("UP76JJ8765", "Black");

        attendant.park(car);
        attendant.park(car1);
        attendant.park(car2);

        assertFalse(parkingLot1.isFull());
        assertTrue(parkingLot.isFull());
    }

    @Test
    public void parkCarUsingFarthestStrategy() throws SlotNotFoundException {
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingLot parkingLot1 = new ParkingLot(2);
        Attendant attendant = new Attendant(Strategy.FARTHEST);
        attendant.assign(parkingLot);
        attendant.assign(parkingLot1);
        Car car = new Car("UP76LJ8765", "White");
        Car car1 = new Car("UP76QJ8765", "White");
        Car car2 = new Car("UP76HR8765", "White");

        attendant.park(car);
        attendant.park(car1);
        attendant.park(car2);

        assertTrue(parkingLot1.isFull());
        assertFalse(parkingLot.isFull());
    }

    @Test
    public void notifyAttendantWhenParkingLotIsFull() throws SlotNotFoundException, SlotIsOccupiedException {
        ParkingLot parkingLot = new ParkingLot(1);
        Observer attendant = mock(Attendant.class);
        Observer attendant1 = mock(Attendant.class);
        NotificationBus.getInstance().subscribe(attendant, ParkingLotEvent.FULL);
        NotificationBus.getInstance().subscribe(attendant1, ParkingLotEvent.FULL);
        Car car1 = new Car("UP76HL8765", "White");

        parkingLot.park(car1);

        verify(attendant).notify(ParkingLotEvent.FULL, parkingLot);
        verify(attendant1).notify(ParkingLotEvent.FULL, parkingLot);
    }

    @Test
    public void notifyAttendantWhenParkingLotIsEmpty() throws SlotNotFoundException, SlotIsOccupiedException, CarNotFoundException {
        ParkingLot parkingLot = new ParkingLot(1);
        Observer attendant = mock(Attendant.class);
        Observer attendant1 = mock(Attendant.class);
        NotificationBus.getInstance().subscribe(attendant, ParkingLotEvent.EMPTY);
        NotificationBus.getInstance().subscribe(attendant1, ParkingLotEvent.EMPTY);
        Car car1 = new Car("UP76HO8765", "White");

        String id = parkingLot.park(car1);
        parkingLot.unPark(id);

        verify(attendant).notify(ParkingLotEvent.EMPTY, parkingLot);
        verify(attendant1).notify(ParkingLotEvent.EMPTY, parkingLot);
    }

    @Test
    public void changeStrategyAndParkCar() throws SlotNotFoundException {
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingLot parkingLot1 = new ParkingLot(2);
        Attendant attendant = new Attendant(Strategy.NEAREST);
        attendant.assign(parkingLot);
        attendant.assign(parkingLot1);
        Car car = new Car("PP76HJ8765", "White");
        Car car1 = new Car("JP76HJ8765", "White");
        Car car2 = new Car("KP76HJ8765", "White");

        attendant.park(car);
        attendant.changeStrategy(Strategy.FARTHEST);
        attendant.park(car1);
        attendant.park(car2);

        assertTrue(parkingLot1.isFull());
        assertFalse(parkingLot.isFull());
    }

    @Test
    public void parkCarsUsingDistributedStrategy() throws SlotNotFoundException {
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingLot parkingLot1 = new ParkingLot(2);
        Attendant attendant = new Attendant(Strategy.DISTRIBUTED);
        attendant.assign(parkingLot);
        attendant.assign(parkingLot1);
        Car car = new Car("UP76HK8765", "Red");
        Car car1 = new Car("UP76TJ8765", "Red");

        attendant.park(car);
        attendant.park(car1);

        assertEquals(1, parkingLot1.emptySlotCount());
        assertEquals(1, parkingLot.emptySlotCount());
    }
}