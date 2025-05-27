from pydantic import BaseModel
from datetime import date

class TransactionCreate(BaseModel):
    date: date
    category: str
    amount: float
    user_id: int

class Transaction(TransactionCreate):
    id: int

    class Config:
        orm_mode = True