from fastapi import FastAPI
from app.routes import transaction

app = FastAPI()

app.include_router(transaction.router)

@app.get("/")
def read_root():
    return {"message": "Welcome to the Expense Tracker API"}