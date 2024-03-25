package com.careerhub.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.careerhub.dao.ICareerHubRepository;
import com.careerhub.dao.ICareerHubRepositoryImp;
import com.careerhub.entity.Applicant;
import com.careerhub.entity.Company;
import com.careerhub.entity.JobApplication;
import com.careerhub.entity.JobListing;

public class MainModule {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ICareerHubRepository repository = new ICareerHubRepositoryImp();

		boolean exit = false;
		while (!exit) {
			System.out.println("********** Career Hub **********");
			System.out.println("1. Add job	");
			System.out.println("2. Add company");
			System.out.println("3. Add applicant");
			System.out.println("4. Add application");
			System.out.println("5. GetJobListings");
			System.out.println("6. GetCompanies");
			System.out.println("7. GetApplicants");
			System.out.println("8. GetApplicationsForJob");
			System.out.println("9. Exit");

			System.out.print("Enter your choice: ");
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					insertJobListing();
					break;
				case 2:
					insertCompany();
					break;
				case 3:
					insertApplicant();
					break;
				case 4:
					insertJobApplication();
					break;
				case 5:
					getJobListings(repository);
					break;
				case 6:
					getCompanies(repository);
					break;
				case 7:
					getApplicants();
					break;
				case 8:
					getApplicationsForJob(repository);
					break;
				case 19:
					exit = true;
					System.out.println("Thank you");
					break;
				default:
					System.out.println("Invalid choice! Please enter a number between 1 and 12.");

				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Invalid input. Please enter a valid integer choice.");
			} finally {
				scanner.close(); // Close the scanner
			}
		}
	}

	private static <Date> void insertJobListing() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter job details:");

		System.out.print("Job ID: ");
		int jobID = scanner.nextInt();
		scanner.nextLine(); // Consume newline character

		System.out.print("Company ID: ");
		int companyID = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Job Title: ");
		String jobTitle = scanner.nextLine();

		System.out.print("Job Description: ");
		String jobDescription = scanner.nextLine();

		System.out.print("Job Location: ");
		String jobLocation = scanner.nextLine();

		System.out.print("Salary: ");
		double salary = scanner.nextDouble();
		scanner.nextLine();

		System.out.print("Job Type: ");
		String jobType = scanner.nextLine();

		System.out.print("Posted Date (Format: yyyy-MM-dd): ");
		String postedDate = scanner.nextLine();

		JobListing jobListing = new JobListing(jobID, companyID, jobTitle, jobDescription, jobLocation, salary, jobType,
				postedDate);

		try {
			ICareerHubRepositoryImp repository = new ICareerHubRepositoryImp();
			repository.insertJobListing(jobListing);
			System.out.println("Job listing added successfully.");
		} catch (SQLException e) {
			System.out.println("Error occurred while adding job listing: " + e.getMessage());
		} finally {
			scanner.close();
		}

	}

	private static void insertCompany() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter company details:");

		System.out.print("Company ID: ");
		int companyID = scanner.nextInt();
		scanner.nextLine(); // Consume newline character

		System.out.print("Company Name: ");
		String companyName = scanner.nextLine();

		System.out.print("Location: ");
		String location = scanner.nextLine();

		Company company = new Company(companyID, companyName, location);

		try {
			ICareerHubRepositoryImp repository = new ICareerHubRepositoryImp();
			repository.insertCompany(company);
			System.out.println("Company added successfully.");
		} catch (SQLException e) {
			System.out.println("Error occurred while adding company: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

	private static void insertApplicant() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter applicant details:");

			System.out.print("Applicant ID: ");
			int applicantID = scanner.nextInt();
			scanner.nextLine(); // Consume newline character

			System.out.print("First Name: ");
			String firstName = scanner.nextLine();

			System.out.print("Last Name: ");
			String lastName = scanner.nextLine();

			System.out.print("Email: ");
			String email = scanner.nextLine();

			System.out.print("Phone: ");
			String phone = scanner.nextLine();

			System.out.print("Resume: ");
			String resume = scanner.nextLine();

			Applicant applicant = new Applicant(applicantID, firstName, lastName, email, phone, resume);

			ICareerHubRepository repository = new ICareerHubRepositoryImp();
			repository.insertApplicant(applicant);
			System.out.println("Applicant added successfully.");
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid integer for the applicant ID.");
		} catch (SQLException e) {
			System.out.println("Error occurred while adding applicant: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

	private static void insertJobApplication() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter job application details:");

			System.out.print("Application ID: ");
			int applicationID = scanner.nextInt();
			scanner.nextLine(); // Consume newline character

			System.out.print("Job ID: ");
			int jobID = scanner.nextInt();
			scanner.nextLine(); // Consume newline character

			System.out.print("Applicant ID: ");
			int applicantID = scanner.nextInt();
			scanner.nextLine();

			System.out.print("Application Date (Format: yyyy-MM-dd): ");
			String applicationDate = scanner.nextLine();

			System.out.print("Cover Letter: ");
			String coverLetter = scanner.nextLine();

			JobApplication jobApplication = new JobApplication(applicationID, jobID, applicantID, applicationDate,
					coverLetter);

			ICareerHubRepository repository = new ICareerHubRepositoryImp();
			repository.insertJobApplication(jobApplication);
			System.out.println("Job application added successfully.");
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter valid data.");
		} catch (SQLException e) {
			System.out.println("Error occurred while adding job application: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

	private static void getJobListings(ICareerHubRepository repository) {
		try {
			List<JobListing> jobListings = repository.getJobListings();
			System.out.println("Job Listings:");
			for (JobListing job : jobListings) {
				System.out.println("Job ID: " + job.getJobID());
				System.out.println("Company ID: " + job.getCompanyID());
				System.out.println("Job Title: " + job.getJobTitle());
				System.out.println("Job Description: " + job.getJobDescription());
				System.out.println("Job Location: " + job.getJobLocation());
				System.out.println("Salary: " + job.getSalary());
				System.out.println("Job Type: " + job.getJobType());
				System.out.println("Posted Date: " + job.getPostedDate());
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Error fetching job listings: " + e.getMessage());
		}
	}

	private static void getCompanies(ICareerHubRepository repository) {
		try {
			List<Company> companies = repository.getCompanies();
			System.out.println("Companies:");
			for (Company company : companies) {
				System.out.println("Company ID: " + company.getCompanyID());
				System.out.println("Company Name: " + company.getCompanyName());
				System.out.println("Location: " + company.getLocation());
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Error fetching companies: " + e.getMessage());
		}
	}

	private static void getApplicants() {
		try {
			ICareerHubRepositoryImp repository = new ICareerHubRepositoryImp();
			List<Applicant> applicants = repository.getApplicants();
			System.out.println("Applicants:");
			for (Applicant applicant : applicants) {
				System.out.println(applicant.toString());
			}
		} catch (SQLException e) {
			System.out.println("Error occurred while getting applicants: " + e.getMessage());
		}
	}

	private static void getApplicationsForJob(ICareerHubRepository repository) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.print("Enter Job ID: ");
			int jobID = scanner.nextInt();
			scanner.nextLine();

			List<JobApplication> applications = repository.getApplicationsForJob(jobID);
			System.out.println("Job Applications for Job ID " + jobID + ":");
			for (JobApplication application : applications) {
				System.out.println(application.toString());
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid integer for the job ID.");
		} catch (SQLException e) {
			System.out.println("Error occurred while getting job applications: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}
}
