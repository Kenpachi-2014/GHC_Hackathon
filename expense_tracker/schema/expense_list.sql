USE expense_tracker;
CREATE TABLE expense_list (
    id SERIAL PRIMARY KEY,
    expense_id BIGINT UNSIGNED NOT NULL,
    category VARCHAR(255) NOT NULL,
    notes TEXT,
    FOREIGN KEY (expense_id) REFERENCES expense(id) ON DELETE CASCADE
);