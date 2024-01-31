package org.example;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Car {
    private String registrationNumber;
    private String color;
    private static Set<String> uniqueRegistrationNumbers = new HashSet<>();

    public Car(String registrationNumber, String color) {
        if (!isValidRegistrationNumber(registrationNumber) || color.isEmpty()) {
            throw new IllegalArgumentException("Invalid registration number or color");
        }

        if (!uniqueRegistrationNumbers.add(registrationNumber)) {
            throw new IllegalArgumentException("Registration number must be unique");
        }

        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    private boolean isValidRegistrationNumber(String registrationNumber) {
        // Registration number should not be empty, have at least 10 characters, and the pattern: 2 letters, 2 digits, 2 letters, 4 digits
        String regex = "^[A-Za-z]{2}\\d{2}[A-Za-z]{2}\\d{4}$";
        return !registrationNumber.isEmpty() && registrationNumber.length() >= 10 && registrationNumber.matches(regex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(registrationNumber, car.registrationNumber) &&
                Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, color);
    }

    public boolean hasColor(String targetColor) {
        return color.equalsIgnoreCase(targetColor);
    }
}
