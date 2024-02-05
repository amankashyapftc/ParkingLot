import org.example.Car;
import org.example.Slot;
import org.example.coustomException.CarNotFoundException;
import org.example.coustomException.SlotIsOccupiedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SlotTest {
    @Test
    public void validParkingSlot() {
        assertDoesNotThrow(Slot::new);
    }

    @Test
    public void isSlotAvailable() {
        Slot slot = new Slot();

        boolean actual = slot.isFree();
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldParkCar() throws Exception {
        Car car = new Car("UP78DJ5656", "Blue");
        Slot slot = new Slot();

        slot.park(car);

        boolean actual = slot.isFree();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotParkCarInUnAvailableSlot() throws Exception {
        Car car = new Car("UP78DJ5658", "Green");
        Slot slot = new Slot();
        slot.park(car);
        Car car2 = new Car("UP78DJ5659", "Red");

        assertThrows(SlotIsOccupiedException.class, () -> slot.park(car2));
    }

    @Test
    public void shouldUnParkTheCar() throws Exception {
        Slot slot = new Slot();
        Car car = new Car("UP78DJ5678", "Black");
        String id = slot.park(car);

        Car unParkedCar = slot.unPark(id);

        assertEquals(car, unParkedCar);
    }

    @Test
    public void shouldNotUnParkCarFromEmptySlot() {
        Slot slot = new Slot();

        assertThrows(CarNotFoundException.class, () -> slot.unPark("abc"));
    }

    @Test
    public void shouldNotUnParkCarWithInValidId() throws Exception {
        Slot slot = new Slot();
        Car car = new Car("UP78DJ5356", "Yellow");
        slot.park(car);

        assertThrows(UnsupportedOperationException.class, () -> slot.unPark("abc"));
    }

    @Test
    public void unParkTheInvalidCar() throws Exception {
        Slot slot = new Slot();
        Car car = new Car("UP89JK7654", "Blue");
        Car car2 = new Car("UP78DJ5690", "Yellow");
        String id = slot.park(car);

        Car unParkedCar = slot.unPark(id);

        assertNotEquals(car2, unParkedCar);
    }
}