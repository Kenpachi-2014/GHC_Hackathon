from fastapi import APIRouter

router = APIRouter()

from .transaction import router as transaction_router

router.include_router(transaction_router, prefix="/transactions", tags=["transactions"])