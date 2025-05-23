INSERT INTO profile (name, description) VALUES ('Tech Fundamentals Profile', 'Profile with books about core skills to work in tech');
INSERT INTO profile (name, description) VALUES ('Music Lover Profile', 'Profile with books about music history');

INSERT INTO book (title, isbn, publication_year, author, publisher, status, profile_id)
VALUES ('Clean Code: A Handbook of Agile Software Craftsmanship', 9780132350884, 2008, 'Robert C. Martin', 'Prentice Hall', 'PLANNED', 1),
VALUES ('The Pragmatic Programmer: Your Journey to Mastery', '9780135957059', 2019, 'Andrew Hunt, David Thomas', 'Addison-Wesley', 'COMPLETED', 1),
VALUES ('Algorithms (4th Edition)', '9780321573513', 2011, 'Robert Sedgewick, Kevin Wayne', 'Addison-Wesley', 'COMPLETED', 1);
VALUES ('Sonic Life: A Memoir', '9780385548656', 2023, 'Thurston Moore', 'Doubleday', 'READING', 2)
VALUES ('This Is Your Brain on Music: The Science of a Human Obsession', '9780525949695', 2006, 'Daniel J. Levitin', 'Dutton Penguin', 'PAUSED', 2);
