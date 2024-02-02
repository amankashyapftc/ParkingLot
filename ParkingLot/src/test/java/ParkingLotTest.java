import org.example.*;

import org.example.coustomException.SlotNotFoundException;
import org.example.coustomException.CarNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    public void validParkingLot() {
        assertDoesNotThrow(() -> new ParkingLot(1));
    }

    @Test
    public void inValidParkingLot() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ParkingLot(0));
        String actual = exception.getMessage();
        String expected = "Number of slots should be greater than zero.";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldParkCarInAvailableParkingSlot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("UP78HJ6789", "Blue");

        parkingLot.park(car);

        assertTrue(parkingLot.isFull());
    }

    @Test
    public void shouldNotParkCarWhenParkingLotIsFull() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car("UP78GH7890", "Green");
        Car car2 = new Car("UP32GH6545", "White");
        parkingLot.park(car1);

        assertThrows(SlotNotFoundException.class, () -> parkingLot.park(car2));
    }

    @Test
    public void shouldUnParkTheCar() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car1 = new Car("PJ78HJ6765", "Blue");
        String id = parkingLot.park(car1);

        Car car = parkingLot.unPark(id);

        assertEquals(car1, car);
    }

    @Test
    public void shouldNotUnParkTheCarWithInvalidId() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car1 = new Car("GJ67HG6543", "Red");
        parkingLot.park(car1);

        assertThrows(CarNotFoundException.class, () -> parkingLot.unPark("123"));
    }
}