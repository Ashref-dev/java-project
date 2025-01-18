package com.javaprojectplane.cli.factory;

import com.javaprojectplane.cli.Plane;
import com.javaprojectplane.cli.Pilot;
import com.javaprojectplane.cli.Passenger;

public class TransportFactory {
    private static int nextPassengerId = 1;
    private static int nextPlaneId = 1;
    private static int nextPilotId = 1;

    public static Plane createPlane(String model, String registrationNumber) {
        return new Plane(nextPlaneId++, model, registrationNumber);
    }

    public static Pilot createPilot(String name, String licenseNumber) {
        return new Pilot(nextPilotId++, name, licenseNumber);
    }

    public static Passenger createPassenger(String name, String passportNumber, String seatNumber) {
        return new Passenger(nextPassengerId++, name, passportNumber, seatNumber);
    }

    // Reset methods for testing or system reset
    public static void resetIds() {
        nextPassengerId = 1;
        nextPlaneId = 1;
        nextPilotId = 1;
    }
} 