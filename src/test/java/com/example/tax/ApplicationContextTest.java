package com.example.tax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class ApplicationContextTest {

    @Test
    void loadsTaxBeansFromXmlConfiguration() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            Tax incomeTax = (Tax) context.getBean("incomeTax");
            Tax propertyTax = (Tax) context.getBean("propertyTax");

            assertNotNull(incomeTax);
            assertNotNull(propertyTax);
            assertEquals("income", incomeTax.getTaxType());
            assertEquals("property", propertyTax.getTaxType());
        }
    }
}
