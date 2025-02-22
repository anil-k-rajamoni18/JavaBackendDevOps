### 1. Arrays
- Arrays are fixed-size data structures that store elements of the same type.
- One-Dimensional Arrays
    syntax: dataType[] arrayName = new dataType[size];
    example:
        int[] numbers = new int[5]; // Array of 5 integers
        numbers[0] = 10; // Assign value to the first element


=> Multi-Dimensional Arrays
    Arrays of arrays (e.g., 2D arrays).
    Syntax: dataType[][] arrayName = new dataType[rows][columns];
    Example:
        int[][] matrix = new int[3][3]; // 3x3 matrix
        matrix[0][0] = 1; // Assign value to the first element

=> Array Operations
    Accessing Elements: Use the index (e.g., numbers[0]).
    Iterating:
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }

    Sorting:
        Arrays.sort(numbers);

    Searching:
        int index = Arrays.binarySearch(numbers, 10);


## 2. Collections
- The Java Collections Framework provides interfaces and classes to store and manipulate groups of objects.

- Collection Interfaces
    List: Ordered collection that allows duplicates.
    Set: Unordered collection that does not allow duplicates.
    Map: Collection of key-value pairs (not part of the Collection interface).

=> List Implementations:
a) ArrayList: Resizable array implementation.
    List<String> list = new ArrayList<>();
    list.add("Java");
    list.add("Python");

b) LinkedList: Doubly-linked list implementation.
    List<String> list = new LinkedList<>();
    list.add("Java");
    list.add("Python");

=> Set Implementations:
a) HashSet: Unordered, uses hashing.

    Set<String> set = new HashSet<>();
    set.add("Java");
    set.add("Python");

b) TreeSet: Sorted, uses a Red-Black tree.

    Set<String> set = new TreeSet<>();
    set.add("Java");
    set.add("Python");

=> Map Implementations:
a) HashMap: Unordered, uses hashing.
    Map<String, Integer> map = new HashMap<>();
    map.put("Java", 1);
    map.put("Python", 2);

b) TreeMap: Sorted, uses a Red-Black tree.
    Map<String, Integer> map = new TreeMap<>();
    map.put("Java", 1);
    map.put("Python", 2);

=> Common Operations
Adding Elements:
    list.add("C++");
    set.add("C++");
    map.put("C++", 3);

Removing Elements:
    list.remove("C++");
    set.remove("C++");
    map.remove("C++");

Iterating:
    for (String item : list) {
        System.out.println(item);
    }

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
        System.out.println(entry.getKey() + ": " + entry.getValue());
    }

## 3. Generics
- Generics allow types (classes and interfaces) to be parameters when defining classes, interfaces, and methods.
- Generic Classes
    Define a class with a type parameter.

    class Box<T> {
        private T item;

        public void setItem(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }
    }


    Box<String> stringBox = new Box<>();
    stringBox.setItem("Hello");

    Box<Float> floatBox = new Box<>() 
    floatBox.setItem(102.2f);


- Generic Methods
    Define a method with a type parameter.
        public <T> void printArray(T[] array) {
            for (T item : array) {
                System.out.println(item);
            }
        }

        Integer[] intArray = {1, 2, 3};
        printArray(intArray);

- Type Parameters
    Use single uppercase letters for type parameters (e.g., T, E, K, V).

    class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    Pair<String, Integer> pair = new Pair<>("Age", 25);

- Bounded Type Parameters
    Restrict types that can be used as type arguments.

    class Box<T extends Number> {
        private T item;

        public void setItem(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }
    }

    Box<Integer> intBox = new Box<>();
    intBox.setItem(10);

## Summary
    Arrays: Fixed-size, store elements of the same type.
    Collections: Dynamic data structures like List, Set, and Map.
    Generics: Enable type-safe and reusable code by parameterizing types.
