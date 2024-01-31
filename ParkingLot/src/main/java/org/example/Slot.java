package org.example;

import java.util.Objects;
import java.util.UUID;

public class Slot {
    private int slotNumber;
    private Car parkedCar;
    private String token;

    public Slot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.parkedCar = null;
    }

    public Slot(int slotNumber, Car parkedCar) {
        this.slotNumber = slotNumber;
        this.parkedCar = parkedCar;
    }

    public String parkCar(Car car) {
        String generatedToken = UUID.randomUUID().toString();
        if (this.parkedCar != null) {
            throw new IllegalArgumentException("Parking slot is already filled.");
        }

        this.parkedCar = car;
        this.token = generatedToken;
        return token;
    }

    public void unPark(String token) {
        if (this.parkedCar == null) {
            throw new IllegalStateException("Cannot unPark from an empty slot.");
        }
        if (!Objects.equals(this.token, token)){
            throw new IllegalArgumentException("Invalid Token");
        }
        this.parkedCar = null;
        this.token = null;
    }


    public boolean isOccupied() {
        return parkedCar != null;
    }

    public boolean isCarPresent(Car car) {
        return parkedCar != null && parkedCar.equals(car);
    }

    public boolean isCarWithColor(String color) {
        return parkedCar != null && parkedCar.hasColor(color);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot that = (Slot) o;
        return slotNumber == that.slotNumber &&
                Objects.equals(parkedCar, that.parkedCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotNumber, parkedCar);
    }
}