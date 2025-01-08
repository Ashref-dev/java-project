package com.javaprojectplane.cli;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_PLANES = 100;
    private static final int MAX_PILOTS = 100;
    private static Plane[] planes = new Plane[MAX_PLANES];
    private static Pilot[] pilots = new Pilot[MAX_PILOTS];
    private static int planeCount = 0;
    private static int pilotCount = 0;
    private static int nextPassengerId = 1;

    public static void main(String[] args) {
        while (true) {
            displayMainMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    managePlanes();
                    break;
                case 2:
                    managePilots();
                    break;
                case 3:
                    managePlanePilotAssignment();
                    break;
                case 4:
                    managePassengers();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n=== Plane Management System ===");
        System.out.println("1. Manage Planes");
        System.out.println("2. Manage Pilots");
        System.out.println("3. Plane-Pilot Assignment");
        System.out.println("4. Manage Passengers");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void managePlanes() {
        while (true) {
            System.out.println("\n=== Manage Planes ===");
            System.out.println("a. Add Plane");
            System.out.println("b. View All Planes");
            System.out.println("c. Search for a Plane");
            System.out.println("d. Delete a Plane");
            System.out.println("e. Back to Main Menu");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().toLowerCase();
            switch (choice) {
                case "a":
                    addPlane();
                    break;
                case "b":
                    viewAllPlanes();
                    break;
                case "c":
                    searchPlane();
                    break;
                case "d":
                    deletePlane();
                    break;
                case "e":
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void managePilots() {
        while (true) {
            System.out.println("\n=== Manage Pilots ===");
            System.out.println("a. Add Pilot");
            System.out.println("b. View All Pilots");
            System.out.println("c. Search for a Pilot");
            System.out.println("d. Delete a Pilot");
            System.out.println("e. Back to Main Menu");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().toLowerCase();
            switch (choice) {
                case "a":
                    addPilot();
                    break;
                case "b":
                    viewAllPilots();
                    break;
                case "c":
                    searchPilot();
                    break;
                case "d":
                    deletePilot();
                    break;
                case "e":
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void managePlanePilotAssignment() {
        while (true) {
            System.out.println("\n=== Plane-Pilot Assignment ===");
            System.out.println("a. Assign Pilot to Plane");
            System.out.println("b. View Plane's Pilot");
            System.out.println("c. Back to Main Menu");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().toLowerCase();
            switch (choice) {
                case "a":
                    assignPilotToPlane();
                    break;
                case "b":
                    viewPlanePilot();
                    break;
                case "c":
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void managePassengers() {
        while (true) {
            System.out.println("\n=== Manage Passengers ===");
            System.out.println("a. Add Passenger to Plane");
            System.out.println("b. View Plane's Passengers");
            System.out.println("c. Remove Passenger from Plane");
            System.out.println("d. Back to Main Menu");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().toLowerCase();
            switch (choice) {
                case "a":
                    addPassengerToPlane();
                    break;
                case "b":
                    viewPlanePassengers();
                    break;
                case "c":
                    removePassengerFromPlane();
                    break;
                case "d":
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // Plane management methods
    private static void addPlane() {
        if (planeCount >= MAX_PLANES) {
            System.out.println("Maximum number of planes reached!");
            return;
        }

        System.out.print("Enter plane model: ");
        String model = scanner.nextLine();
        System.out.print("Enter registration number: ");
        String regNumber = scanner.nextLine();

        planes[planeCount] = new Plane(planeCount + 1, model, regNumber);
        planeCount++;
        System.out.println("Plane added successfully!");
    }

    private static void viewAllPlanes() {
        if (planeCount == 0) {
            System.out.println("No planes registered yet.");
            return;
        }

        System.out.println("\nRegistered Planes:");
        for (int i = 0; i < planeCount; i++) {
            System.out.println(planes[i]);
        }
    }

    private static void searchPlane() {
        System.out.print("Enter plane model or registration number: ");
        String search = scanner.nextLine().toLowerCase();
        boolean found = false;

        for (int i = 0; i < planeCount; i++) {
            if (planes[i].getModel().toLowerCase().contains(search) || 
                planes[i].getRegistrationNumber().toLowerCase().contains(search)) {
                System.out.println(planes[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching planes found.");
        }
    }

    private static void deletePlane() {
        System.out.print("Enter plane ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
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
                System.out.println("Plane deleted successfully!");
            } else {
                System.out.println("Plane not found!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    // Pilot management methods
    private static void addPilot() {
        if (pilotCount >= MAX_PILOTS) {
            System.out.println("Maximum number of pilots reached!");
            return;
        }

        System.out.print("Enter pilot name: ");
        String name = scanner.nextLine();
        System.out.print("Enter license number: ");
        String license = scanner.nextLine();

        pilots[pilotCount] = new Pilot(pilotCount + 1, name, license);
        pilotCount++;
        System.out.println("Pilot added successfully!");
    }

    private static void viewAllPilots() {
        if (pilotCount == 0) {
            System.out.println("No pilots registered yet.");
            return;
        }

        System.out.println("\nRegistered Pilots:");
        for (int i = 0; i < pilotCount; i++) {
            System.out.println(pilots[i]);
        }
    }

    private static void searchPilot() {
        System.out.print("Enter pilot name or license number: ");
        String search = scanner.nextLine().toLowerCase();
        boolean found = false;

        for (int i = 0; i < pilotCount; i++) {
            if (pilots[i].getName().toLowerCase().contains(search) || 
                pilots[i].getLicenseNumber().toLowerCase().contains(search)) {
                System.out.println(pilots[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching pilots found.");
        }
    }

    private static void deletePilot() {
        System.out.print("Enter pilot ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
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
                System.out.println("Pilot deleted successfully!");
            } else {
                System.out.println("Pilot not found!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    // Assignment methods
    private static void assignPilotToPlane() {
        System.out.print("Enter plane ID: ");
        try {
            int planeId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter pilot ID: ");
            int pilotId = Integer.parseInt(scanner.nextLine());

            Plane plane = null;
            Pilot pilot = null;

            // Find plane and pilot
            for (int i = 0; i < planeCount; i++) {
                if (planes[i].getId() == planeId) {
                    plane = planes[i];
                    break;
                }
            }

            for (int i = 0; i < pilotCount; i++) {
                if (pilots[i].getId() == pilotId) {
                    pilot = pilots[i];
                    break;
                }
            }

            if (plane != null && pilot != null) {
                plane.setPilotId(pilotId);
                System.out.println("Pilot assigned to plane successfully!");
            } else {
                System.out.println("Plane or pilot not found!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    private static void viewPlanePilot() {
        System.out.print("Enter plane ID: ");
        try {
            int planeId = Integer.parseInt(scanner.nextLine());
            Plane plane = null;
            
            for (int i = 0; i < planeCount; i++) {
                if (planes[i].getId() == planeId) {
                    plane = planes[i];
                    break;
                }
            }

            if (plane != null) {
                int pilotId = plane.getPilotId();
                if (pilotId != -1) {
                    for (int i = 0; i < pilotCount; i++) {
                        if (pilots[i].getId() == pilotId) {
                            System.out.println("Assigned pilot: " + pilots[i]);
                            return;
                        }
                    }
                }
                System.out.println("No pilot assigned to this plane.");
            } else {
                System.out.println("Plane not found!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    private static void addPassengerToPlane() {
        System.out.print("Enter plane ID: ");
        try {
            int planeId = Integer.parseInt(scanner.nextLine());
            Plane plane = findPlaneById(planeId);

            if (plane == null) {
                System.out.println("Plane not found!");
                return;
            }

            System.out.print("Enter passenger name: ");
            String name = scanner.nextLine();
            System.out.print("Enter passport number: ");
            String passportNumber = scanner.nextLine();
            System.out.print("Enter seat number: ");
            String seatNumber = scanner.nextLine();

            Passenger passenger = new Passenger(nextPassengerId++, name, passportNumber, seatNumber);
            if (plane.addPassenger(passenger)) {
                System.out.println("Passenger added successfully!");
            } else {
                System.out.println("Plane is full! Cannot add more passengers.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    private static void viewPlanePassengers() {
        System.out.print("Enter plane ID: ");
        try {
            int planeId = Integer.parseInt(scanner.nextLine());
            Plane plane = findPlaneById(planeId);

            if (plane == null) {
                System.out.println("Plane not found!");
                return;
            }

            Passenger[] passengers = plane.getPassengers();
            if (passengers.length == 0) {
                System.out.println("No passengers on this plane.");
                return;
            }

            System.out.println("\nPassengers on plane " + planeId + ":");
            for (Passenger passenger : passengers) {
                System.out.println(passenger);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    private static void removePassengerFromPlane() {
        System.out.print("Enter plane ID: ");
        try {
            int planeId = Integer.parseInt(scanner.nextLine());
            Plane plane = findPlaneById(planeId);

            if (plane == null) {
                System.out.println("Plane not found!");
                return;
            }

            System.out.print("Enter passenger ID to remove: ");
            int passengerId = Integer.parseInt(scanner.nextLine());

            if (plane.removePassenger(passengerId)) {
                System.out.println("Passenger removed successfully!");
            } else {
                System.out.println("Passenger not found on this plane!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
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

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
} 