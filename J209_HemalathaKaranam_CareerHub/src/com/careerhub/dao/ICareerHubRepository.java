package com.careerhub.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.careerhub.entity.Applicant;
import com.careerhub.entity.Company;
import com.careerhub.entity.JobApplication;
import com.careerhub.entity.JobListing;

public interface ICareerHubRepository {
	
    //void apply(JobApplication application) throws SQLException;
    
    //List<Applicant> getJobApplicants(int jobID) throws SQLException;

    //void postJob(String jobTitle, String jobDescription, String jobLocation, double salary, String jobType, int companyID) throws SQLException;

    //List<JobListing> getCompanyJobs(int companyID) throws SQLException;
    
    //void createProfile(String email, String firstName, String lastName, String phone, String resume) throws SQLException;
    
    //void applyForJob(int jobID, int applicantID, String coverLetter) throws SQLException;

    void insertJobListing(JobListing job) throws SQLException;

    void insertCompany(Company company) throws SQLException;

    void insertApplicant(Applicant applicant) throws SQLException;

    void insertJobApplication(JobApplication application) throws SQLException;

    List<JobListing> getJobListings() throws SQLException;

    List<Company> getCompanies() throws SQLException;

    List<Applicant> getApplicants() throws SQLException;

    List<JobApplication> getApplicationsForJob(int jobID) throws SQLException;






}

