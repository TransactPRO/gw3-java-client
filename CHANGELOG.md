##### Version 2.0.1 (2024-10-02)

	Add crypto data expired error code

##### Version 2.0.0 (2023-11-09)

	Remove support for Java 8
	Update dependencies to fix vulnerabilities issues
	Added new fields for order data to satisfy AN5524 requirements:
	 - mits-expected: must be set to true for UCOF initialization if any subsequent MIT transactions are supposed to be
	 - variable-amount-recurring: must be set to true for initial recurring transaction if amount will not be fixed for subsequent transactions

##### Version 1.3.7 (2022-11-21)

	Added support for external 3-D Secure
	Added externalMpiData to PaymentMethod (when 3-D Secure was completed before the Gateway call)
	Bump gson from 2.8.5 to 2.8.9
	Bumps [gson](https://github.com/google/gson) from 2.8.5 to 2.8.9.
	- [Release notes](https://github.com/google/gson/releases)
	- [Changelog](https://github.com/google/gson/blob/master/CHANGELOG.md)
	- [Commits](https://github.com/google/gson/compare/gson-parent-2.8.5...gson-parent-2.8.9)
	---
	updated-dependencies:
	- dependency-name: com.google.code.gson:gson
	  dependency-type: direct:production
	...
	Signed-off-by: dependabot[bot] <support@github.com>

##### Version 1.3.6 (2021-09-21)

	Added card type and card mask fields to parsed status response.

##### Version 1.3.5 (2021-09-06)

	Added fields for recurring payments: recurring-frequency and recurring-expiry.

##### Version 1.3.4 (2021-06-16)

	Bumps [hibernate-validator](https://github.com/hibernate/hibernate-validator) from 6.1.0.Final to 6.1.5.Final.
	- [Release notes](https://github.com/hibernate/hibernate-validator/releases)
	- [Changelog](https://github.com/hibernate/hibernate-validator/blob/6.1.5.Final/changelog.txt)
	- [Commits](https://github.com/hibernate/hibernate-validator/compare/6.1.0.Final...6.1.5.Final)
	
	Bumps httpclient from 4.5.3 to 4.5.13.

##### Version 1.3.3 (2021-06-04)

	Added error codes for soft declines

##### Version 1.3.2 (2020-09-02)

	Add merchant-transaction-id to payment response parsing
	Affected:
	 - payment response
	 - result response
	 - callback parsing

##### Version 1.3.1 (2020-08-05)

	Add parameters describing cardholder device

##### Version 1.3.0 (2020-07-03)

	Improve authorization to use digest instead of API key.
	Verify non-failed responses for valid digest.
	Add possibility to validate callback data.
	Implement /report method.
	Implement response parsing.
