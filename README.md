# ATM Simulation – Java example

This project is a simple **ATM (Automated Teller Machine) simulator** implemented in **Java**.

---

## Features

* Create an account with an initial balance
* Deposit funds into an account
* Withdraw funds with checks for:

  * Sufficient account balance
  * Availability of required banknotes in the ATM
---

## Project Structure

* `Account` → represents a bank account with balance
* `Transaction` → represents a deposit or withdrawal operation
* `CashDispenser` → manages ATM cash inventory and the dispensing algorithm
* `AtmService` → core service layer that handles account operations
* `WithdrawalResult` → result object for withdrawals (success/failure, dispensed bills, remaining balance)

---

## Requirements

* **Java 17** or newer
* `javac` / `java`

---

## Running the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/atm-simulation.git
   cd atm-simulation
   ```
---

## Example Usage

### Create an Account
in the BankService class

```java
        this.accountService.createAccount("1001", "1234", new BigDecimal("1000"));
        this.accountService.createAccount("1002", "0000", new BigDecimal("2000"));
```

### Withdraw

```java
WithdrawalResult result = atm.withdraw("123", new BigDecimal("850"));
System.out.println(result);
```

### Sample Output

```
Withdrawal successful!
Dispensed: {500, 200, 100, 50}
Remaining balance: 350
```

---

## Roadmap / Future Improvements
* Add transaction history
* Add pin hashing
* Add PIN-based authentication
* Provide REST API (Spring Boot) for HTTP access

---

## License

This project is released under the MIT License.

---
