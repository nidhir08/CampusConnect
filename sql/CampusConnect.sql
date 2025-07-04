CREATE DATABASE IF NOT EXISTS CampusConnect;
USE CampusConnect;
CREATE TABLE users (
                       user_id INT PRIMARY KEY AUTO_INCREMENT,
                       full_name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       role VARCHAR(20) NOT NULL
);

CREATE TABLE societies (
                           so_id INT PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(50),
                           description VARCHAR(300),
                           logo_url VARCHAR(500),
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE events (
                        event_id INT PRIMARY KEY AUTO_INCREMENT,
                        society_id INT,
                        title VARCHAR(100) NOT NULL,
                        description TEXT,
                        event_date DATETIME NOT NULL,
                        location VARCHAR(100),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (society_id) REFERENCES societies(so_id)
);

CREATE TABLE society_members (
                                 member_id INT PRIMARY KEY AUTO_INCREMENT,
                                 so_id INT NOT NULL,
                                 user_id INT NOT NULL,
                                 position VARCHAR(50),
                                 joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 FOREIGN KEY (so_id) REFERENCES societies(so_id),
                                 FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO users (username, email, password, full_name, role)
VALUES
    ('admin', 'admin@example.com', 'admin123', 'Admin User', 'admin'),
    ('student', 'student@example.com', 'student123', 'Student User', 'student');

INSERT INTO societies (name, description)
VALUES
    ('Programming Club', 'A club for programming enthusiasts'),
    ('Drama Society', 'For students interested in theater and acting'),
    ('Debate Club', 'Improve your public speaking and debating skills');

INSERT INTO events (society_id, title, description, event_date, location)
VALUES (
           1,
           'Hackathon 2025',
           'A 24-hour coding challenge to solve real-world problems.',
           '2025-04-20 10:00:00',
           'Auditorium Hall'
       );
COMMIT;