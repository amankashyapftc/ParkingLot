package org.example.coustomException;

public class CarNotFoundException extends Exception {
    public CarNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
