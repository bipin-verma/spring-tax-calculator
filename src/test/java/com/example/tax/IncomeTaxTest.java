package com.example.tax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class IncomeTaxTest {

    @ParameterizedTest
    @CsvSource({
            "300000, 0.0",
            "600000, 30000.0",
            "900000, 90000.0",
            "1200000, 180000.0",
            "1500000, 300000.0",
            "1600000, 480000.0"
    })
    void calculatesTaxUsingCurrentSlabRules(int taxableAmount, double expectedTaxAmount) {
        IncomeTax incomeTax = new IncomeTax();

        incomeTax.setTaxableAmount(taxableAmount);
        incomeTax.calculateTaxAmount();

        assertEquals(expectedTaxAmount, incomeTax.getTaxAmount());
    }

    @Test
    void marksIncomeTaxAsPaidAndHandlesRepeatedPayment() {
        IncomeTax incomeTax = new IncomeTax();

        assertFalse(incomeTax.isTaxPayed());

        String output = TaxConsoleTestSupport.captureOutput(() -> {
            incomeTax.payTax();
            incomeTax.payTax();
        });

        assertTrue(incomeTax.isTaxPayed());
        assertTrue(output.contains("Hi, your income tax is paid."));
        assertTrue(output.contains("Tax is already paid."));
    }
}
