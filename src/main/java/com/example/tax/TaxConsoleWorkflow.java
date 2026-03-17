package com.example.tax;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;

public class TaxConsoleWorkflow {

    private final ApplicationContext context;
    private final Scanner scanner;

    public TaxConsoleWorkflow(ApplicationContext context, Scanner scanner) {
        this.context = context;
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("Welcome to the Tax Payment Application");

        while (true) {
            printMenu();

            String choiceInput = readLine();
            if (choiceInput == null) {
                System.out.println("Exiting...");
                return;
            }

            Integer userChoice = parseInteger(choiceInput);
            if (userChoice == null) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (userChoice == 3) {
                System.out.println("Exiting...");
                return;
            }

            Tax tax = getTaxFromChoice(userChoice);
            if (tax == null) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            System.out.println("Selected Tax Type: " + tax.getTaxType());
            System.out.println("Enter the taxable amount:");

            String amountInput = readLine();
            Integer taxableAmount = parseInteger(amountInput);
            if (taxableAmount == null || taxableAmount < 0) {
                System.out.println("Invalid taxable amount. Please try again.");
                continue;
            }

            tax.setTaxableAmount(taxableAmount);
            tax.calculateTaxAmount();

            System.out.println("Tax Amount: " + tax.getTaxAmount());
            System.out.println("Do you want to pay this tax? (yes/no)");

            String payChoice = readLine();
            if ("yes".equalsIgnoreCase(payChoice)) {
                tax.payTax();
            } else {
                System.out.println("Tax payment canceled.");
            }
        }
    }

    private void printMenu() {
        System.out.println("Please select which tax you want to pay:");
        System.out.println("1. Income");
        System.out.println("2. Property");
        System.out.println("3. Exit");
    }

    private Tax getTaxFromChoice(int userChoice) {
        return switch (userChoice) {
            case 1 -> context.getBean("incomeTax", Tax.class);
            case 2 -> context.getBean("propertyTax", Tax.class);
            default -> null;
        };
    }

    private String readLine() {
        if (!scanner.hasNextLine()) {
            return null;
        }

        return scanner.nextLine().trim();
    }

    private Integer parseInteger(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            return null;
        }
    }
}
