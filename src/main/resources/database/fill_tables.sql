# To execute the SQL file in the terminal,
# navigate to the directory classroom-api/src/main/resources/database/
# using the cd command. Once there, execute the command
# mysql -u root "databasename" < fill_tables.sql
# replacing "databasename" with the name of your database.
# This command will run the SQL script fill_tables.sql
# and populate your database with the specified data.

# The database I'm using is called "classroom,"
# see line 29 of unit-persistence.xml.


# initial fake data for testing

-- Clean out existing data
TRUNCATE TABLE learner;
TRUNCATE TABLE gradelevel;

-- Insert data into gradelevel
INSERT INTO gradelevel (timestamp, updateTimestamp, version, name, location) VALUES ('2024-04-22 12:00:00', '2024-04-22 12:00:00', 1, 'Elementary School', 'Porto');
INSERT INTO gradelevel (timestamp, updateTimestamp, version, name, location) VALUES ('2024-04-22 12:00:00', '2024-04-22 12:00:00', 1, 'High School', 'Lisboa');
INSERT INTO gradelevel (timestamp, updateTimestamp, version, name, location) VALUES ('2024-04-22 12:00:00', '2024-04-22 12:00:00', 1, 'Undergraduate', 'Aveiro');

-- Insert data into learner, associating with gradelevel
-- Beginner Level Learners
INSERT INTO learner (timestamp, updateTimestamp, version, name, email, phone, gradeLevel_id) VALUES ('2024-04-22 12:00:00', '2024-04-22 12:00:00', 1, 'João Silva', 'joao.silva@instituicao.edu', '912345678', (SELECT id FROM gradelevel WHERE location = 'Porto'));
INSERT INTO learner (timestamp, updateTimestamp, version, name, email, phone, gradeLevel_id) VALUES ('2024-04-22 12:00:00', '2024-04-22 12:00:00', 1, 'Maria Oliveira', 'maria.oliveira@instituicao.edu', '923456789', (SELECT id FROM gradelevel WHERE location = 'Porto'));
INSERT INTO learner (timestamp, updateTimestamp, version, name, email, phone, gradeLevel_id) VALUES ('2024-04-22 12:00:00', '2024-04-22 12:00:00', 1, 'Carlos Pereira', 'carlos.pereira@instituicao.edu', '934567890', (SELECT id FROM gradelevel WHERE location = 'Porto'));

-- Intermediate Level Learners
INSERT INTO learner (timestamp, updateTimestamp, version, name, email, phone, gradeLevel_id) VALUES ('2024-04-22 12:00:00', '2024-04-22 12:00:00', 1, 'Ana Costa', 'ana.costa@instituicao.edu', '945678901', (SELECT id FROM gradelevel WHERE location = 'Lisboa'));
INSERT INTO learner (timestamp, updateTimestamp, version, name, email, phone, gradeLevel_id) VALUES ('2024-04-22 12:00:00', '2024-04-22 12:00:00', 1, 'Pedro Santos', 'pedro.santos@instituicao.edu', '956789012', (SELECT id FROM gradelevel WHERE location = 'Lisboa'));
INSERT INTO learner (timestamp, updateTimestamp, version, name, email, phone, gradeLevel_id) VALUES ('2024-04-22 12:00:00', '2024-04-22 12:00:00', 1, 'Lucas Nascimento', 'lucas.nascimento@instituicao.edu', '967890123', (SELECT id FROM gradelevel WHERE location = 'Lisboa'));

-- Advanced Level Learners
INSERT INTO learner (timestamp, updateTimestamp, version, name, email, phone, gradeLevel_id) VALUES ('2024-04-22 12:00:00', '2024-04-22 12:00:00', 1, 'Paula Rocha', 'paula.rocha@instituicao.edu', '978901234', (SELECT id FROM gradelevel WHERE location = 'Aveiro'));
INSERT INTO learner (timestamp, updateTimestamp, version, name, email, phone, gradeLevel_id) VALUES ('2024-04-22 12:00:00', '2024-04-22 12:00:00', 1, 'Ricardo Alves', 'ricardo.alves@instituicao.edu', '989012345', (SELECT id FROM gradelevel WHERE location = 'Aveiro'));
INSERT INTO learner (timestamp, updateTimestamp, version, name, email, phone, gradeLevel_id) VALUES ('2024-04-22 12:00:00', '2024-04-22 12:00:00', 1, 'Sofia Gomes', 'sofia.gomes@instituicao.edu', '991234567', (SELECT id FROM gradelevel WHERE location = 'Aveiro'));
