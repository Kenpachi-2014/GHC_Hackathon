# Expense Tracker API

This is a Python API project for tracking expenses. It allows users to add transactions with details such as date, category, amount, and user ID. The API is built using FastAPI and connects to a MySQL database.

## Project Structure

```
expense-tracker-api
├── app
│   ├── __init__.py
│   ├── main.py
│   ├── config.py
│   ├── database.py
│   ├── models
│   │   ├── __init__.py
│   │   └── transaction.py
│   ├── routes
│   │   ├── __init__.py
│   │   └── transaction.py
│   ├── schemas
│   │   ├── __init__.py
│   │   └── transaction.py
│   └── services
│       ├── __init__.py
│       └── transaction_service.py
├── tests
│   ├── __init__.py
│   ├── conftest.py
│   └── test_transactions.py
├── requirements.txt
├── .env.example
└── README.md
```

## Setup Instructions

1. **Clone the repository:**
   ```
   git clone <repository-url>
   cd expense-tracker-api
   ```

2. **Create a virtual environment:**
   ```
   python -m venv venv
   source venv/bin/activate  # On Windows use `venv\Scripts\activate`
   ```

3. **Install dependencies:**
   ```
   pip install -r requirements.txt
   ```

4. **Set up the database:**
   - Create a MySQL database for the application.
   - Update the `.env` file with your database credentials.

5. **Run the application:**
   ```
   uvicorn app.main:app --reload
   ```

## API Usage

### Add a Transaction

- **Endpoint:** `POST /transactions`
- **Request Body:**
  ```json
  {
    "date": "2023-10-01",
    "category": "Food",
    "amount": 50.0,
    "user_id": 1
  }
  ```

### Example Response

- **Success:**
  ```json
  {
    "message": "Transaction added successfully",
    "transaction_id": 1
  }
  ```

## Running Tests

To run the tests, use the following command:

```
pytest
```

## License

This project is licensed under the MIT License.