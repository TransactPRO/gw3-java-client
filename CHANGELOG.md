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
