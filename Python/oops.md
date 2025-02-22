### 1. Introduction to Object-Oriented Programming (OOP)
- OOP is a programming paradigm that organizes code into "objects" that represent real-world entities.
- Key Concepts: Classes, Objects, Inheritance, Polymorphism, Encapsulation, and Abstraction.
- Why OOP?: Promotes code reusability, modularity, and scalability


### 2. Classes
- A blueprint or template for creating objects.
- Key Components:
    Attributes: Variables that belong to the class (e.g., name, age).
    Methods: Functions that belong to the class (e.g., greet(), calculate()).
    Constructor (__init__): A special method called when an object is created. Used to initialize attributes.

- Example:
    class Dog:
        # Constructor
        def __init__(self, name, age):
            self.name = name  # Attribute
            self.age = age    # Attribute

        # Method
        def bark(self):
            return f"{self.name} says woof!"

### 3. Objects

- An instance of a class. Objects are created using the class blueprint.
- Creating Objects: Use the class name followed by parentheses.
- Accessing Attributes and Methods: Use dot notation e.g., object.attribute, object.method()
- Example:

    # Create an object
    my_dog = Dog("Buddy", 5)

    # Access attributes
    print(my_dog.name)  # Output: Buddy

    # Call methods
    print(my_dog.bark())  # Output: Buddy says woof!


 ### 4.Inheritance
- A mechanism where a new class (child) derives properties and behaviors from an existing class (parent).
- Purpose: Promotes code reuse and establishes a relationship between classes.
- Overriding Methods: Child classes can redefine methods inherited from the parent class.

Example:

# Parent class
class Animal:
    def __init__(self, name):
        self.name = name

    def speak(self):
        return "Some generic sound"

# Child class inheriting from Animal
class Cat(Animal):
    # Overriding the speak method
    def speak(self):
        return f"{self.name} says meow!"

# Create an object
my_cat = Cat("Whiskers")
print(my_cat.speak())  # Output: Whiskers says meow!

### 5. Polymorphism
-  The ability of different classes to be treated as instances of the same class through a shared interface.

Types:
Method Overriding: A child class provides a specific implementation of a method already defined in the parent class (as shown in inheritance).

Method Overloading: Python does not support method overloading directly (unlike Java/C++), but it can be simulated using default arguments or variable-length arguments.

- Example:

# Polymorphism with method overriding
class Dog(Animal):
    def speak(self):
        return f"{self.name} says woof!"

class Bird(Animal):
    def speak(self):
        return f"{self.name} says chirp!"

# Function demonstrating polymorphism
def animal_sound(animal):
    print(animal.speak())

# Create objects
my_dog = Dog("Buddy")
my_bird = Bird("Tweety")

# Polymorphic behavior
animal_sound(my_dog)   # Output: Buddy says woof!
animal_sound(my_bird)  # Output: Tweety says chirp!


### Key Takeaways
Classes: Define the structure and behavior of objects.

Objects: Instances of classes that hold data and perform actions.

Inheritance: Allows classes to inherit attributes and methods from other classes.

Polymorphism: Enables objects of different classes to be treated as objects of a common superclass.
