import org.example.Car;
import org.example.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    public void testInitializeParkingLotWith5SlotsArrayList() {
        ParkingLot parkingLot = new ParkingLot(5);
        assertEquals(parkingLot, new ParkingLot(5));
        for (int i = 1; i <= 5; i++) {
            assertFalse(parkingLot.isSlotOccupied(i));
        }
    }
    @Test
    public void testInitializeParkingLotWith0SlotsGivesError() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot(0));
    }

    @Test
    public void testAbleToParkCarInParkingLot() {
        // Arrange
        ParkingLot parkingLot = new ParkingLot(5);
        Car car = new Car("AB23SD2345", "Blue");

        // Act
        parkingLot.parkCar(car);

        // Assert
        assertTrue(parkingLot.isSlotOccupied(1));
    }

    @Test
    public void testIfOneSlotIsFullAssignNextEmptySlotUntilTheWholeParkingLotIsFull() {
        // Arrange
        ParkingLot parkingLot = new ParkingLot(3); // Assuming a parking lot with 3 slots

        // Park cars until the parking lot is full
        Car car1 = new Car("AB12GH7893", "Blue");
        Car car2 = new Car("XY45HJ7896", "Red");
        Car car3 = new Car("DE78GH8909", "Green");

        // Act
        parkingLot.parkCar(car1);
        parkingLot.parkCar(car2);
        parkingLot.parkCar(car3);

        // Assert
        assertTrue(parkingLot.isSlotOccupied(1));
        assertTrue(parkingLot.isSlotOccupied(2));
        assertTrue(parkingLot.isSlotOccupied(3));
        assertTrue(parkingLot.isFull());

        // Try to park another car, it should throw an exception
        Car extraCar = new Car("GH45JK8906", "Yellow");
        assertThrows(IllegalArgumentException.class, () -> parkingLot.parkCar(extraCar));
    }
    @Test
    public void testAbleToCountCarsWithColor() {
        ParkingLot parkingLot = new ParkingLot(5);

        Car blueCar1 = new Car("UP65GH2345", "Blue");
        Car blueCar2 = new Car("UP65GH2343", "Blue");
        Car redCar = new Car("UP65GH2342", "Red");
        Car greenCar = new Car("UP65GH2341", "Green");

        parkingLot.parkCar(blueCar1);
        parkingLot.parkCar(blueCar2);
        parkingLot.parkCar(redCar);
        parkingLot.parkCar(greenCar);

        int blueCarCount = parkingLot.countCarsWithColor("Blue");

        assertEquals(2, blueCarCount);
    }

    @Test
    public void testNotAbleToParkCarIfAllSlotsAreFull() {
        ParkingLot parkingLot = new ParkingLot(2); // Set a parking lot with 2 slots

        Car car1 = new Car("UP65GH2348", "Blue");
        Car car2 = new Car("UP65GH2346", "Red");
        Car car3 = new Car("UP65GH2347", "Green");

        parkingLot.parkCar(car1);
        parkingLot.parkCar(car2);

        assertThrows(IllegalArgumentException.class, () -> parkingLot.parkCar(car3));
    }


    @Test
    public void testAbleToUnParkFromParkingLotAfterVerifyingTheToken() {
        // Arrange
        ParkingLot parkingLot = new ParkingLot(5);
        Car car = new Car("AB34GH2123", "Blue");

        // Park the car in the parking lot
        String token = parkingLot.parkCar(car);

        // Act
        parkingLot.unPark(token);

        // Assert
        assertFalse(parkingLot.isSlotOccupied(1));
    }

}
