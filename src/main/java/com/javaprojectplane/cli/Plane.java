package com.javaprojectplane.cli;

public class Plane {
    private static final int MAX_PASSENGERS = 200;
    private int id;
    private String model;
    private String registrationNumber;
    private int pilotId;
    private Passenger[] passengers;
    private int passengerCount;

    public Plane(int id, String model, String registrationNumber) {
        this.id = id;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.pilotId = -1; // No pilot assigned initially
        this.passengers = new Passenger[MAX_PASSENGERS];
        this.passengerCount = 0;
    }

    // Passenger management methods
    public boolean addPassenger(Passenger passenger) {
        if (passengerCount >= MAX_PASSENGERS) {
            return false;
        }
        passengers[passengerCount++] = passenger;
        return true;
    }

    public boolean removePassenger(int passengerId) {
        int index = -1;
        for (int i = 0; i < passengerCount; i++) {
            if (passengers[i].getId() == passengerId) {
                index = i;
                break;
            }
        }
        
        if (index != -1) {
            // Shift remaining passengers
            for (int i = index; i < passengerCount - 1; i++) {
                passengers[i] = passengers[i + 1];
            }
            passengerCount--;
            return true;
        }
        return false;
    }

    public Passenger[] getPassengers() {
        Passenger[] result = new Passenger[passengerCount];
        System.arraycopy(passengers, 0, result, 0, passengerCount);
        return result;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getModel() { return model; }
    public String getRegistrationNumber() { return registrationNumber; }
    public int getPilotId() { return pilotId; }
    public void setPilotId(int pilotId) { this.pilotId = pilotId; }

    @Override
    public String toString() {
        return String.format("Plane [ID: %d, Model: %s, Registration: %s, Pilot ID: %d, Passengers: %d]",
            id, model, registrationNumber, pilotId, passengerCount);
    }
} 