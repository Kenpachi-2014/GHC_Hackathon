from sqlalchemy import Column, Integer, String, Float, Date
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()

class Transaction(Base):
    __tablename__ = 'transactions'

    id = Column(Integer, primary_key=True, index=True)
    date = Column(Date, nullable=False)
    category = Column(String(50), nullable=False)
    amount = Column(Float, nullable=False)
    user_id = Column(Integer, nullable=False)