package com.careerhub.exception;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.careerhub.entity.JobListing;

public class SalaryCalculator {
    public static void main(String[] args) {
        try {
            // Establish database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/J209_CareerHub", "root", "Hemalatha_1043");

            // Retrieve job listings from the database
            List<JobListing> jobListings = getJobListings(connection);

            // Calculate average salary
            double averageSalary = calculateAverageSalary(jobListings);
            System.out.println("Average Salary: " + averageSalary);

            // Close database connection
            connection.close();
        } catch (SQLException | NegativeSalaryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static List<JobListing> getJobListings(Connection connection) throws SQLException {
        List<JobListing> jobListings = new ArrayList<>();
        String query = "SELECT * FROM JobListing";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                JobListing job = new JobListing(
                        resultSet.getInt("JobID"),
                        resultSet.getInt("CompanyID"),
                        resultSet.getString("JobTitle"),
                        resultSet.getString("JobDescription"),
                        resultSet.getString("JobLocation"),
                        resultSet.getDouble("Salary"),
                        resultSet.getString("JobType"),
                        resultSet.getString("PostedDate")
                );
                jobListings.add(job);
            }
        }
        return jobListings;
    }

    public static double calculateAverageSalary(List<JobListing> jobListings) throws NegativeSalaryException {
        double total = 0;
        int count = 0;

        for (JobListing job : jobListings) {
            if (job.getSalary() < 0) {
                throw new NegativeSalaryException("Negative salary found: " + job.getSalary() + " for job ID: " + job.getJobID());
            }
            total += job.getSalary();
            count++;
        }

        return total / count;
    }
}
