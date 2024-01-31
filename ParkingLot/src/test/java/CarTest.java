import org.example.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarTest {
    @Test
    public void testRegistrationNumberHasACertainPattern() {
        assertDoesNotThrow(() -> new Car("AB12CD5643", "Silver"));
    }

    @Test
    public void testRegistrationNumberHasAtLeast10Char() {
        assertThrows(IllegalArgumentException.class, () -> new Car("AB12CD345", "Yellow"));
        assertDoesNotThrow(() -> new Car("AB12CD3456", "Silver"));
    }

    @Test
    public void testIfRegistrationNumberDoesNotFollowThePatternItShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new Car("A1B2C3D4E5", "Black"));
    }

    @Test
    public void testRegistrationNumberCanNotBeEmpty(){
        assertThrows(IllegalArgumentException.class, () -> new Car("", "Yellow"));
    }

    @Test
    public void test2CarsDoNotCreateWithSameRegistrationNumber() {
        Car car1 = new Car("KA12CD3456", "Blue");
        assertThrows(IllegalArgumentException.class, () -> new Car("KA12CD3456", "Red"));
    }

    @Test
    public void testColorCanNotBeEmpty(){
        assertThrows(IllegalArgumentException.class, () -> new Car("AB12CD3456", ""));
    }
}
