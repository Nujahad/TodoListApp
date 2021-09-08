DROP TABLE IF EXISTS USER;

CREATE TABLE USER (
                      id INT AUTO_INCREMENT  PRIMARY KEY,
                      username VARCHAR(250) NOT NULL,
                      password VARCHAR(250) NOT NULL,
);

CREATE TABLE TASK (
                      id INT AUTO_INCREMENT  PRIMARY KEY,
                      taskName VARCHAR(250),
                      taskDescription VARCHAR(250) NOT NULL,
                      taskStatus VARCHAR(250) NOT NULL,
                      userId INT NOT NULL,
                      lastModified TIMESTAMP NOT NULL
);