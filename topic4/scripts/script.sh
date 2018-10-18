mysql -s -r $MYSQL_USER -p$MYSQL_PASSWORD <<EOF

DROP DATABASE IF EXISTS highschool;
CREATE DATABASE highschool;

DROP USER IF EXISTS 'manager';
CREATE USER 'manager';
GRANT ALL PRIVILEGES ON highschool.* TO 'manager';

USE highschool;

DROP TABLE IF EXISTS Student;
CREATE TABLE Student (
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    birthdate DATE NOT NULL,
    registration_no INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (registration_no)
);

DROP TABLE IF EXISTS Teacher;
CREATE TABLE Teacher (
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    birthdate DATE NOT NULL,
    ID INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS Course;
CREATE TABLE Course (
    name VARCHAR(30) NOT NULL,
    teacher INT NOT NULL,
    weeklyhours TINYINT(2) UNSIGNED,
    PRIMARY KEY (name),
    CONSTRAINT FK_CourseTeacher FOREIGN KEY (teacher) REFERENCES Teacher(ID) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Schedule;
CREATE TABLE Schedule (
    course VARCHAR(30) NOT NULL,
    weekday ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday') NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    PRIMARY KEY (course, weekday),
    CONSTRAINT FK_ScheduleCourse FOREIGN KEY (course) REFERENCES Course(name) ON DELETE CASCADE ON UPDATE CASCADE,
    CHECK (start_time < end_time)
);

DROP TABLE IF EXISTS CourseStudent;
CREATE TABLE CourseStudent (
    year YEAR NOT NULL,
    student INT NOT NULL,
    course VARCHAR(30) NOT NULL,
    final_note TINYINT(2),
    PRIMARY KEY (year, student, course),
    CONSTRAINT FK_AssistStudent FOREIGN KEY (student) REFERENCES Student(registration_no) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_AssistCourse FOREIGN KEY (course) REFERENCES Course(name) ON DELETE CASCADE ON UPDATE CASCADE
);


DROP TABLE IF EXISTS CourseStudentExam;
CREATE TABLE CourseStudentExam (
    student INT NOT NULL,
    course VARCHAR(30) NOT NULL,
    year YEAR NOT NULL,
    note TINYINT(2) NOT NULL,
    CONSTRAINT FK_ExamStudent FOREIGN KEY (student) REFERENCES CourseStudent(student) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_ExamCourse FOREIGN KEY (course) REFERENCES CourseStudent(course) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_ExamYear FOREIGN KEY (year) REFERENCES CourseStudent(year) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Student (first_name, last_name, birthdate) VALUES 
    ('Darla', 'Hampton','1990-10-15'),      -- 1
    ('Kristen', 'Caldwell','1989-04-07'),   -- 2
    ('Ollie', 'Reeves','1990-01-11'),       -- 3
    ('Phyllis', 'Cain','1990-09-01'),       -- 4
    ('Jean', 'Russell','1990-04-01'),       -- 5
    ('Antonia', 'Bryan','1987-01-25'),      -- 6
    ('Richard', 'Coleman','1989-01-10'),    -- 7
    ('Juan', 'King','1990-01-01'),          -- 8
    ('John', 'Dawson','1990-01-01'),        -- 9
    ('Derek', 'Burke','1990-01-01'),        -- 10
    ('Ida', 'Jacobs','1990-01-01'),         -- 11
    ('Roderick', 'Underwood','1990-01-01'), -- 12
    ('Jordan', 'Smith','1990-01-01'),       -- 13
    ('Merle', 'Daniels','1990-01-01'),      -- 14
    ('Gloria', 'Gardner','1990-01-01'),     -- 15
    ('Tomas', 'Shelton','1990-01-01'),      -- 16
    ('Vicki', 'Craig','1990-01-01'),        -- 17
    ('Kelley', 'Webster','1990-01-01'),     -- 18
    ('Howard', 'Chapman','1990-01-01'),     -- 19
    ('Katie', 'Moran','1990-01-01');        -- 20

INSERT INTO Teacher (first_name, last_name, birthdate) VALUES
    ('Yuonne', 'Beale', '1982-12-30'),      -- 1
    ('Altagracia', 'Mcleod', '1964-9-15'),  -- 2
    ('Kimberley', 'Andrew', '1971-6-01');   -- 3

INSERT INTO Course (name, teacher, weeklyhours) VALUES
    ('Analisis y diseño de Sistemas', 1, 14),
    ('Base de datos', 1, 12),
    ('Diseño de algortimos I', 2, 14);
    
    
INSERT INTO CourseStudent (year, student, course) VALUES
    ('2018', 1, 'Analisis y diseño de Sistemas'),
    ('2018', 3, 'Analisis y diseño de Sistemas'),
    ('2018', 5, 'Analisis y diseño de Sistemas'),
    ('2018', 4, 'Analisis y diseño de Sistemas'),
    ('2018', 7, 'Analisis y diseño de Sistemas'),
    ('2018', 9, 'Analisis y diseño de Sistemas'),
    ('2018', 15, 'Analisis y diseño de Sistemas'),
    ('2018', 20, 'Analisis y diseño de Sistemas'),
    ('2018', 19, 'Analisis y diseño de Sistemas'),
    ('2018', 8, 'Analisis y diseño de Sistemas');

INSERT INTO CourseStudent (year, student, course) VALUES
    ('2018', 1, 'Base de datos'),
    ('2018', 2, 'Base de datos'),
    ('2018', 3, 'Base de datos'),
    ('2018', 4, 'Base de datos'),
    ('2018', 5, 'Base de datos'),
    ('2018', 6, 'Base de datos'),
    ('2018', 7,'Base de datos'),
    ('2018', 8,'Base de datos'),
    ('2018', 9,'Base de datos'),
    ('2018', 10, 'Base de datos');

INSERT INTO CourseStudent (year, student, course) VALUES
    ('2018', 11, 'Diseño de algortimos I'),
    ('2018', 12, 'Diseño de algortimos I'),
    ('2018', 13, 'Diseño de algortimos I'),
    ('2018', 14, 'Diseño de algortimos I'),
    ('2018', 15, 'Diseño de algortimos I'),
    ('2018', 16, 'Diseño de algortimos I'),
    ('2018', 17, 'Diseño de algortimos I'),
    ('2018', 18, 'Diseño de algortimos I'),
    ('2018', 19, 'Diseño de algortimos I'),
    ('2018', 20, 'Diseño de algortimos I');

INSERT INTO CourseStudentExam (year, student, course, note) VALUES
    ('2018', 11, 'Diseño de algortimos I', 7),
    ('2018', 12, 'Diseño de algortimos I', 6),
    ('2018', 13, 'Diseño de algortimos I', 7),
    ('2018', 14, 'Diseño de algortimos I', 6),
    ('2018', 15, 'Diseño de algortimos I', 7),
    ('2018', 16, 'Diseño de algortimos I', 6),
    ('2018', 17, 'Diseño de algortimos I', 6),
    ('2018', 18, 'Diseño de algortimos I', 7),
    ('2018', 19, 'Diseño de algortimos I', 6),
    ('2018', 20, 'Diseño de algortimos I', 2),
    ('2018', 11, 'Diseño de algortimos I', 6),
    ('2018', 12, 'Diseño de algortimos I', 2),
    ('2018', 13, 'Diseño de algortimos I', 7),
    ('2018', 14, 'Diseño de algortimos I', 10),
    ('2018', 15, 'Diseño de algortimos I', 7),
    ('2018', 16, 'Diseño de algortimos I', 10),
    ('2018', 17, 'Diseño de algortimos I', 7),
    ('2018', 18, 'Diseño de algortimos I', 6),
    ('2018', 19, 'Diseño de algortimos I', 10),
    ('2018', 20, 'Diseño de algortimos I', 6),
    ('2018', 11, 'Diseño de algortimos I', 10),
    ('2018', 12, 'Diseño de algortimos I', 7),
    ('2018', 13, 'Diseño de algortimos I', 4),
    ('2018', 14, 'Diseño de algortimos I', 6),
    ('2018', 15, 'Diseño de algortimos I', 6),
    ('2018', 16, 'Diseño de algortimos I', 7),
    ('2018', 17, 'Diseño de algortimos I', 4),
    ('2018', 18, 'Diseño de algortimos I', 6),
    ('2018', 19, 'Diseño de algortimos I', 7),
    ('2018', 20, 'Diseño de algortimos I', 4),

    ('2018', 1, 'Base de datos', 8),
    ('2018', 2, 'Base de datos', 6),
    ('2018', 3, 'Base de datos', 4),
    ('2018', 4, 'Base de datos', 7),
    ('2018', 5, 'Base de datos', 4),
    ('2018', 6, 'Base de datos', 7),
    ('2018', 7,'Base de datos', 4),
    ('2018', 8,'Base de datos', 7),
    ('2018', 9,'Base de datos', 8),
    ('2018', 10, 'Base de datos', 4),
    ('2018', 1, 'Base de datos', 6),
    ('2018', 2, 'Base de datos', 7),
    ('2018', 3, 'Base de datos', 4),
    ('2018', 4, 'Base de datos', 6),
    ('2018', 5, 'Base de datos', 6),
    ('2018', 6, 'Base de datos', 6),
    ('2018', 7,'Base de datos', 4),
    ('2018', 8,'Base de datos', 5),
    ('2018', 9,'Base de datos', 7),
    ('2018', 10, 'Base de datos', 4),
    ('2018', 1, 'Base de datos', 8),
    ('2018', 2, 'Base de datos', 4),
    ('2018', 3, 'Base de datos', 7),
    ('2018', 4, 'Base de datos', 7),
    ('2018', 5, 'Base de datos', 4),
    ('2018', 6, 'Base de datos', 8),
    ('2018', 7,'Base de datos', 8),
    ('2018', 8,'Base de datos', 8),
    ('2018', 9,'Base de datos', 7),
    ('2018', 10, 'Base de datos', 7),

    ('2018', 1, 'Analisis y diseño de Sistemas', 7),
    ('2018', 3, 'Analisis y diseño de Sistemas', 3),
    ('2018', 5, 'Analisis y diseño de Sistemas', 7),
    ('2018', 4, 'Analisis y diseño de Sistemas', 8),
    ('2018', 7, 'Analisis y diseño de Sistemas', 7),
    ('2018', 9, 'Analisis y diseño de Sistemas', 8),
    ('2018', 15, 'Analisis y diseño de Sistemas', 7),
    ('2018', 20, 'Analisis y diseño de Sistemas', 3),
    ('2018', 19, 'Analisis y diseño de Sistemas', 7),
    ('2018', 8, 'Analisis y diseño de Sistemas', 7),
    ('2018', 1, 'Analisis y diseño de Sistemas', 3),
    ('2018', 3, 'Analisis y diseño de Sistemas', 7),
    ('2018', 5, 'Analisis y diseño de Sistemas', 8),
    ('2018', 4, 'Analisis y diseño de Sistemas', 3),
    ('2018', 7, 'Analisis y diseño de Sistemas', 8),
    ('2018', 9, 'Analisis y diseño de Sistemas', 8),
    ('2018', 15, 'Analisis y diseño de Sistemas', 7),
    ('2018', 20, 'Analisis y diseño de Sistemas', 8),
    ('2018', 19, 'Analisis y diseño de Sistemas', 3),
    ('2018', 8, 'Analisis y diseño de Sistemas', 7),
    ('2018', 1, 'Analisis y diseño de Sistemas', 3),
    ('2018', 3, 'Analisis y diseño de Sistemas', 8),
    ('2018', 5, 'Analisis y diseño de Sistemas', 7),
    ('2018', 4, 'Analisis y diseño de Sistemas', 3),
    ('2018', 7, 'Analisis y diseño de Sistemas', 8),
    ('2018', 9, 'Analisis y diseño de Sistemas', 7),
    ('2018', 15, 'Analisis y diseño de Sistemas', 3),
    ('2018', 20, 'Analisis y diseño de Sistemas', 7),
    ('2018', 19, 'Analisis y diseño de Sistemas', 3),
    ('2018', 8, 'Analisis y diseño de Sistemas', 7);
    
INSERT INTO Schedule (course, weekday, start_time, end_time) VALUES
    ('Analisis y diseño de Sistemas', 1, '08:00', '12:00'),
    ('Analisis y diseño de Sistemas', 3, '08:00', '12:00' ),
    ('Analisis y diseño de Sistemas', 5, '14:00', '16:00'),
    ('Base de datos', 2, '08:00', '12:00' ),
    ('Base de datos', 4, '12:00', '16:00'),
    ('Diseño de algortimos I', 1, '13:00', '16:00'),
    ('Diseño de algortimos I', 4, '08:00', '12:00'),
    ('Diseño de algortimos I', 5, '10:00', '14:00');

SET @given_course = 'Analisis y diseño de Sistemas';
SELECT CONCAT('Course: ',c.Name),
       CONCAT('Teacher: ', t.last_name, ' ', t.first_name)
FROM
Course c INNER JOIN Teacher t
ON c.teacher = t.ID
WHERE c.name = @given_course;

SELECT 'Students: ';
SELECT CONCAT('\t', s.last_name, ' ', s.first_name)
FROM
CourseStudent cs INNER JOIN Student s
ON cs.student = s.registration_no
WHERE cs.course = @given_course;

SELECT '\n'; -- New Line

SET @course = 'Base de datos';
SET @aprove_average = 6;
SET @student_count = (SELECT COUNT(*) FROM CourseStudent where (course = @course));
SET @aproved_count =
                    (SELECT COUNT(*) 
                    FROM 
                        (SELECT AVG(note) AS average 
                        FROM CourseStudentExam
                        WHERE (course = @course)
                        GROUP BY student, course) AS averages
                    WHERE (average >= 6));
SET @aproved_percentage = CAST(((@aproved_count / @student_count)*100) AS DECIMAL(4,1));
SELECT CONCAT('Aproved students percentage for course ',@course, ' is: ',@aproved_percentage, '%');

SELECT '\n'; -- New Line

SET @given_teacher = 1;
SELECT CONCAT('Teacher: ', last_name, ' ', first_name)
FROM
    Teacher
WHERE ID = @given_teacher;

SELECT 'Schedule: ';
SELECT  CONCAT('\t',
              RPAD(schedule.weekday, 10, ' '),
              TIME_FORMAT(schedule.start_time, "%H:%i"), ' - ',
              TIME_FORMAT(schedule.end_time, "%H:%i"), ': ', 
              schedule.course)
FROM Teacher t INNER JOIN Course c
ON (t.ID = c.teacher && t.ID = @given_teacher)
INNER JOIN Schedule schedule
ON schedule.course = c.name
ORDER BY schedule.weekday ASC, schedule.start_time ASC;

EOF