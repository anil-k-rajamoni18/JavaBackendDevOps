### Introduction to Object-Oriented Programming (OOP)
- OOP is a programming paradigm that organizes code into "objects" that represent real-world entities.
- Key Concepts: Classes, Objects, Inheritance, Polymorphism, Encapsulation, and Abstraction.
- Why OOP?: Promotes code reusability, modularity, and scalability

## 1. Classes
- A class is a blueprint for creating objects. 
- It defines the structure and behavior of objects.
- Class Declarations
    class ClassName {
        // Fields (variables)
        // Constructors
        // Methods
    }
Example:

class Car {
    // Fields
    String brand;
    int year;

    // Constructor
    Car(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    // Method
    void displayInfo() {
        System.out.println("Brand: " + brand + ", Year: " + year);
    }
}


- Constructors
    Special methods used to initialize objects.
    Have the same name as the class.
    No return type (not even void).
    Example: Car myCar = new Car("Toyota", 2020);

- Fields
    Variables declared inside a class.
    Represent the state of an object.
    Example: String brand; and int year; in the Car class.

- Methods
    Functions defined inside a class.
    Represent the behavior of an object.
    Example: void displayInfo() in the Car class.

## 2. Objects
- Objects are instances of classes. 
- They represent real-world entities.
- Use the new keyword to create an object.
- Example: Car myCar = new Car("Toyota", 2020);

- Object References
    Variables of a class type hold references to objects.
    Example:    
        Car car1 = new Car("Toyota", 2020);
        Car car2 = car1; // car2 references the same object as car1
        
- Object Equality
    Use == to compare object references.
    Use .equals() to compare object content (if overridden).

    Car car1 = new Car("Toyota", 2020);
    Car car2 = new Car("Toyota", 2020);
    System.out.println(car1 == car2); // false (different references)
    System.out.println(car1.equals(car2)); // true (if .equals() is overridden)

## 3. Inheritance
- Inheritance allows a class to inherit fields and methods from another class.
- Use the extends keyword to create a subclass.
- Example:
    class Vehicle {
        String type;
        void start() {
            System.out.println("Vehicle started.");
        }
    }

    class Car extends Vehicle {
        String brand;
        void displayBrand() {
            System.out.println("Brand: " + brand);
        }
    }

- Overriding Methods
    A subclass can override a method from its superclass.
    Use the @Override annotation for clarity.

    class Car extends Vehicle {
        @Override
        void start() {
            System.out.println("Car started.");
        }
    }

- Hiding Fields
    Fields in a subclass can hide fields in the superclass.
    Use super to access hidden fields.

    class Vehicle {
        String type = "Vehicle";
    }

    class Car extends Vehicle {
        String type = "Car";
        void displayType() {
            System.out.println(super.type); // "Vehicle"
            System.out.println(this.type);  // "Car"
        }
    }

## 4. Polymorphism
- Polymorphism allows objects to take on multiple forms.
=> Method Overriding
    A subclass provides a specific implementation of a method defined in its superclass.

    class Animal {
        void sound() {
            System.out.println("Animal makes a sound.");
        }
    }

    class Dog extends Animal {
        @Override
        void sound() {
            System.out.println("Dog barks.");
        }
    }

=> Method Overloading
    Multiple methods with the same name but different parameters.

    class MathUtils {
        int add(int a, int b) {
            return a + b;
        }
        double add(double a, double b) {
            return a + b;
        }
    }

=> Operator Overloading
    Java does not support operator overloading (except for + with strings).


## 5. Encapsulation
- Encapsulation is the concept of bundling data and methods that operate on the data within a single unit (class).
- Data Hiding
    Restrict direct access to fields using access modifiers.
- Example:
    class Person {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

- Access Modifiers
    public: Accessible from anywhere.
    private: Accessible only within the class.
    protected: Accessible within the package and subclasses.
    default (no modifier): Accessible within the package.

## 6. Abstraction
- Abstraction is the concept of hiding implementation details and showing only the necessary features.
- Abstract Classes Cannot be instantiated.
- Can contain abstract methods (without implementation) and non-abstract methods.
- Example:
    abstract class Animal {
        void name() {
            System.out.println("animal");
        }
        abstract void sound();
    }

    class Dog extends Animal {
        @Override
        void sound() {
            System.out.println("Dog barks.");
        }
    }

=> Interfaces
- Define a contract for classes to implement.
- All methods are abstract by default (Java 8+ allows default methods).
- Example:
    interface Drivable {
        void drive();
    }

    class Car implements Drivable {
        @Override
        public void drive() {
            System.out.println("Car is driving.");
        }
    }

- Abstract Methods
    Methods without a body.
    Must be overridden by subclasses.

    abstract class Shape {
        abstract void draw();
    }


### Summary
    Classes: Blueprint for objects.

    Objects: Instances of classes.

    Inheritance: Reuse code by creating subclasses.

    Polymorphism: Methods and objects can take multiple forms.

    Encapsulation: Hide data and expose functionality.

    Abstraction: Simplify complex systems by hiding details.
