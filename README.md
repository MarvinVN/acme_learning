# Foobar

Foobar is a Python library for dealing with word pluralization.

## Installation

Clone the repository.

```bash
git clone https://github.com/MarvinVN/acme_learning.git
```

## Running the Application

Have a MySQL server running. Run ```create_db.sql``` to create the data base.


Edit ```application.properties``` with your MySQL username and password.

```txt
spring.datasource.url=jdbc:mysql://localhost:3306/acme_learning
spring.datasource.username={your_username}
spring.datasource.password={your_password}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Navigate to the root folder and run ```mvn clean install``` to build the application and run tests.

Run ```mvn spring-boot:run``` to run the application.

## Business Requirements
All of the business requirements can be fulfilled by one or more of the supplied requests in the supplied Postman collection: ```ACME Learning Requests```

---------------
- Instructors and Students can sign up to the system

Can be done from the ```Instructor/Student: Sign Up``` request.
- Instructors and Students can log in to the system

First, have an Instructor/Student sign up. Then can be done from the ```Instructor/Student: Login``` request.
- Instructors can create new Courses

First have an Instructor sign up, then can be done from the ```Instructor: Create Course``` request with instructor id.
- Instructors can list only their Courses

Can be done from the ```Instructor: List Courses``` request with instructor id. Create a course first to see it listed.
- Instructors can start Courses, after this time no enrollments are allowed

Can be done from the ```Instructor: Start Course``` request with instructor id and course id.
- Instructors can cancel non started Courses

Can be done from the ```Instructor: Cancel Course``` request with instructor id and course id. Started courses won't be canceled. Logic can be found in ```Instructor.cancelCourse()```
- Students can list all the Courses

Can be done from the ```Student: Get All Courses``` request.
- Students can enroll to any non-started Course

Can be done from the ```Student: Enroll Course``` request with student id and course id. Students won't be able to join courses that have already started. Logic can be found in ```StudentService.enrollCourse()```
- Students can drop a Course

Can be done from the ```Student: Drop Course``` request with student id and course id.
- Students can list all the Courses they are enrolled to

Can be done from the ```Student: Get Enrolled Courses``` request with student id.
- Instructors can list enrolled students in a Course

Can be done from the ```Instructor: List Students in Course``` request with instructor id and course id.
