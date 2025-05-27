from sqlalchemy.orm import Session
from app.models.transaction import Transaction
from app.schemas.transaction import TransactionCreate

def add_transaction(db: Session, transaction: TransactionCreate):
    db_transaction = Transaction(
        date=transaction.date,
        category=transaction.category,
        amount=transaction.amount,
        user_id=transaction.user_id
    )
    db.add(db_transaction)
    db.commit()
    db.refresh(db_transaction)
    return db_transaction

def get_transactions(db: Session, skip: int = 0, limit: int = 100):
    return db.query(Transaction).offset(skip).limit(limit).all()