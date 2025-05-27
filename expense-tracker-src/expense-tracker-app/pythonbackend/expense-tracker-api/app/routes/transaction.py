from fastapi import APIRouter, HTTPException
from sqlalchemy.orm import Session
from app.models.transaction import Transaction
from app.schemas.transaction import TransactionCreate
from app.database import get_db

router = APIRouter()

@router.post("/transactions/", response_model=TransactionCreate)
def create_transaction(transaction: TransactionCreate, db: Session = next(get_db())):
    db_transaction = Transaction(**transaction.dict())
    db.add(db_transaction)
    db.commit()
    db.refresh(db_transaction)
    return db_transaction