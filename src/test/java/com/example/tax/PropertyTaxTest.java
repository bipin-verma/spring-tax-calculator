package com.example.tax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PropertyTaxTest {

    @Test
    void calculatesPropertyTaxAtFivePercent() {
        PropertyTax propertyTax = new PropertyTax();

        propertyTax.setTaxableAmount(5000000);
        propertyTax.calculateTaxAmount();

        assertEquals(250000.0, propertyTax.getTaxAmount());
    }

    @Test
    void marksPropertyTaxAsPaidAndHandlesRepeatedPayment() {
        PropertyTax propertyTax = new PropertyTax();

        assertFalse(propertyTax.isTaxPayed());

        String output = TaxConsoleTestSupport.captureOutput(() -> {
            propertyTax.payTax();
            propertyTax.payTax();
        });

        assertTrue(propertyTax.isTaxPayed());
        assertTrue(output.contains("Hi, your property tax is paid."));
        assertTrue(output.contains("Tax is already paid."));
    }
}
