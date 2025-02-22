### 1. Variables
Variables are used to store data in a program. They have a type, a name, and a value.

- Primitive Types: These are basic data types that store simple values.
    int: Stores integers (e.g., int age = 25;)
    double: Stores floating-point numbers (e.g., double price = 19.99;)
    boolean: Stores true or false (e.g., boolean isRaining = false;)
    char: Stores a single character (e.g., char grade = 'A';)

- Reference Types: These store references to objects or arrays.
    Arrays: A collection of elements of the same type (e.g., int[] numbers = {1, 2, 3};)
    Objects: Instances of classes (e.g., String name = new String("Alice");)


- Key Points
    Primitive types are stored in the stack memory.
    Reference types are stored in the heap memory, and the variable holds a reference to the memory location.

### 2. Operators
- Operators are used to perform operations on variables and values.
- Arithmetic Operators
    + (Addition), - (Subtraction), * (Multiplication), / (Division), % (Modulus)

    Example: int result = 10 + 5; // result = 15

- Comparison Operators
    == (Equal to), != (Not equal to), > (Greater than), < (Less than), >= (Greater than or equal to), <= (Less than or equal to)

    Example: boolean isEqual = (10 == 5); // isEqual = false

- Logical Operators
    && (Logical AND), || (Logical OR), ! (Logical NOT)

    Example: boolean result = (true && false); // result = false

- Assignment Operators
    = (Assign), += (Add and assign), -= (Subtract and assign), *= (Multiply and assign), /= (Divide and assign)

    Example: int x = 10; x += 5; // x = 15

- Other Operators
    Ternary Operator: condition ? expression1 : expression2

    Example: String result = (10 > 5) ? "Yes" : "No"; // result = "Yes"

### 3. Control Structures
- Control structures are used to control the flow of execution in a program.

- If-Else Statements
    Used for decision-making.

    int age = 18;
    if (age >= 18) {
        System.out.println("You are an adult.");
    } else {
        System.out.println("You are a minor.");
    }

- For Loops
    Used to iterate a block of code a specific number of times.


    for (int i = 0; i < 5; i++) {
        System.out.println("Iteration: " + i);
    }

- While Loops
    - Used to repeatedly execute a block of code as long as a condition is true.

    int i = 0;
    while (i < 5) {
        System.out.println("Iteration: " + i);
        i++;
    }

- Switch Statements
    Used to select one of many code blocks to execute

    int day = 1;
    switch (day) {
        case 1:
            System.out.println("Monday");
        case 2:
            System.out.println("Tuesday");
            break;
        default:
            System.out.println("Other day");
    }


### 4. Methods
Methods are blocks of code that perform a specific task. 
They help in reusing code and organizing programs.

- Method Declarations
    returnType methodName(parameter1, parameter2, ...) {
        // Method body
        return value; // Optional
    }

    Example:
    int add(int a, int b) {
        return a + b;
    }

- Method Calls
    Methods are called using their name and passing arguments.
    Example:
        int result = add(5, 10); // result = 15

- Parameter Passing
    Java uses pass-by-value for primitive types (a copy of the value is passed).
    For reference types, the reference (memory address) is passed by value.

- Return Types
    Methods can return a value using the return statement.
    If a method does not return a value, its return type is void

    void greet(String name) {
        System.out.println("Hello, " + name);
    }

- Key Points
    Method overloading: Multiple methods with the same name but different parameters.
    Example:
        int add(int a, int b) { return a + b; }
        double add(double a, double b) { return a + b; }
