package com.example.tax;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class TaxConsoleWorkflowTest {

    @Test
    void runsIncomeTaxFlowAndPaysSuccessfully() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            TaxConsoleWorkflow workflow = new TaxConsoleWorkflow(
                    context,
                    scannerFor("1", "1200000", "1", "3")
            );

            String output = TaxConsoleTestSupport.captureOutput(workflow::run);

            assertTrue(output.contains("Welcome to the Tax Payment Application"));
            assertTrue(output.contains("Please enter your income value:"));
            assertTrue(output.contains("You have selected income tax and your tax amount is: 180000.0"));
            assertTrue(output.contains("Hi, your income tax is paid."));
            assertTrue(output.contains("Exiting..."));
        }
    }

    @Test
    void handlesInvalidChoiceAndPropertyTaxCancellation() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            TaxConsoleWorkflow workflow = new TaxConsoleWorkflow(
                    context,
                    scannerFor("9", "2", "5000000", "2", "3")
            );

            String output = TaxConsoleTestSupport.captureOutput(workflow::run);

            assertTrue(output.contains("Invalid choice. Please try again."));
            assertTrue(output.contains("Please enter your property value:"));
            assertTrue(output.contains("You have selected property tax and your tax amount is: 250000.0"));
            assertTrue(output.contains("Tax payment cancelled."));
            assertTrue(output.contains("Exiting..."));
        }
    }

    @Test
    void preventsPayingTheSameTaxTwiceInOneSession() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            TaxConsoleWorkflow workflow = new TaxConsoleWorkflow(
                    context,
                    scannerFor("1", "1200000", "1", "1", "3")
            );

            String output = TaxConsoleTestSupport.captureOutput(workflow::run);

            assertTrue(output.contains("Hi, your income tax is paid."));
            assertTrue(output.contains("You have already paid income tax."));
            assertTrue(output.contains("Exiting..."));
        }
    }

    private Scanner scannerFor(String... lines) {
        String input = String.join(System.lineSeparator(), lines) + System.lineSeparator();
        return new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    }
}
