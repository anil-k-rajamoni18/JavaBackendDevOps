## 1. Lambda Expressions
- To provide a clear and concise way to represent functional interfaces (interfaces with a single abstract method).
- Syntax:
    (parameters) -> expression
    (parameters) -> { statements; }

Example:

// Without Lambda
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Hello World");
    }
};

// With Lambda
Runnable r = () -> System.out.println("Hello World");

Key Points:
    - Lambda expressions enable functional programming in Java.
    - They can be used wherever a functional interface is expected (e.g., Runnable, Comparator).

## 2. Functional Interfaces
- To provide a target type for lambda expressions and method references.
- An interface with exactly one abstract method.
- Examples:
    Runnable → void run()
    Comparator → int compare(T o1, T o2)
    Consumer → void accept(T t)
- Built-in Functional Interfaces:
    Predicate<T>: Takes an input and returns a boolean.
        boolean test(T t)
    Function<T, R>: Takes an input and returns an output.
        R apply(T t)
    Consumer<T>: Takes an input and performs an operation.
        void accept(T t)
    Supplier<T>: Supplies a result.
        T get()


- Example:
    Predicate<Integer> isEven = n -> n % 2 == 0;
    System.out.println(isEven.test(4)); // true

## 3. Stream API
- To process collections of objects in a functional style.
Key Concepts:
    Stream: A sequence of elements supporting sequential and parallel operations.
    Intermediate Operations: Operations that return a stream (e.g., filter, map, sorted).
    Terminal Operations: Operations that produce a result or side-effect (e.g., collect, forEach, reduce).

- Example:
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
    List<String> filteredNames = names.stream()
                                    .filter(name -> name.startsWith("A"))
                                    .collect(Collectors.toList());
    System.out.println(filteredNames); // [Alice]

Key Points:
    Streams are lazy; computation happens only when a terminal operation is invoked.
    Streams can be processed in parallel using parallelStream().

## 4. Method References
- To simplify lambda expressions by referring to existing methods.
Types:
    Static Method Reference: ClassName::staticMethod
    Instance Method Reference: instance::method
    Constructor Reference: ClassName::new

Example:
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
    names.forEach(System.out::println); // Method reference

## 5. Optional Class
- To avoid null checks and NullPointerException.

=> Key Methods:
    ofNullable(T value): Wraps a value that may be null.
    isPresent(): Checks if a value is present.
    orElse(T other): Returns the value if present, otherwise returns other.


- Example:
    Optional<String> name = Optional.ofNullable(getName());
    System.out.println(name.orElse("Default Name"));


### 6. Date and Time API (java.time)
- To provide a more robust and flexible date and time API.

Key Classes:
LocalDate: Represents a date (year, month, day).
LocalTime: Represents a time (hour, minute, second).
LocalDateTime: Combines date and time.
ZonedDateTime: Represents a date-time with a time zone.

Example:
    LocalDate today = LocalDate.now();
    LocalDate tomorrow = today.plusDays(1);
    System.out.println("Tomorrow's date: " + tomorrow);
