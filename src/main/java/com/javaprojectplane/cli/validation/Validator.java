package com.javaprojectplane.cli.validation;

import com.javaprojectplane.cli.exceptions.ValidationException;
import com.javaprojectplane.cli.Plane;

import java.util.regex.Pattern;

public class Validator {
    // Passport number format: any combination of letters and numbers, minimum 6 characters
    private static final Pattern PASSPORT_PATTERN = Pattern.compile("^[A-Z0-9]+$");
    
    // License number format: any combination of letters and numbers, minimum 6 characters
    private static final Pattern LICENSE_PATTERN = Pattern.compile("^[A-Z0-9]+$");
    
    // Registration number format: any combination of letters and numbers, minimum 4 characters
    private static final Pattern REGISTRATION_PATTERN = Pattern.compile("^[A-Z0-9]+$");
    
    // Seat number format: any combination of letters and numbers
    private static final Pattern SEAT_PATTERN = Pattern.compile("^[A-Z0-9]+$");

    public static void validatePassportNumber(String passportNumber) {
        if (passportNumber == null || !PASSPORT_PATTERN.matcher(passportNumber.toUpperCase()).matches()) {
            throw new ValidationException("Invalid passport number format. Must be at least 6 alphanumeric characters");
        }
    }

    public static void validateLicenseNumber(String licenseNumber) {
        if (licenseNumber == null || !LICENSE_PATTERN.matcher(licenseNumber.toUpperCase()).matches()) {
            throw new ValidationException("Invalid license number format. Must be at least 6 alphanumeric characters");
        }
    }

    public static void validateRegistrationNumber(String registrationNumber) {
        if (registrationNumber == null || !REGISTRATION_PATTERN.matcher(registrationNumber.toUpperCase()).matches()) {
            throw new ValidationException("Invalid registration number format. Must be at least 4 alphanumeric characters");
        }
    }

    public static void validateSeatNumber(String seatNumber, Plane plane) {
        if (seatNumber == null || !SEAT_PATTERN.matcher(seatNumber.toUpperCase()).matches()) {
            throw new ValidationException("Invalid seat number format. Must contain only letters and numbers");
        }

        // Check for duplicate seat assignment
        if (plane != null) {
            for (var passenger : plane.getPassengers()) {
                if (passenger.getSeatNumber().equalsIgnoreCase(seatNumber)) {
                    throw new ValidationException("Seat " + seatNumber + " is already occupied");
                }
            }
        }
    }

    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 2 || name.length() > 50) {
            throw new ValidationException("Name must be between 2 and 50 characters");
        }
        if (!name.matches("^[a-zA-Z0-9\\s'-]+$")) {
            throw new ValidationException("Name can only contain letters, numbers, spaces, hyphens, and apostrophes");
        }
    }
} 