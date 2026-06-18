# 🤝 NGO Volunteer Management System

This project is a console-based software system developed in Java, dedicated to managing the activities and processes of a non-governmental organization (NGO). The application was developed as a team project and facilitates the interaction between three main types of users: Administrators, Volunteers, and Donors.

## 👥 Development Team
This project was built collaboratively by a team of three:
* **Șova Ioan-Rareș**
* **Cosmulete Ion-Cosmin** 
* **Cismaru Adrian** 

## 🛠 Technologies & Concepts
* **Language:** Java.
* **Design:** A package-based architecture (`models` and `tests`) that implements core Object-Oriented Programming (OOP) relations such as Inheritance, Composition, and Association. The abstract `Utilizator` class acts as the base for the `Administrator`, `Voluntar`, and `Donator` classes.
* **PDF Generation:** Integration of the external **iTextPDF** library to export administrative reports and volunteer certificates.
* **Testing:** The **JUnit** framework is used for automated testing of critical functionalities.

## 🚀 Main Features

The application provides an interactive menu governed by role-based access control:

### 👨‍💼 Administrator
* Manages registered users via a dedicated panel, allowing the viewing and deletion of accounts.
* Creates, modifies, and deletes NGO activities. Activities are categorized using an enumeration (e.g., `TEREN`, `LOGISTICA`, `ADMINISTRATIV`, `FUNDRAISING`, `EDUCATIONAL`).
* Manages NGO reports, allowing the creation, detailed viewing, deletion, and PDF exporting of data.
* Views a comprehensive history of all donations made to the NGO.

### 🙋 Volunteer
* Views the list of available NGO activities and applies for them. The system automatically verifies available spots and prevents duplicate enrollments for the same activity.
* Maintains a real-time `IstoricVoluntariat` (Volunteer History) that calculates the total accumulated hours.
* Can generate and download an official Volunteer Certificate in PDF format, saved dynamically as `istoric_voluntar_[id]_[nume].pdf`.
* Can update profile details, including the associated phone number.

### 💖 Donor
* Can be categorized as `PERSOANA_FIZICA`, `PERSOANA_JURIDICA`, or `ANONIM`.
* Makes donations by specifying the amount and their preferred payment method (e.g., Bank Transfer).
* The system processes the payment and generates an electronic receipt for the transaction.

## 📂 Project Structure
The source code is organized into two primary packages:
* `models/`: Contains the business logic, actor definitions (`Utilizator`, `Administrator`, `Voluntar`, `Donator`), entities (`Activitate`, `Donatie`, `IstoricVoluntariat`, `Raport`), enums, and the `Main.java` entry point.
* `tests/`: Contains the unit testing classes (e.g., `AplicareActivitateTest.java`).

## 💻 How to Run the Project

1. Clone this repository:
```bash
   git clone [https://github.com/your-username/your-repo-name.git](https://github.com/your-username/your-repo-name.git)
```
2. Import the project into your preferred IDE (e.g., Eclipse, IntelliJ IDEA).
3. Ensure the following external libraries are added to your Build Path:
   * **iTextPDF** (required for `.pdf` generation)
   * **JUnit 5** (required for running the test suite)
4. Run the `Main.java` class from the `models` package. The system initializes with mock data (an admin, a donor, a volunteer, and two sample activities) to allow immediate testing of the features.

## 🧪 Testing Coverage

The project includes rigorous unit tests. For example, the `aplicaLaActivitate` method is tested against 5 distinct scenarios:

1. Normal successful enrollment.
2. Lower boundary enrollment (registering for the last available spot).
3. Exception forcing (attempting to register when 0 spots remain).
4. Abnormal situations (handling negative available spots).
5. Decision branching (blocking a user who is already registered).
