package org.example;


import org.example.coustomException.SlotIsOccupiedException;
import org.example.coustomException.CarNotFoundException;
import org.example.coustomException.SlotNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

public class ParkingLot {
    private final ArrayList<Slot> slots;

    public ParkingLot(int numberOfSlots) {
        if (numberOfSlots <= 0) {
            throw new IllegalArgumentException("Number of slots should be greater than zero.");
        }
        this.slots = new ArrayList<>(numberOfSlots);
        for (int i = 0; i < numberOfSlots; i++) {
            slots.add(new Slot());
        }
    }

    private Slot getAvailableSlot(Strategy... strategys) {
        Strategy strategy = strategys.length > 0 ? strategys[0]: Strategy.NEAREST;

        return strategy.getAvailableSlot(slots);
    }

    public String park(Car car, Strategy... strategy) throws SlotIsOccupiedException, SlotNotFoundException {
        Slot slot = this.getAvailableSlot(strategy);
        if (slot == null) {
            throw new SlotNotFoundException("Slots are full.");
        }

        String id = slot.park(car);
        if (this.isFull()) {
            NotificationBus.getInstance().publish(this, ParkingLotEvent.FULL);
        }
        return id;
    }

    private Slot getParkedCarSlot(String id) throws CarNotFoundException {
        Optional<Slot> slot = slots.stream().filter(p -> p.isValidId(id)).findFirst();
        if (slot.isEmpty()) {
            throw new CarNotFoundException("Car is not parked in slot.");
        }
        return slot.get();
    }

    public Car unPark(String id) throws CarNotFoundException, SlotNotFoundException {
        Slot slot = this.getParkedCarSlot(id);
        if (slot == null) {
            throw new SlotNotFoundException("Slot not found.");
        }

        Car car = slot.unPark(id);
        if (this.isEmpty()) {
            NotificationBus.getInstance().publish(this, ParkingLotEvent.EMPTY);
        }
        return car;
    }

    public boolean isFull() {
        long slotCount = slots.stream().filter(p -> !p.isFree()).count();
        return slotCount == (long) slots.size();
    }

    public boolean isEmpty() {
        long slotCount = slots.stream().filter(Slot::isFree).count();
        return slotCount == (long) slots.size();
    }

    public int emptySlotCount() {
        return (int) slots.stream().filter(Slot::isFree).count();
    }
}