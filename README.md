# Classroom RESTful Web Service

## Overview

### For Educational Institutions
This program is tailored to assist educational institutions in efficiently managing their student data. It enables schools and other learning centers to register and manipulate student details across different grade levels. With this system, institutions can effortlessly add new students, update existing student profiles, and organize them into appropriate grade levels, all through a user-friendly interface. This enhances the ability to handle educational data with greater accuracy and efficiency, making it easier for schools to maintain up-to-date records of their students' progress and enrollment.

### For Developers
This application is a RESTful web service built using Java and leverages the Spring Framework for robust back-end functionality. It features a suite of APIs for CRUD operations, which allows for seamless integration with existing systems or the development of new applications. The architecture utilizes Spring MVC to handle requests and responses, Spring Data JPA for object-relational mapping (ORM), and Hibernate for transaction management. This setup not only simplifies the development process but also enhances scalability and maintainability. The service provides a flexible, powerful solution for managing educational data, supporting extensive customization to meet specific institutional needs.

## Key Features
- **Learner Management:** Comprehensive CRUD operations allow for managing detailed learner profiles within the educational system.
- **Grade Level Management:** Extensive functionalities to handle operations related to different educational stages and settings.
- **RESTful API:** Designed for seamless integration with other systems, providing straightforward endpoints for easy data manipulation and retrieval.

## Architecture and Design
This project employs a layered architectural approach, enhancing separation of concerns, scalability, and maintainability:
- **Controller Layer:** Manages the flow of data between the server and clients, handling incoming requests and responding appropriately.
- **Service Layer:** Implements the business logic of the application, acting as a liaison between the controller and persistence layers.
- **Persistence Layer:** Directly manages the database operations abstracted through DAO patterns to simplify data access and manipulations.
- **Model Layer:** Represents the domain model of the application, defining the essential data structures.

### Design Patterns
- **DAO (Data Access Object):** Encapsulates all interactions with the data source to provide a flexible solution for managing data persistence.
- **DTO (Data Transfer Object):** Optimizes data transfer across the network by reducing the amount of data that needs to be sent.
- **MVC (Model-View-Controller):** Separates the application into three interconnected components, allowing for efficient code reuse and parallel development. Models represent the data structure, views display the data, and controllers handle the input, converting it to commands for the model or view.

## Technologies Used
- **Spring MVC:** Provides a model-view-controller architecture and ready components that can be used to develop flexible and loosely coupled web applications. This framework handles the infrastructure so you can focus on your application.
- **Spring REST:** Facilitates the creation of RESTful web services using the Spring Framework, allowing easy handling of requests with mappings and serialization.
- **Spring Data JPA & Hibernate:** Simplifies the implementation of data access layers by reducing the boilerplate code. Manages the data layer in a way that's resilient and performant, ensuring smooth interaction with the database.
- **MySQL:** Provides a reliable and scalable database management system for storing all educational data.
- **Maven:** Manages dependencies and builds the project, integrating seamlessly with Tomcat for deployment.
- **Tomcat:** A robust application server used to deploy and manage the web service effectively. Essential for running your Java applications.
- **IntelliJ IDEA:** The preferred IDE, offering powerful coding assistance and ergonomic design for Java development. It enhances productivity through its advanced coding assistance, intelligent refactoring, and deep integration with the Spring framework.
- **Thymeleaf** (In Development): Being integrated to adopt an architecture where the application can render its own web pages, enhancing the flexibility to produce server-side dynamic content.


## Setup and Deployment

### 1. Tomcat Deployment using Maven
To deploy the Classroom RESTful Web Service to a Tomcat server using Maven, follow these steps:

- **Ensure Tomcat is Installed:** Confirm that the Tomcat server is installed and operational on your system.

### 1.1 Setting Up Tomcat
- **Download and Extract Tomcat:**
  - Start by downloading Tomcat from the official website.
  - Extract the downloaded file to your preferred location.

- **Configure Tomcat Users:**
  - Navigate to the `conf` directory in your Tomcat installation (`~/tomcat/conf/`).
  - Open `tomcat-users.xml` and insert the following entries at the end of the file to enable user roles and access for deployment:
    ```xml
    <role rolename="admin-gui"/>
    <role rolename="manager-gui"/>
    <role rolename="manager-script"/>
    <user username="tomcat" password="tomcat" roles="admin-gui,manager-gui,manager-script"/>
    ```

### 1.2. Configuring Maven for Deployment
- **Create and Configure settings.xml in Maven:**
  - In your home directory under `.m2`, create a `settings.xml` file if it does not already exist.
  - Insert the following configuration to set up server authentication, allowing Maven to interact with Tomcat:
    ```xml
    <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
      <servers>
        <!-- Server Authentication Information -->
        <server>
          <id>TomcatServer</id>
          <username>tomcat</username>
          <password>tomcat</password>
        </server>
      </servers>
    </settings>
    ```

