# Spring Tax Calculator

Spring Tax Calculator is a compact Java 17 project that demonstrates XML-based dependency injection with Spring and basic tax calculation logic for income tax and property tax.

## Tech Stack

- Java 17
- Maven
- Spring Framework XML configuration
- JUnit 5

## Project Overview

This `v1` version focuses on a simple contract-driven design:

- `Tax` defines the common behavior for tax implementations.
- `IncomeTax` calculates tax using simplified slab-based logic.
- `PropertyTax` calculates tax as 5% of the property value.
- `applicationContext.xml` wires both implementations as Spring beans.
- `TaxApplication` loads the Spring context, uses each bean, prints the calculated amount, and marks the tax as paid.

## Current Flow

1. The application starts in `TaxApplication`.
2. Spring loads `applicationContext.xml`.
3. The context creates `incomeTax` and `propertyTax` beans.
4. The application sets taxable amounts for both beans.
5. Each implementation calculates its tax amount.
6. The application prints the tax amount and calls `payTax()`.
7. The bean marks the tax as paid for the current runtime instance.

## How To Run

```bash
mvn test
mvn package
java -jar target/spring-tax-calculator-0.0.1-SNAPSHOT.jar
```

If you prefer the Maven Wrapper, use `mvnw.cmd` on Windows or `./mvnw` on Unix-like systems.

## Sample Output

```text
Income Tax Amount: 180000.0
Hi, your income tax is paid.
Property Tax Amount: 250000.0
Hi, your property tax is paid.
```

## Known Limitations

- Income tax currently uses simplified slab logic, meaning one rate is applied to the full amount in a bracket.
- The project is a console-based demonstration and does not expose a REST API.
- Tax payment state is stored only in memory and is reset each time the application starts.
- There is no persistence layer or external data source in `v1`.

## Future Versions Roadmap

- `v1`: XML bean wiring and console-based tax calculation demo.
- `v2`: planned enhancement built on top of the current codebase.
- `v3`: planned enhancement that continues the same project history.

## Why This Repo Exists

This repository is intended as a learning and portfolio project that shows:

- interface-based design
- basic Spring bean configuration
- simple business-logic implementation
- incremental project evolution across versions
