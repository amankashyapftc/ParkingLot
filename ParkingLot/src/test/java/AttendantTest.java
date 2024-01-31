import org.example.Car;
import org.example.ParkingLot;
import org.example.Attendant;
import org.example.Strategy;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AttendantTest {

    @Test
    public void testAbleToAddParkingLotIntoAttendant() {
        // Arrange
        Attendant attendant = new Attendant();
        ParkingLot parkingLot1 = new ParkingLot(5);
        ParkingLot parkingLot2 = new ParkingLot(3);

        // Act
        attendant.assignParkingLot(parkingLot1);
        attendant.assignParkingLot(parkingLot2);

        // Assert
        assertEquals(2, attendant.size());
        assertTrue(attendant.contains(parkingLot1));
        assertTrue(attendant.contains(parkingLot2));
    }

    @Test
    public void testAbleToAddParkACarInNextParkingLotIfParkingLot1IsFull() {
        // Arrange
        ParkingLot parkingLot1 = new ParkingLot(1); // Capacity 1
        ParkingLot parkingLot2 = new ParkingLot(1); // Capacity 1
        Attendant attendant = new Attendant();

        Strategy parkingStrategy = new Strategy();
        attendant.setParkingStrategy(parkingStrategy);
        attendant.assignParkingLot(parkingLot1);
        attendant.assignParkingLot(parkingLot2);

        // Act
        Car car1 = new Car("UP09HG1234", "Blue");
        Car car2 = new Car("HN23HG4567", "Red");

        attendant.parkCar(car1);
        attendant.parkCar(car2);

        // Assert
        assertTrue(parkingLot1.isSlotOccupied(1));
        assertTrue(parkingLot2.isSlotOccupied(1));
        assertThrows(IndexOutOfBoundsException.class, () -> parkingLot1.isSlotOccupied(2));
        assertThrows(IndexOutOfBoundsException.class, () -> parkingLot2.isSlotOccupied(2));
    }

    @Test
    public void testMultipleAttendantCanBeAssignToSingleParkingLotShouldThrowError() {
        // Arrange
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        Attendant attendant = new Attendant();
        attendant.assignParkingLot(parkingLot1);
        attendant.assignParkingLot(parkingLot2);

        Car car = new Car("UP78GH5578", "Red");

        when(parkingLot1.parkCar(car)).thenThrow(new IllegalArgumentException("Parking lot is full."));
        when(parkingLot2.parkCar(car)).thenReturn("1");

        attendant.parkCar(car);

        verify(parkingLot1, times(1)).parkCar(car);
        verify(parkingLot2, times(1)).parkCar(car);
    }

    @Test
    public void testOneAttendantCanParkInMultipleParkingLot(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        Attendant attendant = new Attendant();
        Strategy parkingStrategy = new Strategy();
        attendant.setParkingStrategy(parkingStrategy);
        attendant.assignParkingLot(parkingLot1);
        attendant.assignParkingLot(parkingLot2);

        Car car1 = new Car("UP78GH5678", "Red");
        Car car2 = new Car("UP78HH5678", "Red");

        attendant.parkCar(car1);
        attendant.parkCar(car2);

        assertTrue(parkingLot1.isFull());
        assertTrue(parkingLot2.isFull());
    }

    @Test
    public void testAbleToNotifyIfLotIsFull() {

        ParkingLot parkingLot = new ParkingLot(1);
        Attendant attendant = new Attendant();
        attendant.assignParkingLot(parkingLot);

        Strategy parkingStrategy = new Strategy();
        attendant.setParkingStrategy(parkingStrategy);

        attendant.parkCar(new Car("AB34GC1323", "Red"));


        assertTrue(parkingStrategy.isLotFull());
    }

}
