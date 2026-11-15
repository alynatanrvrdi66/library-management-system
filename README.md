# Library Management System

A console-based Library Management System developed using **Java** and **Object-Oriented Programming (OOP)** principles.

## Features

- **Add Materials:** Add books, magazines, and DVDs to the library.
- **List Materials:** Display all materials or filter them by type.
- **Borrow Materials:** Borrow available books and magazines.
- **Return Materials:** Return previously borrowed materials.
- **Reserve Books:** Reserve books for specific users.
- **Data Persistence:** Save and load library information using a text file.

## Technologies & Concepts

- Java
- Object-Oriented Programming (OOP)
- Inheritance
- Abstract Classes
- Interfaces
- Encapsulation
- File Handling
- ArrayList

## Project Structure

- `Main.java` – Handles user interaction through a console menu.
- `Library.java` – Manages library operations and file handling.
- `Material.java` – Abstract base class for library materials.
- `Book.java` – Represents books and implements borrowing and reservation operations.
- `Magazine.java` – Represents magazines and supports borrowing operations.
- `DVD.java` – Represents DVDs.
- `Borrowable.java` – Defines borrowing-related operations.
- `Reservable.java` – Defines reservation-related operations.

## Data Storage

Library data is stored in `library.txt`. The application loads existing records when it starts and saves changes to the file.

## How to Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Open `Main.java`.
4. Run the `main()` method.
5. Follow the instructions in the console menu.

## Purpose

This project was developed to practice Java programming, object-oriented design, interfaces, inheritance, and file handling through a practical library management application.
