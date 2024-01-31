package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParkingLot {
    private int capacity;
    private List<Slot> slots;

    public ParkingLot(int capacity) {

        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }

        this.capacity = capacity;
        this.slots = new ArrayList<>(capacity);
        initializeParkingSlots();
    }

    private void initializeParkingSlots() {
        for (int i = 1; i <= capacity; i++) {
            slots.add(new Slot(i));
        }
    }

    public String parkCar(Car car) {
        if (capacity == 0) {
            throw new IllegalArgumentException("All slots are full. Cannot park more cars.");
        }

        for (Slot slot : slots) {
            if (!slot.isOccupied()) {
                try {
                    String token = slot.parkCar(car);
                    this.capacity--;
                    return token;
                } catch (IllegalArgumentException ignored) {
                    // Continue to the next slot if the current one is filled
                }
            }
        }

        throw new IllegalArgumentException("Unable to park the car. All parking slots are already filled.");
    }

    public void unPark(String token) {
        for (Slot slot : slots) {
            try {
                slot.unPark(token);
                capacity++; // Increase capacity when the car is successfully unparked
                return;
            } catch (IllegalArgumentException ignored) {
                // Continue to the next slot if the token is invalid or the slot is empty
            }
        }

        throw new IllegalArgumentException("Unable to unpark the car. Invalid token or car not found.");
    }




    public int countCarsWithColor(String color) {
        int count = 0;
        for (Slot slot : slots) {
            if (slot.isOccupied() && slot.isCarWithColor(color)) {
                count++;
            }
        }
        return count;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLot that = (ParkingLot) o;
        return capacity == that.capacity && Objects.equals(slots, that.slots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, slots);
    }

    public boolean isSlotOccupied(int slotNumber) {
        return slots.get(slotNumber - 1).isOccupied();
    }

    public boolean isFull() {
        return capacity == 0;
    }

}
