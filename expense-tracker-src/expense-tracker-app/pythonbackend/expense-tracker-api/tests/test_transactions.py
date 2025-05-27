import pytest
from fastapi.testclient import TestClient
from app.main import app
from app.database import get_db
from app.models.transaction import Transaction
from sqlalchemy.orm import Session

@pytest.fixture
def client():
    return TestClient(app)

@pytest.fixture
def db_session():
    db = next(get_db())
    yield db
    db.close()

def test_add_transaction(client, db_session):
    response = client.post("/transactions/", json={
        "date": "2023-10-01",
        "category": "Food",
        "amount": 50.0,
        "user_id": 1
    })
    assert response.status_code == 201
    data = response.json()
    assert data["date"] == "2023-10-01"
    assert data["category"] == "Food"
    assert data["amount"] == 50.0
    assert data["user_id"] == 1

    # Verify that the transaction was added to the database
    transaction = db_session.query(Transaction).filter_by(id=data["id"]).first()
    assert transaction is not None
    assert transaction.date == "2023-10-01"
    assert transaction.category == "Food"
    assert transaction.amount == 50.0
    assert transaction.user_id == 1

def test_add_transaction_invalid_data(client):
    response = client.post("/transactions/", json={
        "date": "invalid-date",
        "category": "Food",
        "amount": -50.0,
        "user_id": "not-an-id"
    })
    assert response.status_code == 422  # Unprocessable Entity for invalid data