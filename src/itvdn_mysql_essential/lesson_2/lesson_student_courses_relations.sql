CREATE TABLE Students
(
    StudentId int NOT NULL AUTO_INCREMENT ,
    FName varchar(50) NOT NULL,
    LName varchar(50) NOT NULL,
    Email varchar(50) NOT NULL,
    Phone varchar(50) NOT NULL,
    primary key (StudentId)
);

CREATE TABLE Courses
(
    CourseId int  NOT NULL auto_increment,
    CourseName varchar(50) NOT NULL,
    Price double(10,2) Not Null,
    PRIMARY KEY (CourseId)
);


CREATE TABLE StudentsCourses
(
    StudentId int NOT NULL,
    CourseId int NOT NULL,
    PRIMARY KEY(StudentId, CourseId),
    FOREIGN KEY (StudentId) references Students(StudentId),
    FOREIGN KEY (CourseId) references Courses(CourseId)
);

INSERT INTO Students
(FName, LName, Email, Phone)
VALUES
('Petro', 'Petrov', 'PetrPetrenko@mail.com', '(093)1231212'),
('Ivan', 'Ivanov', 'IvanenkoIvan@mail.com', '(095)2313244'),
('Fedor', 'F', 'MaximovMax@mail.com', '(095)7658786');

INSERT INTO Courses
(CourseName, Price)
VALUES
('SQL Essential', 100.00),
('C# Professional', 200.00),
('ASP.NET MVC', 300.00),
('Patterns GoF', 400.00);

INSERT INTO StudentsCourses
(StudentId, CourseId)
VALUES
(1,1),
(2,1),
(3,3),
(3,1),
(2,2);


SELECT * FROM Students;
SELECT * FROM StudentsCourses;
SELECT * FROM Courses;