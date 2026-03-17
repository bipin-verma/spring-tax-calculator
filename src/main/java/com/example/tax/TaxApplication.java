package com.example.tax;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TaxApplication {

    public static void main(String[] args) {
        try (
                ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
                java.util.Scanner scanner = new java.util.Scanner(System.in)
        ) {
            new TaxConsoleWorkflow(context, scanner).run();
        }
    }
}
