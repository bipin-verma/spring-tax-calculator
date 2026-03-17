# Changelog

All notable changes to this project are documented in this file.

## [v3.0.0] - 2026-03-18

### Summary
Third version of the project that improves the console workflow by tracking tax payment status during the current application session.

### Highlights
- Added in-session paid-status detection for each tax bean.
- Prevented paying the same tax twice in one runtime session.
- Improved console prompts and payment selection flow.
- Added tests for already-paid workflow behavior.
- Updated README and flow documentation for the `v3` user experience.

### Notes
This version improves application behavior and user flow by introducing session-level payment awareness on top of the interactive `v2` design.

## [v2.0.0] - 2026-03-18

### Summary
Second version of the project that upgrades the fixed demo flow into an interactive console-based tax payment workflow.

### Highlights
- Added menu-driven console interaction.
- Let users choose between income tax and property tax.
- Added taxable amount input from the console.
- Added optional payment confirmation during runtime.
- Improved test coverage for interactive workflow scenarios.
- Updated README to document the new user flow.

### Notes
This version shifts the project from a static example into a user-driven console application while keeping the same core tax logic.

## [v1.0.0] - 2026-03-18

### Summary
Initial version of the Spring Tax Calculator built with Java 17 and XML-based Spring bean configuration.

### Highlights
- Added a common `Tax` interface.
- Added `IncomeTax` and `PropertyTax` implementations.
- Wired beans using `applicationContext.xml`.
- Implemented a simple console-based demo flow with fixed sample values.
- Added unit tests for tax calculation and payment behavior.
- Added project documentation and Maven build setup.

### Notes
This version establishes the project foundation and focuses on basic Spring wiring plus tax calculation logic.
