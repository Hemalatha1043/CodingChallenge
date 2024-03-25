package com.careerhub.exception;

import java.util.Scanner;

public class EmailValidation {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();

        try {
            validateEmail(email);
            System.out.println("Email is valid. Proceed with registration.");
        } catch (InvalidEmailFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
        finally {
        	scanner.close();
        }
	}

    public static void validateEmail(String email) throws InvalidEmailFormatException {
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new InvalidEmailFormatException("Invalid email format.");
        }
    }
   
}