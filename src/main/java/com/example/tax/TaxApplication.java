package com.example.tax;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TaxApplication {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            // Retrieve the IncomeTax bean and perform operations
            Tax incomeTax = (Tax) context.getBean("incomeTax");
            incomeTax.setTaxableAmount(1200000); // Example taxable amount
            incomeTax.calculateTaxAmount();
            System.out.println("Income Tax Amount: " + incomeTax.getTaxAmount());
            incomeTax.payTax();

            // Retrieve the PropertyTax bean and perform operations
            Tax propertyTax = (Tax) context.getBean("propertyTax");
            propertyTax.setTaxableAmount(5000000); // Example taxable amount
            propertyTax.calculateTaxAmount();
            System.out.println("Property Tax Amount: " + propertyTax.getTaxAmount());
            propertyTax.payTax();
        }
    }
}
