package org.example.coustomException;

public class SlotNotFoundException extends Exception {
    public SlotNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}