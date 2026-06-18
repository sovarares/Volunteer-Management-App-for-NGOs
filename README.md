#  NGO Volunteer Management System

This project is a console-based software system developed in Java, dedicated to managing the activities and processes of a non-governmental organization (NGO)[cite: 4]. The application was developed as a team project and facilitates the interaction between three main types of users: Administrators, Volunteers, and Donors[cite: 4].

## 👥 Development Team
This project was built collaboratively by a team of three:
* **Șova Ioan-Rareș**[cite: 4]
* **Cosmulete Ion-Cosmin** 
* **Cismaru Adrian** 

## 🛠 Technologies & Concepts
* **Language:** Java[cite: 4].
* **Design:** A package-based architecture (`models` and `tests`) that implements core Object-Oriented Programming (OOP) relations such as Inheritance, Composition, and Association[cite: 4]. The abstract `Utilizator` class acts as the base for the `Administrator`, `Voluntar`, and `Donator` classes[cite: 4, 15].
* **PDF Generation:** Integration of the external **iTextPDF** library to export administrative reports and volunteer certificates[cite: 4, 13, 16].
* **Testing:** The **JUnit** framework is used for automated testing of critical functionalities[cite: 4].

## 🚀 Main Features

The application provides an interactive menu governed by role-based access control:

### 👨‍💼 Administrator
* Manages registered users via a dedicated panel, allowing the viewing and deletion of accounts[cite: 8].
* Creates, modifies, and deletes NGO activities[cite: 8]. Activities are categorized using an enumeration (e.g., `TEREN`, `LOGISTICA`, `ADMINISTRATIV`, `FUNDRAISING`, `EDUCATIONAL`)[cite: 6].
* Manages NGO reports, allowing the creation, detailed viewing, deletion, and PDF exporting of data[cite: 8, 13].
* Views a comprehensive history of all donations made to the NGO[cite: 8].

### 🙋 Volunteer
* Views the list of available NGO activities and applies for them. The system automatically verifies available spots and prevents duplicate enrollments for the same activity[cite: 16].
* Maintains a real-time `IstoricVoluntariat` (Volunteer History) that calculates the total accumulated hours[cite: 11, 16].
* Can generate and download an official Volunteer Certificate in PDF format, saved dynamically as `istoric_voluntar_[id]_[nume].pdf`[cite: 16].
* Can update profile details, including the associated phone number[cite: 16].

### 💖 Donor
* Can be categorized as `PERSOANA_FIZICA`, `PERSOANA_JURIDICA`, or `ANONIM`[cite: 14].
* Makes donations by specifying the amount and their preferred payment method (e.g., Bank Transfer)[cite: 10].
* The system processes the payment and generates an electronic receipt for the transaction[cite: 9, 10].

## 📂 Project Structure
The source code is organized into two primary packages[cite: 4]:
* `models/`: Contains the business logic, actor definitions (`Utilizator`, `Administrator`, `Voluntar`, `Donator`), entities (`Activitate`, `Donatie`, `IstoricVoluntariat`, `Raport`), enums, and the `Main.java` entry point[cite: 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16].
* `tests/`: Contains the unit testing classes (e.g., `AplicareActivitateTest.java`).

## 💻 How to Run the Project

1. Clone this repository:
```bash
   git clone [https://github.com/your-username/your-repo-name.git](https://github.com/your-username/your-repo-name.git)
