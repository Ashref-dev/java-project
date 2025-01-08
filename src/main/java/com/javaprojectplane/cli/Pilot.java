package com.javaprojectplane.cli;

public class Pilot {
    private int id;
    private String name;
    private String licenseNumber;

    public Pilot(int id, String name, String licenseNumber) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getLicenseNumber() { return licenseNumber; }

    @Override
    public String toString() {
        return String.format("Pilot [ID: %d, Name: %s, License: %s]",
            id, name, licenseNumber);
    }
} 