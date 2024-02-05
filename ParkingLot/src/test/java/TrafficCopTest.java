import org.example.*;
import org.example.coustomException.CarNotFoundException;
import org.example.coustomException.SlotIsOccupiedException;
import org.example.coustomException.SlotNotFoundException;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TrafficCopTest {
    @Test
    public void shouldGetNotificationWhenParkingLotIsFull() throws SlotNotFoundException, SlotIsOccupiedException {
        ParkingLot parkingLot = new ParkingLot(1);
        Observer cop = mock(TrafficCop.class);
        Car car = new Car("IO87YU7654", "Green");
        NotificationBus.getInstance().subscribe(cop, ParkingLotEvent.FULL);

        parkingLot.park(car);

        verify(cop).notify(ParkingLotEvent.FULL, parkingLot);
    }

    @Test
    public void shouldGetNotificationWhenParkingLotIsEmpty() throws SlotNotFoundException, SlotIsOccupiedException, CarNotFoundException {
        ParkingLot parkingLot = new ParkingLot(2);
        Observer cop = mock(TrafficCop.class);
        Car car = new Car("YG56RT5432", "Red");
        NotificationBus.getInstance().subscribe(cop, ParkingLotEvent.EMPTY);

        String id = parkingLot.park(car);
        parkingLot.unPark(id);

        verify(cop).notify(ParkingLotEvent.EMPTY, parkingLot);
    }
}