<div align="center">

# Car Rental

</div>
Welcome to my car rental application built using Java with the Spring Boot framework.

## Overview
This app is designed for both customers and administrators of a car rental service.
As a guest user (not logged in), you can access the homepage, register, 
and log in, as well as read customer feedbacks.
You can register using JWT authentication. Once logged in as a user, 
you can view all available cars, update your profile,
add details needed for renting, and place rental orders.
To make an order, you provide the car ID, location ID for pick-up, and rental period.
You can also leave feedback and view ones you've previously submitted.
Administrators have access to all features and endpoints after logging in.

## Table of Contents
- [Technologies](#technologies)
- [Project setup](#project-setup)
- [Tools needed](#tools-needed)
- [Installation](#installation)
- [Unit testing](#unit-testing)
- [Feedback](#feedback)

## *TECHNOLOGIES*

### Frameworks and Libraries:
- Spring Boot Starter Data JPA: Helps interact with the database.
- Spring Boot Starter Web: Manages RESTful web applications. 
- Spring Boot Starter Validation: Validates data input.
- Spring Boot Starter Security: Manages user authentication.
- Spring Boot Starter Test: Provides testing libraries and tools for Spring Boot applications.
- Lombok: Simplifies Java code writing.

### Database:
- MySQL Connector/J: Connects Java applications to MySQL databases.

### Security Libraries:
- Jwt Token: Handles authentication using JWT.

### Testing Frameworks:
- **JUnit:** A widely-used testing framework for Java.
- **Mockito:** A popular mocking framework for unit tests in Java.

## *PROJECT SETUP*

- Java Version: 17
- Dependency Management: Maven
- Build Tool: Spring Boot Maven Plugin

## *TOOLS NEEDED*
### To run this Spring Boot project, you'll need:

- **Java Development Kit (JDK) 17:** Required for Spring Boot 3.2.5. Download from [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or [OpenJDK](https://openjdk.java.net/).

- **Integrated Development Environment (IDE):** Recommended IDEs include [IntelliJ IDEA](https://www.jetbrains.com/idea/download/), [Eclipse](https://www.eclipse.org/downloads/), or [NetBeans](https://netbeans.apache.org/download/index.html).

- **Maven:** Manages dependencies for Java projects. Download from [Maven](https://maven.apache.org/download.cgi).

- **Docker:** Optional for managing a MySQL database container. Download from [Docker](https://www.docker.com/products/docker-desktop).

- **Postman:** For testing APIs. Download from [Postman](https://www.postman.com/downloads/).

- **Optional MySQL Workbench:** For easy database management. Download from [MySQL Workbench](https://dev.mysql.com/downloads/workbench/).

## *INSTALLATION*
### Opening the Project from Git Repository:

1. Launch IntelliJ IDEA.
2. Choose to open an existing project from Git:  
Check out from Version Control or File -> New -> Project from Version Control.
3. Enter the repository URL (https://github.com/rvdxk/car-rental), clone, and select a local directory.  

### Setting up the Project in IntelliJ IDEA:

1. Maven will detect the project's pom.xml file and update dependencies.
2. Use IntelliJ's terminal to run "mvn install".

### Docker integration:
1. Use IntelliJ's terminal to run "docker pull mysql" 
2. Next, run "docker-compose up --build".
3. Run the application by setting Spring Boot's main file "CarRentalSpringProjectApplication" and selecting Run &lt;CarRentalSpringProjectApplication&gt;.

### Testing with Postman:

To get authorization and a token.

1. Send a POST request with JSON:

#### For a user: http://localhost:3306/authorize/register/user

```json
{
"firstName": "userFirstName",
"lastName": "userLastName",
"email": "userEmail@carrental.com",
"password": "userPassword"
}
```

#### For an admin: http://localhost:3306/authorize/register/admin

```json
{
"firstName": "adminFirstName",
"lastName": "adminLastName",
"email": "adminEmail@carrental.com",
"password": "adminPassword"
}
````
2. Get the generated token for further endpoint testing. 
3. In Postman, use the token in the Authorization tab under "Bearer Token".
## *UNIT TESTING*
Run unit tests with Maven: mvn test.
## *FEEDBACK*
For any issues or suggestions, please open an issue on the GitHub repository. Your feedback is important!

