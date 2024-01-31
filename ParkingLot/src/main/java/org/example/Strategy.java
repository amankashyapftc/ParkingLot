package org.example;

import java.util.List;

public class Strategy {
    private boolean isLotFull;
    private boolean isLotAvailable;
    private boolean isNearestFirst;

    public Strategy() {
        this.isLotFull = false;
        this.isLotAvailable = true;
        this.isNearestFirst = true;
    }

    public void notifyLotFull() {
        isLotFull = true;
        isLotAvailable = false;
        System.out.println("Parking lot is full.");
    }

    public void notifyLotAvailable() {
        isLotFull = false;
        isLotAvailable = true;
        System.out.println("Parking lot is available.");
    }

    public boolean isLotFull() {
        return isLotFull;
    }

    public boolean isLotAvailable() {
        return isLotAvailable;
    }

    public void switchParkingStrategy() {
        isNearestFirst = !isNearestFirst;
        String strategy = isNearestFirst ? "Nearest Slot First" : "Farthest Slot First";
        System.out.println("Switched to " + strategy + " parking strategy.");
    }

    public boolean isNearestFirst() {
        return isNearestFirst;
    }

    public int getTargetSlot(List<Slot> slots) {
        // Implement logic to get the target slot based on the current strategy
        // You can modify this method according to your parking strategy logic
        // For example, return the nearest or farthest available slot index
        if (isNearestFirst) {
            // Implement nearest slot first logic
        } else {
            // Implement farthest slot first logic
        }
        return -1; // Replace with the actual slot index
    }
}
