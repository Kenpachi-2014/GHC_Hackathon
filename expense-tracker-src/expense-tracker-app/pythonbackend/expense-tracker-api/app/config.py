import os

class Config:
    DATABASE_URL = os.getenv("DATABASE_URL", "mysql+pymysql://user:password@localhost/db_name")
    SECRET_KEY = os.getenv("SECRET_KEY", "your_secret_key")
    DEBUG = os.getenv("DEBUG", "True") == "True"