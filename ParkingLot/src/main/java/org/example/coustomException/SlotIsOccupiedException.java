package org.example.coustomException;

public class SlotIsOccupiedException extends Exception {
    public SlotIsOccupiedException(String errorMessage) {
        super(errorMessage);
    }
}