import pytest
from app.database import get_db
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

@pytest.fixture(scope='session')
def db():
    # Setup the database connection for testing
    engine = create_engine("mysql+mysqlconnector://user:password@localhost/test_db")
    TestingSessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

    # Create the database tables
    from app.models.transaction import Base
    Base.metadata.create_all(bind=engine)

    # Create a new session
    session = TestingSessionLocal()
    yield session

    # Teardown the database session
    session.close()
    Base.metadata.drop_all(bind=engine)

@pytest.fixture
def client():
    from fastapi.testclient import TestClient
    from app.main import app

    with TestClient(app) as client:
        yield client