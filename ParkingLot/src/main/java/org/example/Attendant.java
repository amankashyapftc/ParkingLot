package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Attendant {
    private List<ParkingLot> parkingLots;
    private Strategy strategy;

    public Attendant() {
        this.parkingLots = new ArrayList<>();
    }

    public void assignParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public void setParkingStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    public int size() {
        return parkingLots.size();
    }

    public boolean contains(ParkingLot parkingLot) {
        return parkingLots.contains(parkingLot);
    }
    public String parkCar(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                String token = parkingLot.parkCar(car);
                if (parkingLot.isFull()) {
                    strategy.notifyLotFull();
                }
                return token;
            } catch (IllegalArgumentException ignored) {
                // Continue to the next parking lot if the current one is full
            }
        }
        throw new IllegalArgumentException("Unable to park the car. All parking lots are full.");
    }

    public void unPark(String token) {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                parkingLot.unPark(token);
                return; // Break once successfully unparked the car
            } catch (IllegalArgumentException ignored) {
                // Continue to the next parking lot if the token is invalid or the car is not found
            }
        }
        System.out.println("Unable to unpark the car. Invalid token or car not found.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendant that = (Attendant) o;
        return Objects.equals(parkingLots, that.parkingLots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingLots);
    }
}
