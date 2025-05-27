# Expense Tracker Application

## Overview
The Expense Tracker Application is a reactive and responsive web application designed to help users manage their expenses efficiently. It features a Spring Boot backend with a relational database and a user-friendly web UI built with React.

## Features
- **Add and Update Expenses**: Users can easily add new expenses and update existing ones.
- **Expense History**: Maintain a comprehensive history of all expenses for better tracking.
- **Download Reports**: Generate and download reports for a specified time period.
- **Share Expenses**: Share expenses with multiple users for collaborative tracking.
- **Analytics**: Run analytics on the expense data to gain insights into spending habits.

## Project Structure
The project is divided into two main parts: the backend and the frontend.

### Backend
- **Spring Boot**: The backend is built using Spring Boot, providing a robust framework for building RESTful APIs.
- **Database**: A relational database is used to store user and expense data.
- **Directory Structure**:
  - `controller`: Contains REST controllers for handling HTTP requests.
  - `model`: Contains entity classes representing the data model.
  - `repository`: Contains interfaces for database operations.
  - `service`: Contains service classes for business logic.
  - `dto`: Contains Data Transfer Objects for data transfer between client and server.

### Frontend
- **React**: The frontend is built using React, providing a dynamic user interface.
- **Directory Structure**:
  - `components`: Reusable React components.
  - `pages`: Page components for different views.
  - `services`: Service files for making API calls to the backend.
  - `styles`: CSS or styled-components for styling the application.

## Setup Instructions
1. **Clone the Repository**: Clone the project repository to your local machine.
2. **Backend Setup**:
   - Navigate to the `backend` directory.
   - Run `mvn clean install` to build the backend.
   - Configure the database connection in `src/main/resources/application.properties`.
   - Run the Spring Boot application using `mvn spring-boot:run`.
3. **Frontend Setup**:
   - Navigate to the `frontend` directory.
   - Run `npm install` to install the necessary dependencies.
   - Start the React application using `npm start`.

## Conclusion
The Expense Tracker Application is a comprehensive tool for managing expenses, providing users with the ability to track, analyze, and share their financial data effectively.