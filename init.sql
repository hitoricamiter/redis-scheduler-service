CREATE TABLE words (
                       id SERIAL PRIMARY KEY,
                       russian VARCHAR(255) UNIQUE,
                       english VARCHAR(255)
);

INSERT INTO words (russian, english) VALUES ('кот', 'cat'), ('собака', 'dog'), ('дом', 'house');

-- таблица для логов запросов
CREATE TABLE word_requests (
                               id SERIAL PRIMARY KEY,
                               russian VARCHAR(255),
                               requested_at TIMESTAMP DEFAULT now()
);