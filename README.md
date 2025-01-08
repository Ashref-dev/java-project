# Project Idea: **Plane Management System (CLI-based)**
A basic command-line interface application with a menu system.


## Project Structure

```
src/
└── main/
    └── java/
        └── com/
            └── javaprojectplane/
                └── cli/
                    └── Main.java
```


#### Description:
The application will manage a simple system for tracking planes and their pilots. Users can perform basic operations like adding, viewing, and deleting planes and pilots, with data stored in arrays. The system will allow basic associations between planes and their assigned pilots.

---

### Features:

#### 1. **Manage Planes**:
   - Add a new plane.
   - View all planes.
   - Search for a plane by model or registration number.
   - Delete a plane.

#### 2. **Manage Pilots**:
   - Add a new pilot.
   - View all pilots.
   - Search for a pilot by name or ID.
   - Delete a pilot.

#### 3. **Plane-Pilot Assignment**:
   - Assign a pilot to a plane.
   - View the assigned pilot for a plane.

---

### Menu Design (CLI):
```plaintext
Plane Management System:
1. Manage Planes
   a. Add Plane
   b. View All Planes
   c. Search for a Plane
   d. Delete a Plane
2. Manage Pilots
   a. Add Pilot
   b. View All Pilots
   c. Search for a Pilot
   d. Delete a Pilot
3. Plane-Pilot Assignment
   a. Assign Pilot to Plane
   b. View Plane's Pilot
4. Exit
```

---

### Implementation Details:

#### Objects:
1. **`Plane` Class**:
   - Fields: `id`, `model`, `registrationNumber`, `pilotId`.
   - Methods: Getters/Setters, `toString`.

2. **`Pilot` Class**:
   - Fields: `id`, `name`, `licenseNumber`.
   - Methods: Getters/Setters, `toString`.

#### Data Storage:
- Use arrays to store `Plane` and `Pilot` objects.
- Operations such as add, search, and delete will directly manipulate these arrays.

---

### UML Diagrams:
1. **Use Case Diagram**:
   - Actors: `User`.
   - Use Cases: Add Plane, Add Pilot, Assign Pilot, Search Plane/Pilot, Delete Plane/Pilot.

2. **Class Diagram**:
   - Classes: `Plane`, `Pilot`, `PlaneManager`, `PilotManager`.

3. **Sequence Diagram**:
   - Example: Assigning a Pilot to a Plane.
     - User selects "Assign Pilot" → System asks for plane ID and pilot ID → Updates `Plane` object with the pilot's ID.

---

### Tools:
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code.
- **UML**: Use Lucidchart, StarUML, or any simple UML drawing tool.

---

### Deliverables:
1. **Executable Java Application**:
   - A `.jar` file that runs on any machine with a JDK.
2. **Documentation**:
   - UML diagrams (Use Case, Class, Sequence).

---

This simple project focuses on objects (planes and pilots) and basic interactions via arrays, making it ideal for learning and demonstrating core Java skills.