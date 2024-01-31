import org.example.Car;
import org.example.Slot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SlotTest {
    @Test
    public void testAbleToParkCarInBlankSlot() {
        Slot slot = new Slot(1);
        Car car = new Car("UP78DJ3587", "Blue");
        slot.parkCar(car);
        assertEquals(slot, new Slot(1, car));
    }

    @Test
    public void testWhileParkingParkCarReturnAToken() {
        Slot slot = new Slot(1);
        Car car = new Car("UP23GH1234","Red");
        String token = slot.parkCar(car);
        assertNotNull(token);
    }
    @Test
    public void testIfYouParkInvalidCarItWillThrowError() {
        Slot slot = new Slot(1);
        assertThrows(IllegalArgumentException.class, () -> {
            Car invalidCar = new Car("ABC123", "Black");
            slot.parkCar(invalidCar);
        });
    }

    @Test
    public void testIfYouParkCarWithNullColorItWillThrowError() {
        Slot slot = new Slot(1);
        assertThrows(IllegalArgumentException.class, () -> {
            Car invalidCar = new Car("UP78DJ3587", "");
            slot.parkCar(invalidCar);
        });
    }

    @Test
    public void testIfYouParkCarInAlreadyFilledSlotShouldThrowAnError() {
        Slot slot = new Slot(1);
        Car firstCar = new Car("LK34GH6754", "Blue");
        Car secondCar = new Car("UP30GT4532", "Red");

        slot.parkCar(firstCar);
        assertThrows(IllegalArgumentException.class, () -> slot.parkCar(secondCar));
    }

    @Test
    public void testCarIsPresentInSlotOrNot() {
        Slot parkingSlot = new Slot(1);
        Car car = new Car("UP78DJ3534", "Blue");

        parkingSlot.parkCar(car);

        assertTrue(parkingSlot.isCarPresent(car));
    }


    @Test
    public void testAbleToUnParkCarAfterVerifyingToken(){
        Slot parkingSlot = new Slot(1);
        Car car = new Car("AD23AS2345","Blue");


        String token = parkingSlot.parkCar(car);

        parkingSlot.unPark(token);
        assertFalse(parkingSlot.isOccupied());

    }




}
