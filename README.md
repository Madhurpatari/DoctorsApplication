# Hospital Management System

The Hospital Management System is a software application developed using Spring Boot, Java, and Maven. 
It provides a comprehensive solution for hospitals or healthcare organizations to efficiently manage patient records, appointments, and basic administrative tasks.

## Features

- Patient Management: Allows CRUD operations for managing patient records.
- Appointment Management: Enables scheduling, updating, and canceling appointments.
- Authentication: Implements MD5 hashing for secure password storage and patient authentication.
- RESTful APIs: Provides RESTful APIs for seamless integration with other systems.
- Extensible: The project structure allows for easy extension and customization to meet specific requirements.

## Technologies Used

- Spring Boot
- Java
- Maven

## Getting Started

To get started with the Hospital Management System, follow these steps:

1. Clone the repository:
``` GIT
git clone https://github.com/Madhurpatari/DoctorsApplication.git
```

## Usage

Once the application is running, you can access the endpoints using tools like cURL or Postman. Here are some example API endpoints:

- Dcotor Management:
 
  - POST /Doctor/sign-up: Create a new doctor.
  - POST /Doctor/sign-in: Sign-in as a doctor.
  - GET /Appointments: Retrieve all appointments.
 
- Patient Management:
  - POST /Patient/sign-up: Create a new patient.
  - POST /Patient/sign-in: Sign-in as a patient.
  - GET /Doctor: Retrieve all doctors.
  - DELETE /cancelAppointment: Delete a appointments.

- Appointment Management:
  - POST /appointments: Create a new appointment.


## Contributing

Contributions are welcome! If you have any suggestions or improvements for the project, feel free to submit a pull request.

