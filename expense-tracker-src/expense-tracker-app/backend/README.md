# Expense Tracker Application - Backend

This is the backend part of the Expense Tracker application built using Spring Boot. The application provides a RESTful API for managing expenses, users, and generating reports.

## Features

- **Expense Management**: Add, update, and retrieve expenses.
- **User Management**: Manage users and share expenses among multiple users.
- **Analytics**: Run analytics on expense data to gain insights.
- **Reports**: Download reports of expenses in various formats.
- **Expense History**: Maintain a history of all expenses.

## Project Structure

- `src/main/java/com/example/expensetracker`: Contains the main application code.
  - `controller`: REST controllers for handling HTTP requests.
  - `model`: Entity classes representing the data model.
  - `repository`: Interfaces for database operations.
  - `service`: Service classes containing business logic.
  - `dto`: Data Transfer Objects for data exchange.
  
- `src/main/resources`: Contains configuration and initialization files.
  - `application.properties`: Configuration properties for the application.
  - `db/schema.sql`: SQL statements for initializing the database schema.

- `src/test/java/com/example/expensetracker`: Contains unit tests for the application.

## Getting Started

1. **Clone the repository**:
   ```
   git clone <repository-url>
   ```

2. **Navigate to the backend directory**:
   ```
   cd expense-tracker-app/backend
   ```

3. **Build the application**:
   ```
   mvn clean install
   ```

4. **Run the application**:
   ```
   mvn spring-boot:run
   ```

5. **Access the API**: The API will be available at `http://localhost:8080`.

## Dependencies

This project uses Maven for dependency management. The `pom.xml` file includes necessary dependencies for Spring Boot, JPA, and other libraries.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or features.

## License

This project is licensed under the MIT License. See the LICENSE file for details.