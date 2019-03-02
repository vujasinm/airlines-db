DROP DATABASE IF EXISTS final_project;
CREATE DATABASE final_project;

-- select the databasepassenger
USE final_project;

-- create the tables
CREATE TABLE Plane
(
  PlaneID 		INT 			NOT NULL 	AUTO_INCREMENT,
  Brand 		VARCHAR(25) 	NOT NULL,
  Type 			INT 			NOT NULL,
  Capacity 		INT 			NULL,
  PRIMARY KEY (PlaneID)
);

CREATE TABLE Airport
(
	AirportName	 	VARCHAR(25) 	NOT NULL,
	City			VARCHAR(45) 	NOT NULL,
	Country			VARCHAR(45) 	NOT NULL,
	PRIMARY KEY (AirportName)
);

CREATE TABLE Flights
(
	FlightNo	 		VARCHAR(5) 		NOT NULL,
	PlaneID		 		INT 			NOT NULL,
  	OriginAirport 		VARCHAR(25) 	NOT NULL,
	DestinationAirport 	VARCHAR(25) 	NOT NULL,
	DepartureTime		DATETIME 		NOT NULL,
	ArrivalTime 		DATETIME 		NOT NULL,
	PRIMARY KEY (FlightNo),
	FOREIGN KEY (PlaneID)
    REFERENCES final_project.Plane(PlaneID)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
	FOREIGN KEY (OriginAirport)
    REFERENCES final_project.Airport(AirportName) 
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (DestinationAirport)
    REFERENCES final_project.Airport(AirportName)
	ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Passenger
(
	FirstName	 VARCHAR(45) 		NOT NULL,
	MiddleName	 VARCHAR(45) 		NULL,
	LastName	 VARCHAR(45) 		NOT NULL,
	DOB			 DATE 				NOT NULL,
	Flight 		 VARCHAR(5) 		NOT NULL,
	Seat 		 VARCHAR(3) 		NULL,
	PassengerID  INT 				NOT NULL 	AUTO_INCREMENT,
	PRIMARY KEY (PassengerID),
	CONSTRAINT FlightNumber
    FOREIGN KEY (Flight)
    REFERENCES final_project.Flights(FlightNo)
	ON DELETE CASCADE
    ON UPDATE CASCADE
);
-- import data
INSERT INTO airport (AirportName, City ,Country)
VALUES ('DXB','Dubai','UAE'), ('JFK','New York','USA'), ('FRA','Frankfurt','Germany'), ('LHR','London','UK'), 
 ('BOM','Mumbai','India'), ('SFO','San Francisco','USA'), ('BOS','Boston','USA'), ('LAX','Los Angeles','USA');
 
 INSERT INTO plane (PlaneID,Brand,Type,Capacity)
 VALUES 
 (1,'Emirates',380,853),
(2,'American Airlines',777,400),
(3,'Lufthansa',330,247),
(4,'British Airways',330,247),
(5,'Etihad',380,853),
(6,'Delta Airlines',777,400);
 
INSERT INTO flights (FlightNo, PlaneID, OriginAirport ,DestinationAirport ,DepartureTime, ArrivalTime)
VALUES ('AA234',2,'JFK','BOS','2016-4-10 9:00','2016-4-10 10:00'),
('DL021',6,'BOS','SFO','2016-4-10 22:00','2016-4-10 1:40'),
('BA218',4,'LHR','BOM','2016-4-10 13:00','2016-4-10 0:50'),
('LF560',3,'LAX','FRA','2016-4-10 19:00','2016-4-10 6:00'),
('EK281',1,'DXB','JFK','2016-4-10 11:30','2016-4-10 15:00'),
('LF450',3,'FRA','BOM','2016-4-10 12:55','2016-4-10 11:35'),
('EK201',1,'BOS','DXB','2016-4-10 21:30','2016-4-10 7:10'),
('EH330',5,'JFK','LHR','2016-4-10 18:50','2016-4-10 7:15'),
('DL256',6,'LAX','SFO','2016-4-10 16:00','2016-4-10 17:00');

INSERT INTO passenger (FirstName,MiddleName,LastName,DOB,Flight,Seat,PassengerID)
VALUES
('Lakshmi','','Venkatesh','1995-5-31','EK201','5K',1310595),
('Marija','','Vujasin','1992-9-17','DL021','1C',2170992);

