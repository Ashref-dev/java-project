package com.javaprojectplane.cli;

public class Passenger {
    private int id;
    private String name;
    private String passportNumber;
    private String seatNumber;

    public Passenger(int id, String name, String passportNumber, String seatNumber) {
        this.id = id;
        this.name = name;
        this.passportNumber = passportNumber;
        this.seatNumber = seatNumber;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getPassportNumber() { return passportNumber; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    @Override
    public String toString() {
        return String.format("Passenger [ID: %d, Name: %s, Passport: %s, Seat: %s]",
            id, name, passportNumber, seatNumber);
    }
} 