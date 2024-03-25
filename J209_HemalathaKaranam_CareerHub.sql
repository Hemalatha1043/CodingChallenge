create database J209_CareerHub;
use  J209_careerHub;

CREATE TABLE Company (CompanyID INT PRIMARY KEY, CompanyName VARCHAR(255), Location VARCHAR(255));

CREATE TABLE JobListing (JobID INT PRIMARY KEY, CompanyID INT, JobTitle VARCHAR(255), JobDescription TEXT, JobLocation VARCHAR(255), Salary DECIMAL(10, 2), JobType VARCHAR(50), PostedDate DATETIME, FOREIGN KEY (CompanyID) REFERENCES Company(CompanyID));

CREATE TABLE Applicant (ApplicantID INT PRIMARY KEY, FirstName VARCHAR(100), LastName VARCHAR(100), Email VARCHAR(255), Phone VARCHAR(20), Resume VARCHAR(255));

CREATE TABLE JobApplication (ApplicationID INT PRIMARY KEY, JobID INT, ApplicantID INT, ApplicationDate DATETIME, CoverLetter TEXT, FOREIGN KEY (JobID) REFERENCES JobListing(JobID), FOREIGN KEY (ApplicantID) REFERENCES Applicant(ApplicantID));



select * from company;
select * from joblisting;
select * from Applicant;
select * from JobApplication;

