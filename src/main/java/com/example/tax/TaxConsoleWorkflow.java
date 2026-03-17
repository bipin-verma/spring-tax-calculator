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

            if (tax.isTaxPayed()) {
                System.out.println("You have already paid " + tax.getTaxType() + " tax.");
                continue;
            }

            System.out.println("Please enter your " + tax.getTaxType() + " value:");

            String amountInput = readLine();
            Integer taxableAmount = parseInteger(amountInput);
            if (taxableAmount == null || taxableAmount < 0) {
                System.out.println("Invalid taxable amount. Please try again.");
                continue;
            }

            tax.setTaxableAmount(taxableAmount);
            tax.calculateTaxAmount();

            System.out.println("You have selected " + tax.getTaxType() + " tax and your tax amount is: " + tax.getTaxAmount());
            System.out.println("Do you want to pay the tax?");
            System.out.println("1. Yes");
            System.out.println("2. No");

            String payChoiceInput = readLine();
            Integer payChoice = parseInteger(payChoiceInput);
            if (payChoice == null) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (payChoice == 1) {
                tax.payTax();
            } else if (payChoice == 2) {
                System.out.println("Tax payment cancelled.");
            } else {
                System.out.println("Invalid choice. Please try again.");
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
