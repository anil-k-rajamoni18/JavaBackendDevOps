### Variables
- Variables are containers for storing data values. They are created using assignment.
- Variables store data values.
- Use = for assignment.
Data types:
    Strings: name = "Alice"
    Numbers: age = 25 (int), height = 5.9 (float)
    Booleans: is_student = True
    Complex: x = 2 + 3j
    None: id = None 
    
- Use descriptive names and follow naming conventions (e.g., snake_case).

### Operators
- Operators are used to perform operations on variables and values.
Arithmetic: +, -, *, /, %, **, //
    Example: 
    a = 10
    b = 3
    print(a + b)  # Addition
    print(a - b)  # Subtraction
    print(a * b)  # Multiplication
    print(a / b)  # Division
    print(a % b)  # Modulus (remainder)
    print(a ** b) # Exponentiation
    print(a // b) # Floor division

Comparison: >, <, ==, !=, >=, <=
    Example: 
    print(a > b)  # Greater than
    print(a < b)  # Less than
    print(a == b) # Equal to
    print(a != b) # Not equal to
    print(a >= b) # Greater than or equal to
    print(a <= b) # Less than or equal to

Logical: and, or, not
    Example: 
        print(a > 5 and b < 10)  # AND
        print(a > 5 or b > 10)   # OR
        print(not(a > 5))        # NOT

Assignment: =, +=, -=, *=
    Example: 
        x = 5
        x += 3  # Equivalent to x = x + 3
        x -= 2  # Equivalent to x = x - 2


### Control Structures
- If-Else Statements: Execute code based on conditions
    age = 18
    if age >= 18:
        print("You are an adult.")
    else:
        print("You are a minor.")

- For Loops: Iterate over a sequence (e.g., list, string, range).
    
    for i in range(5):  # Prints 0 to 4
        print(i)     

- While Loops: Execute code as long as a condition is true.
    
    count = 0
    while count < 5:
        print(count)
        count += 1

- Avoid infinite loops in while loops.
- Use break and continue to control loop flow.

### Functions
- Functions are reusable blocks of code that perform a specific task.
- Use the def keyword to define functions. 

    def greet(name):  
    print(f"Hello, {name}")  

- Call with ():
    greet("Ram")  

- Use arguments and return values:
    
    def add(a, b):  
        return a + b  
    result = add(3, 5)  


### Modules
- Modules are files containing Python code (functions, variables, classes) that can be reused.
1. Importing Modules: Use the import statement.
    
    import math
    print(math.sqrt(16))  # Output: 4.0

2. Creating Your Own Modules:
    Save your code in a .py file (e.g., mymodule.py).
    Import it in another script.


    # mymodule.py
    def greet(name):
        print(f"Hello, {name}!")

    # main.py
    import mymodule
    mymodule.greet("Alice")

3. Importing Specific Functions:
    from math import sqrt
    print(sqrt(25))  # Output: 5.0


- Organize related functions into modules.
- Use if __name__ == "__main__": to run code only when the module is executed directly.
