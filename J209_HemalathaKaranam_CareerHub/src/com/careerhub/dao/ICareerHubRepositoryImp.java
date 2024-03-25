package com.careerhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.careerhub.entity.Applicant;
import com.careerhub.entity.Company;
import com.careerhub.entity.JobApplication;
import com.careerhub.entity.JobListing;
import com.careerhub.util.DBUtil;

public class ICareerHubRepositoryImp implements ICareerHubRepository {
	Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;

	@Override
	public void insertJobListing(JobListing job) throws SQLException {
		con = DBUtil.getDBConn();
		stmt = con.createStatement();
		String query = "INSERT INTO JobListing(JobID, CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setInt(1, job.getJobID());
			preparedStatement.setInt(2, job.getCompanyID());
			preparedStatement.setString(3, job.getJobTitle());
			preparedStatement.setString(4, job.getJobDescription());
			preparedStatement.setString(5, job.getJobLocation());
			preparedStatement.setDouble(6, job.getSalary());
			preparedStatement.setString(7, job.getJobType());
			preparedStatement.setString(8, job.getPostedDate());
			preparedStatement.executeUpdate();
		}

	}

	@Override
	public void insertCompany(Company company) throws SQLException {
		con = DBUtil.getDBConn();
		stmt = con.createStatement();
		String query = "INSERT INTO Company (CompanyID, CompanyName, Location) VALUES (?, ?, ?)";
		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setInt(1, company.getCompanyID());
			preparedStatement.setString(2, company.getCompanyName());
			preparedStatement.setString(3, company.getLocation());
			preparedStatement.executeUpdate();
		}
	}

	@Override
	public void insertApplicant(Applicant applicant) throws SQLException {
		con = DBUtil.getDBConn();
		stmt = con.createStatement();
		String query = "INSERT INTO Applicant (ApplicantID, FirstName, LastName, Email, Phone, Resume) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setInt(1, applicant.getApplicantID());
			preparedStatement.setString(2, applicant.getFirstName());
			preparedStatement.setString(3, applicant.getLastName());
			preparedStatement.setString(4, applicant.getEmail());
			preparedStatement.setString(5, applicant.getPhone());
			preparedStatement.setString(6, applicant.getResume());
			preparedStatement.executeUpdate();
		}
	}

	@Override
	public void insertJobApplication(JobApplication application) throws SQLException {
		con = DBUtil.getDBConn();
		stmt = con.createStatement();
		String query = "INSERT INTO JobApplication (ApplicationID, JobID, ApplicantID, ApplicationDate, CoverLetter) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setInt(1, application.getApplicationID());
			preparedStatement.setInt(2, application.getJobID());
			preparedStatement.setInt(3, application.getApplicantID());
			preparedStatement.setString(4, application.getApplicationDate());
			preparedStatement.setString(5, application.getCoverLetter());
			preparedStatement.executeUpdate();
		}
	}

	public List<JobListing> getJobListings() throws SQLException {
		con = DBUtil.getDBConn();
		stmt = con.createStatement();
		List<JobListing> jobListings = new ArrayList<>();
		String query = "SELECT * FROM JobListing";
		try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			while (resultSet.next()) {
				JobListing job = new JobListing(resultSet.getInt("JobID"), resultSet.getInt("CompanyID"),
						resultSet.getString("JobTitle"), resultSet.getString("JobDescription"),
						resultSet.getString("JobLocation"), resultSet.getDouble("Salary"),
						resultSet.getString("JobType"), resultSet.getString("PostedDate"));
				jobListings.add(job);
			}
		}
		return jobListings;
	}

	@Override
	public List<Company> getCompanies() throws SQLException {
		con = DBUtil.getDBConn();
		stmt = con.createStatement();
		List<Company> companies = new ArrayList<>();
		String query = "SELECT * FROM Company";
		try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			while (resultSet.next()) {
				Company company = new Company(resultSet.getInt("CompanyID"), resultSet.getString("CompanyName"),
						resultSet.getString("Location"));
				companies.add(company);
			}
		}
		return companies;
	}

	@Override
	public List<Applicant> getApplicants() throws SQLException {
		con = DBUtil.getDBConn();
		stmt = con.createStatement();
		List<Applicant> applicants = new ArrayList<>();
		String query = "SELECT * FROM Applicant";
		try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			while (resultSet.next()) {
				Applicant applicant = new Applicant(resultSet.getInt("ApplicantID"), resultSet.getString("FirstName"),
						resultSet.getString("LastName"), resultSet.getString("Email"), resultSet.getString("Phone"),
						resultSet.getString("Resume"));
				applicants.add(applicant);
			}
		}
		return applicants;
	}

	@Override
	public List<JobApplication> getApplicationsForJob(int jobID) throws SQLException {
		con = DBUtil.getDBConn();
		stmt = con.createStatement();
		List<JobApplication> applications = new ArrayList<>();
		String query = "SELECT * FROM JobApplication WHERE JobID = ?";
		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setInt(1, jobID);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					JobApplication application = new JobApplication(resultSet.getInt("ApplicationID"),
							resultSet.getInt("JobID"), resultSet.getInt("ApplicantID"),
							resultSet.getString("ApplicationDate"), resultSet.getString("CoverLetter"));
					applications.add(application);
				}
			}
		}
		return applications;
	}

}
