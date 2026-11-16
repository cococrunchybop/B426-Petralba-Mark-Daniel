# Activity: Banking System

## Objective

Model simple banking transactions using all the OOP concepts you have learned: encapsulation, inheritance, polymorphism (overriding and overloading), and abstraction (abstract classes and interfaces).

## Instructions

Create all classes and interfaces in the same package. Hardcode your objects in `Main`. Do not use arrays or menus.

### Part 1: Create the abstract class `Account`

1. Create an abstract class named `Account` with these **private** data members:
   - `String accountNumber`
   - `String accountHolder`
   - `double balance`

2. Add a parameterized constructor for all three data members. Use `this`.

3. Add these getters and setters:
   - `getAccountNumber()` (no setter)
   - `getAccountHolder()` and `setAccountHolder()`
   - `getBalance()`
   - `protected void setBalance(double balance)`

   Do NOT create a `public` setter for `balance`. The balance must only change through deposits and withdrawals.

   Note: `protected` means that only the class itself, its subclasses, and classes in the same package can use the member.

4. Override `toString()` so it returns the account number, account holder, and balance, one per line.

5. Add this abstract method:

   ```java
   public abstract void displayAccountInfo();
   ```

### Part 2: Create the interfaces

6. Create an interface named `Transaction` with these methods:

   ```java
   void deposit(double amount);
   boolean withdraw(double amount);
   ```

7. Create an interface named `Transferable` with this method:

   ```java
   boolean transfer(Transaction target, double amount);
   ```

8. Create an interface named `InterestBearing` with this constant and method:

   ```java
   double INTEREST_RATE = 2.5;
   void addInterest();
   ```

9. Create an interface named `Overdraftable` with this constant and method:

   ```java
   double OVERDRAFT_LIMIT = 2000.0;
   boolean isWithinOverdraft(double amount);
   ```

### Part 3: Create the `SavingsAccount` class

10. `SavingsAccount` must extend `Account` and implement `Transaction`, `Transferable`, and `InterestBearing`.

11. Add this constant:

    ```java
    public static final double MIN_BALANCE = 500.0;
    ```

12. Create a constructor that uses `super(...)`.

13. Implement `displayAccountInfo()`. Print the account details using `toString()`, then print:
    - The interest rate (use the constant `INTEREST_RATE`)
    - The minimum balance (use the constant `MIN_BALANCE`)

14. Implement `deposit(double amount)`:
    - If the amount is zero or negative, print `"Invalid deposit amount."` and stop.
    - Otherwise, update the balance and print `"Deposited <amount>. New balance: <balance>"`.

15. Implement `withdraw(double amount)`:
    - If the amount is zero or negative, print `"Invalid withdrawal amount."` and return `false`.
    - If the balance would go below `MIN_BALANCE`, print `"Withdrawal failed: balance cannot go below <MIN_BALANCE>"` and return `false`.
    - Otherwise, update the balance, print `"Withdrew <amount>. New balance: <balance>"`, and return `true`.

16. Implement `transfer(Transaction target, double amount)`:
    - Print `"Transferring <amount>..."`.
    - Call `withdraw(amount)`. If it succeeds, call `target.deposit(amount)`, print `"Transfer successful."`, and return `true`.
    - Otherwise, print `"Transfer failed."` and return `false`.

17. Implement `addInterest()`:
    - Compute the interest using the balance and `INTEREST_RATE` (a rate of 2.5 means 2.5%).
    - Add it to the balance and print `"Interest added: <interest>. New balance: <balance>"`.

### Part 4: Create the `CheckingAccount` class

18. `CheckingAccount` must extend `Account` and implement `Transaction`, `Transferable`, and `Overdraftable`.

19. Create a constructor that uses `super(...)`.

20. Implement `displayAccountInfo()`. Print the account details using `toString()`, then print the overdraft limit (use the constant `OVERDRAFT_LIMIT`).

21. Implement `isWithinOverdraft(double amount)`. It returns `true` if the balance after the withdrawal would not go lower than negative `OVERDRAFT_LIMIT`.

22. Implement `deposit(double amount)` with the same rules as in `SavingsAccount`.

23. Implement `withdraw(double amount)`:
    - If the amount is zero or negative, print `"Invalid withdrawal amount."` and return `false`.
    - Use `isWithinOverdraft(amount)`. If it returns `false`, print `"Withdrawal failed: exceeds the overdraft limit of <OVERDRAFT_LIMIT>"` and return `false`.
    - Otherwise, update the balance, print `"Withdrew <amount>. New balance: <balance>"`, and return `true`.

24. Implement `transfer(Transaction target, double amount)` with the same rules as in `SavingsAccount`.

### Part 5: Method overloading

25. In both `SavingsAccount` and `CheckingAccount`, add these overloaded methods:

    ```java
    public void deposit(double amount, String remarks)
    public boolean withdraw(double amount, String remarks)
    ```

26. Each overloaded method must call the one-parameter version first (do not repeat the validation), then print `"Remarks: <remarks>"`. The overloaded `withdraw` must return the result of the one-parameter version.

### Part 6: Create the `Main` class

27. Create these objects:
    - `SavingsAccount`: account number `"1001"`, holder `"Juan Dela Cruz"`, balance `5000.0`
    - `CheckingAccount`: account number `"2001"`, holder `"Maria Santos"`, balance `1000.0`

28. Create a method with this signature:

    ```java
    public static void testAccount(Account account)
    ```

    It must call `displayAccountInfo()`.

29. Call `testAccount()` for both accounts.

30. For the savings account, call:
    - `deposit(1000)`
    - `withdraw(5800)`
    - `deposit(500, "Birthday gift")`
    - `withdraw(1500, "Rent")`

31. For the checking account, call:
    - `withdraw(2500)`
    - `withdraw(1000)`

32. Transfer `1000` from the savings account to the checking account.

33. Call `addInterest()` on the savings account.

### Part 7: Label the OOP concepts

34. At the bottom of `Main.java`, write a comment block that labels where each concept is used. Write one line per concept, with the class and method names:
    - Encapsulation
    - Inheritance
    - Polymorphism (overriding)
    - Polymorphism (overloading)
    - Abstraction (abstract class)
    - Abstraction (interfaces)
    - Constants

## Sample Output

```
=== Savings Account ===
Account Number: 1001
Account Holder: Juan Dela Cruz
Balance: 5000.0
Interest Rate: 2.5%
Minimum Balance: 500.0

=== Checking Account ===
Account Number: 2001
Account Holder: Maria Santos
Balance: 1000.0
Overdraft Limit: 2000.0

=== Savings Transactions ===
Deposited 1000.0. New balance: 6000.0
Withdrawal failed: balance cannot go below 500.0
Deposited 500.0. New balance: 6500.0
Remarks: Birthday gift
Withdrew 1500.0. New balance: 5000.0
Remarks: Rent

=== Checking Transactions ===
Withdrew 2500.0. New balance: -1500.0
Withdrawal failed: exceeds the overdraft limit of 2000.0

=== Transfer (Savings to Checking) ===
Transferring 1000.0...
Withdrew 1000.0. New balance: 4000.0
Deposited 1000.0. New balance: -500.0
Transfer successful.

=== Interest ===
Interest added: 100.0. New balance: 4100.0
```

## Stretch Goal (Optional)

1. Overload the constructors:
   - `SavingsAccount(String accountNumber, String accountHolder, double balance)` should use a default interest rate. Use `this(...)` to call the other constructor.
   - `CheckingAccount(String accountNumber, String accountHolder, double balance)` should do the same for the overdraft limit.
2. Overload `transfer()` with a version that has a `String remarks` parameter.
