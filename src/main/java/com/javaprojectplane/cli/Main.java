package com.javaprojectplane.cli;

import com.javaprojectplane.cli.validation.Validator;
import com.javaprojectplane.cli.exceptions.ValidationException;
import com.javaprojectplane.cli.ui.ConsoleMenu;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final int MAX_PLANES = 100;
    private static final int MAX_PILOTS = 100;
    private static Plane[] planes = new Plane[MAX_PLANES];
    private static Pilot[] pilots = new Pilot[MAX_PILOTS];
    private static int planeCount = 0;
    private static int pilotCount = 0;
    private static int nextPassengerId = 1;

    public static void main(String[] args) {
        ConsoleMenu.showWelcomeScreen();
        
        while (true) {
            List<String> mainOptions = Arrays.asList(
                "Manage Planes",
                "Manage Pilots",
                "Plane-Pilot Assignment",
                "Manage Passengers",
                "Exit"
            );

            int choice = ConsoleMenu.showMenu("Plane Management System", mainOptions);
            
            switch (choice) {
                case 0:
                    managePlanes();
                    break;
                case 1:
                    managePilots();
                    break;
                case 2:
                    managePlanePilotAssignment();
                    break;
                case 3:
                    managePassengers();
                    break;
                case 4:
                case -1:
                    ConsoleMenu.showMessage("Exiting...");
                    return;
            }
        }
    }

    private static void managePlanes() {
        while (true) {
            List<String> options = Arrays.asList(
                "Add Plane",
                "View All Planes",
                "Search for a Plane",
                "Delete a Plane",
                "Back to Main Menu"
            );

            int choice = ConsoleMenu.showMenu("Manage Planes", options);
            
            switch (choice) {
                case 0:
                    addPlane();
                    break;
                case 1:
                    viewAllPlanes();
                    break;
                case 2:
                    searchPlane();
                    break;
                case 3:
                    deletePlane();
                    break;
                case 4:
                case -1:
                    return;
            }
        }
    }

    private static void managePilots() {
        while (true) {
            List<String> options = Arrays.asList(
                "Add Pilot",
                "View All Pilots",
                "Search for a Pilot",
                "Delete a Pilot",
                "Back to Main Menu"
            );

            int choice = ConsoleMenu.showMenu("Manage Pilots", options);
            
            switch (choice) {
                case 0:
                    addPilot();
                    break;
                case 1:
                    viewAllPilots();
                    break;
                case 2:
                    searchPilot();
                    break;
                case 3:
                    deletePilot();
                    break;
                case 4:
                case -1:
                    return;
            }
        }
    }

    private static void managePlanePilotAssignment() {
        while (true) {
            List<String> options = Arrays.asList(
                "Assign Pilot to Plane",
                "View Plane's Pilot",
                "Back to Main Menu"
            );

            int choice = ConsoleMenu.showMenu("Plane-Pilot Assignment", options);
            
            switch (choice) {
                case 0:
                    assignPilotToPlane();
                    break;
                case 1:
                    viewPlanePilot();
                    break;
                case 2:
                case -1:
                    return;
            }
        }
    }

    private static void managePassengers() {
        while (true) {
            List<String> options = Arrays.asList(
                "Add Passenger to Plane",
                "View Plane's Passengers",
                "Remove Passenger from Plane",
                "Back to Main Menu"
            );

            int choice = ConsoleMenu.showMenu("Manage Passengers", options);
            
            switch (choice) {
                case 0:
                    addPassengerToPlane();
                    break;
                case 1:
                    viewPlanePassengers();
                    break;
                case 2:
                    removePassengerFromPlane();
                    break;
                case 3:
                case -1:
                    return;
            }
        }
    }

    private static void addPlane() {
        if (planeCount >= MAX_PLANES) {
            ConsoleMenu.showMessage("Maximum number of planes reached!");
            return;
        }

        try {
            String model = ConsoleMenu.readInput("Enter plane model: ");
            Validator.validateName(model);

            String regNumber = ConsoleMenu.readInput("Enter registration number: ");
            Validator.validateRegistrationNumber(regNumber);

            // Check for duplicate registration numbers
            for (int i = 0; i < planeCount; i++) {
                if (planes[i].getRegistrationNumber().equalsIgnoreCase(regNumber)) {
                    throw new ValidationException("A plane with this registration number already exists");
                }
            }

            planes[planeCount] = new Plane(planeCount + 1, model, regNumber);
            planeCount++;
            ConsoleMenu.showMessage("Plane added successfully!");
        } catch (ValidationException e) {
            ConsoleMenu.showMessage("Error: " + e.getMessage());
        }
    }

    private static void viewAllPlanes() {
        if (planeCount == 0) {
            ConsoleMenu.showMessage("No planes registered yet.");
            return;
        }

        StringBuilder message = new StringBuilder("Registered Planes:\n\n");
        for (int i = 0; i < planeCount; i++) {
            message.append(planes[i].toString()).append("\n");
        }
        ConsoleMenu.showMessage(message.toString());
    }

    private static void searchPlane() {
        String search = ConsoleMenu.readInput("Enter plane model or registration number: ");
        boolean found = false;
        StringBuilder results = new StringBuilder("Search Results:\n\n");

        for (int i = 0; i < planeCount; i++) {
            if (planes[i].getModel().toLowerCase().contains(search.toLowerCase()) || 
                planes[i].getRegistrationNumber().toLowerCase().contains(search.toLowerCase())) {
                results.append(planes[i].toString()).append("\n");
                found = true;
            }
        }

        if (!found) {
            ConsoleMenu.showMessage("No matching planes found.");
        } else {
            ConsoleMenu.showMessage(results.toString());
        }
    }

    private static void deletePlane() {
        try {
            int id = Integer.parseInt(ConsoleMenu.readInput("Enter plane ID to delete: "));
            int index = -1;

            for (int i = 0; i < planeCount; i++) {
                if (planes[i].getId() == id) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                // Shift remaining planes
                for (int i = index; i < planeCount - 1; i++) {
                    planes[i] = planes[i + 1];
                }
                planeCount--;
                ConsoleMenu.showMessage("Plane deleted successfully!");
            } else {
                ConsoleMenu.showMessage("Plane not found!");
            }
        } catch (NumberFormatException e) {
            ConsoleMenu.showMessage("Invalid ID format!");
        }
    }

    private static void addPilot() {
        if (pilotCount >= MAX_PILOTS) {
            ConsoleMenu.showMessage("Maximum number of pilots reached!");
            return;
        }

        try {
            String name = ConsoleMenu.readInput("Enter pilot name: ");
            Validator.validateName(name);

            String license = ConsoleMenu.readInput("Enter license number: ");
            Validator.validateLicenseNumber(license);

            // Check for duplicate license numbers
            for (int i = 0; i < pilotCount; i++) {
                if (pilots[i].getLicenseNumber().equalsIgnoreCase(license)) {
                    throw new ValidationException("A pilot with this license number already exists");
                }
            }

            pilots[pilotCount] = new Pilot(pilotCount + 1, name, license);
            pilotCount++;
            ConsoleMenu.showMessage("Pilot added successfully!");
        } catch (ValidationException e) {
            ConsoleMenu.showMessage("Error: " + e.getMessage());
        }
    }

    private static void viewAllPilots() {
        if (pilotCount == 0) {
            ConsoleMenu.showMessage("No pilots registered yet.");
            return;
        }

        StringBuilder message = new StringBuilder("Registered Pilots:\n\n");
        for (int i = 0; i < pilotCount; i++) {
            message.append(pilots[i].toString()).append("\n");
        }
        ConsoleMenu.showMessage(message.toString());
    }

    private static void searchPilot() {
        String search = ConsoleMenu.readInput("Enter pilot name or license number: ");
        boolean found = false;
        StringBuilder results = new StringBuilder("Search Results:\n\n");

        for (int i = 0; i < pilotCount; i++) {
            if (pilots[i].getName().toLowerCase().contains(search.toLowerCase()) || 
                pilots[i].getLicenseNumber().toLowerCase().contains(search.toLowerCase())) {
                results.append(pilots[i].toString()).append("\n");
                found = true;
            }
        }

        if (!found) {
            ConsoleMenu.showMessage("No matching pilots found.");
        } else {
            ConsoleMenu.showMessage(results.toString());
        }
    }

    private static void deletePilot() {
        try {
            int id = Integer.parseInt(ConsoleMenu.readInput("Enter pilot ID to delete: "));
            int index = -1;

            for (int i = 0; i < pilotCount; i++) {
                if (pilots[i].getId() == id) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                // Remove pilot assignment from planes
                for (int i = 0; i < planeCount; i++) {
                    if (planes[i].getPilotId() == id) {
                        planes[i].setPilotId(-1);
                    }
                }

                // Shift remaining pilots
                for (int i = index; i < pilotCount - 1; i++) {
                    pilots[i] = pilots[i + 1];
                }
                pilotCount--;
                ConsoleMenu.showMessage("Pilot deleted successfully!");
            } else {
                ConsoleMenu.showMessage("Pilot not found!");
            }
        } catch (NumberFormatException e) {
            ConsoleMenu.showMessage("Invalid ID format!");
        }
    }

    private static void assignPilotToPlane() {
        try {
            int planeId = Integer.parseInt(ConsoleMenu.readInput("Enter plane ID: "));
            int pilotId = Integer.parseInt(ConsoleMenu.readInput("Enter pilot ID: "));

            Plane plane = findPlaneById(planeId);
            Pilot pilot = findPilotById(pilotId);

            if (plane != null && pilot != null) {
                plane.setPilotId(pilotId);
                ConsoleMenu.showMessage("Pilot assigned to plane successfully!");
            } else {
                ConsoleMenu.showMessage("Plane or pilot not found!");
            }
        } catch (NumberFormatException e) {
            ConsoleMenu.showMessage("Invalid ID format!");
        }
    }

    private static void viewPlanePilot() {
        try {
            int planeId = Integer.parseInt(ConsoleMenu.readInput("Enter plane ID: "));
            Plane plane = findPlaneById(planeId);
            
            if (plane != null) {
                int pilotId = plane.getPilotId();
                if (pilotId != -1) {
                    Pilot pilot = findPilotById(pilotId);
                    if (pilot != null) {
                        ConsoleMenu.showMessage("Assigned pilot: " + pilot);
                        return;
                    }
                }
                ConsoleMenu.showMessage("No pilot assigned to this plane.");
            } else {
                ConsoleMenu.showMessage("Plane not found!");
            }
        } catch (NumberFormatException e) {
            ConsoleMenu.showMessage("Invalid ID format!");
        }
    }

    private static Pilot findPilotById(int id) {
        for (int i = 0; i < pilotCount; i++) {
            if (pilots[i].getId() == id) {
                return pilots[i];
            }
        }
        return null;
    }

    private static void addPassengerToPlane() {
        try {
            int planeId = Integer.parseInt(ConsoleMenu.readInput("Enter plane ID: "));
            Plane plane = findPlaneById(planeId);

            if (plane == null) {
                ConsoleMenu.showMessage("Plane not found!");
                return;
            }

            String name = ConsoleMenu.readInput("Enter passenger name: ");
            Validator.validateName(name);

            String passportNumber = ConsoleMenu.readInput("Enter passport number: ");
            Validator.validatePassportNumber(passportNumber);

            // Check for duplicate passport numbers on the same plane
            for (Passenger existingPassenger : plane.getPassengers()) {
                if (existingPassenger.getPassportNumber().equalsIgnoreCase(passportNumber)) {
                    throw new ValidationException("A passenger with this passport number is already on this plane");
                }
            }

            String seatNumber = ConsoleMenu.readInput("Enter seat number: ");
            Validator.validateSeatNumber(seatNumber, plane);

            Passenger passenger = new Passenger(nextPassengerId++, name, passportNumber, seatNumber);
            if (plane.addPassenger(passenger)) {
                ConsoleMenu.showMessage("Passenger added successfully!");
            } else {
                ConsoleMenu.showMessage("Plane is full! Cannot add more passengers.");
            }
        } catch (NumberFormatException e) {
            ConsoleMenu.showMessage("Invalid ID format!");
        } catch (ValidationException e) {
            ConsoleMenu.showMessage("Error: " + e.getMessage());
        }
    }

    private static void viewPlanePassengers() {
        try {
            int planeId = Integer.parseInt(ConsoleMenu.readInput("Enter plane ID: "));
            Plane plane = findPlaneById(planeId);

            if (plane == null) {
                ConsoleMenu.showMessage("Plane not found!");
                return;
            }

            Passenger[] passengers = plane.getPassengers();
            if (passengers.length == 0) {
                ConsoleMenu.showMessage("No passengers on this plane.");
                return;
            }

            StringBuilder message = new StringBuilder("Passengers on plane " + planeId + ":\n\n");
            for (Passenger passenger : passengers) {
                message.append(passenger.toString()).append("\n");
            }
            ConsoleMenu.showMessage(message.toString());
        } catch (NumberFormatException e) {
            ConsoleMenu.showMessage("Invalid ID format!");
        }
    }

    private static void removePassengerFromPlane() {
        try {
            int planeId = Integer.parseInt(ConsoleMenu.readInput("Enter plane ID: "));
            Plane plane = findPlaneById(planeId);

            if (plane == null) {
                ConsoleMenu.showMessage("Plane not found!");
                return;
            }

            int passengerId = Integer.parseInt(ConsoleMenu.readInput("Enter passenger ID to remove: "));

            if (plane.removePassenger(passengerId)) {
                ConsoleMenu.showMessage("Passenger removed successfully!");
            } else {
                ConsoleMenu.showMessage("Passenger not found on this plane!");
            }
        } catch (NumberFormatException e) {
            ConsoleMenu.showMessage("Invalid ID format!");
        }
    }

    private static Plane findPlaneById(int id) {
        for (int i = 0; i < planeCount; i++) {
            if (planes[i].getId() == id) {
                return planes[i];
            }
        }
        return null;
    }
} 