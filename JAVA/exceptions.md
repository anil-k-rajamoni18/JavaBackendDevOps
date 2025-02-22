### Exception Handling 
-  To handle exceptions gracefully and prevent program crashes.
- Components:

Try Block:
    Contains the code that might throw an exception.
    If an exception occurs, the control is transferred to the corresponding catch block.

Catch Block:
    Catches and handles the exception thrown by the try block.
    Can have multiple catch blocks to handle different types of exceptions.

Finally Block:
    Executes regardless of whether an exception is thrown or not.
    Typically used for cleanup activities (e.g., closing files, releasing resources).

Syntax:
    try {
        // Code that might throw an exception
    } catch (ExceptionType1 e1) {
        // Handle ExceptionType1
    } catch (ExceptionType2 e2) {
        // Handle ExceptionType2
    } finally {
        // Code that always executes
    }

Example:

    try {
        int result = 10 / 2; // ArithmeticException
        System.out.println(result);
    } catch (ArithmeticException e) {
        System.out.println("Cannot divide by zero!");
    } finally {
        System.out.println("Execution complete.");
    }

### 2.Exception Types
=> Checked Exceptions:
    Checked at compile-time.
    Must be either caught or declared to be thrown (using throws).
    Examples: IOException, SQLException.

=> Unchecked Exceptions:
    Not checked at compile-time.
    Occur due to logical errors in the code.
    Examples: NullPointerException, ArithmeticException.

=> Error Class:
    Represents serious issues that are not meant to be handled by the application.
    Examples: OutOfMemoryError, StackOverflowError.

=> Hierarchy:
    Throwable
        ├── Error
        └── Exception
            ├── RuntimeException (Unchecked)
            └── Other Exceptions (Checked)
            
### 3. Throwing Exceptions
    Throw Statement:
    Used to explicitly throw an exception.
    Typically used for custom exceptions or to rethrow exceptions.
    Syntax: throw new ExceptionType("Error Message");
    Example: 
    if (age < 18) {
        throw new ArithmeticException("Age must be 18 or older.");
    }

### 4. Throws Clause:
- Used in method signatures to declare that a method might throw an exception.
- Caller of the method must handle or declare the exception.
- Syntax:
    returnType methodName() throws ExceptionType1, ExceptionType2 {
        // Method body
    }
- Example:
    public void readFile() throws IOException {
        // Code that might throw IOException
    }

###  Custom Exceptions
- To create application-specific exceptions for better error handling.

Steps:
    Create a class that extends Exception (for checked) or RuntimeException (for unchecked).
    Provide constructors to initialize the exception (e.g., with a custom message).

Example:

    // Custom Checked Exception
    class InsufficientFundsException extends Exception {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }

    // Custom Unchecked Exception
    class InvalidAgeException extends RuntimeException {
        public InvalidAgeException(String message) {
            super(message);
        }
    }

Usage:

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Not enough funds!");
        }
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new InvalidAgeException("Age cannot be negative!");
        }
    }


### Key Takeaways:
    Use try-catch-finally blocks to handle exceptions gracefully.
    Understand the difference between checked and unchecked exceptions.
    Use throw to explicitly throw exceptions and throws to declare them.
    Create custom exceptions to handle application-specific errors.