### 1.3. Deploying Using Maven
- **Deploy with Maven:**
  - With the above configurations in place, use Maven to deploy your application directly to Tomcat. Ensure you've properly set up your Maven `settings.xml` for Tomcat credentials correctly.
### Application Deployment Commands

```bash
# Deploy the application to the server
mvn tomcat7:deploy

# Redeploy the application after making changes
mvn tomcat7:redeploy

# Remove the application from the server
mvn tomcat7:undeploy
```

### 2. Database Configuration and Setup
To set up the database for the Classroom RESTful Web Service:

- **Create the MySQL Database:**
  - Manually create a MySQL database named `classroom`. This database needs to exist prior to deployment as the JPA does not create the database itself, only the tables:
    ```sql
    CREATE DATABASE classroom;
    ```

- **Configure JPA to Create Tables:**
  - The JPA is configured to create tables automatically. Ensure the `persistence.xml` file under `src/main/resources/META-INF/` includes the following property for initial setup:
    ```xml
    <property name="hibernate.hbm2ddl.auto" value="create" />
    ```
  - This setting will create tables based on the annotated entity classes in your application.

- **Switch to Update After Initial Setup:**
  - After the initial deployment and once the tables are created, it is recommended to change the `hibernate.hbm2ddl.auto` property to `update`. This ensures that any further changes in your entity models will only update the existing database schema without dropping and recreating tables:
    ```xml
    <property name="hibernate.hbm2ddl.auto" value="update" />
    ```

3. **Initialize Database with Test Data:**
   - From the `src/main/resources/database/`, execute the `fill_tables.sql` script to populate the database with initial data for testing:
     ```bash
     mysql -u username -p classroom < fill_tables.sql
     ```
   - Replace `username` with your MySQL username.
   - Example for Root User Without Password:
      ```bash
     mysql -u root classroom < fill_tables.sql
     ```

## Classroom API Documentation

## Using Postman to Test API Endpoints
Postman is a powerful tool for testing APIs. Below are step-by-step instructions to set up and use Postman with the Classroom RESTful Web Service.

### Setting Up Postman
1. **Install Postman:** Download and install Postman from [postman.com](https://www.postman.com/downloads/).

### Testing API Endpoints

#### Base URL
All requests start with the base URL: http://localhost:8080/classroom


#### Grade Level Operations
- **List All Grade Levels**
  - **GET** `/gradelevel/list`
  - Retrieves a list of all grade levels.

- **Get Grade Level by ID**
  - **GET** `/gradelevel/{id}`
  - Fetch a specific grade level by its ID.

- **Add Grade Level**
  - **POST** `/gradelevel/add`
  - Adds a new grade level with an initially empty list of learners.
  - **Body:**
    ```json
    {
      "name": "12th grade class",
      "location": "Lisboa",
      "learnerList": []
    }
    ```

- **Edit Grade Level by ID**
  - **PUT** `/gradelevel/edit/{id}`
  - Edits an existing grade level. Changing the name updates it for all associated learners.
  - **Body:**
    ```json
    {
      "name": "Updated Name",
      "location": "New Location"
    }
    ```

- **Delete Grade Level by ID**
  - **DELETE** `/gradelevel/delete/{id}`
  - Deletes a grade level only if there are no learners associated with it.

#### Learner Operations
- **List All Learners**
  - **GET** `/learner/list`
  - Retrieves a list of all learners.

- **Get Learner by ID**
  - **GET** `/learner/{id}`
  - Fetch a specific learner by their ID.

- **Add Learner**
  - **POST** `/learner/add`
  - Adds a new learner to an existing grade level.
  - **Body:**
    ```json
    {
      "name": "John Doe",
      "email": "john.doe@example.com",
      "phone": "987654321",
      "gradeLevelName": "Bachelor's in Mathematics"
    }
    ```

- **Edit Learner by ID**
  - **PUT** `/learner/edit/{id}`
  - Updates details about a learner.
  - **Body:**
    ```json
    {
      "name": "John Smith",
      "email": "john.smith@example.com",
      "phone": "987678546",
      "gradeLevelName": "Bachelor's in Mathematics"
    }
    ```

- **Delete Learner by ID**
  - **DELETE** `/learner/delete/{id}`
  - Removes a learner from the system.


## Contact
- **Name:** Luciano Filho
- **Email:** [luciano.filho.dev@gmail.com](mailto:luciano.filho.dev@gmail.com)

Feel free to reach out for any inquiries or collaborations related to this project.

