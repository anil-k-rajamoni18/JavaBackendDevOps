## 1. Lists
- Lists are ordered, mutable collections of items.
- Creating Lists:
    fruits = ["apple", "banana", "cherry"]
    numbers = [1, 2, 3, 4, 5]
    mixed_list = [1, "apple", True, 3.14]

- Indexing: Access elements using their position (starts at 0).
    print(fruits[0])  # Output: "apple"
    print(fruits[-1]) # Output: "cherry" (negative indexing)

- Slicing: Extract a portion of the list.
    print(numbers[1:4])  # Output: [2, 3, 4] (excludes index 4)
    print(numbers[:3])   # Output: [1, 2, 3] (from start to index 2)
    print(numbers[2:])   # Output: [3, 4, 5] (from index 2 to end)

- Manipulating Lists:
    Additing:
        fruits.append("orange")  # Adds to the end
        fruits.insert(1, "mango")  # Inserts at index 1

    Remove items:
        fruits.remove("banana")  # Removes "banana"
        fruits.pop(2)  # Removes item at index 2
    
    Modify items:
        fruits[0] = "kiwi"  # Changes "apple" to "kiwi"

    Common Methods:
        len(fruits): Returns the length of the list.
        fruits.sort(): Sorts the list.
        fruits.reverse(): Reverses the list.


### 2. Tuples
- Tuples are ordered, immutable collections of items.
- Creating Tuples:
    
    coordinates = (10, 20)
    colors = ("red", "green", "blue")
    single_item_tuple = (5,)  # Note the comma

- Indexing: Similar to lists.
    
    print(colors[1])  # Output: "green"
    print(colors[-1]) # Output: "blue"

- Slicing: Similar to lists.
    print(colors[1:3])  # Output: ("green", "blue")

- Immutability: Cannot change elements after creation.
    colors[1] = "yellow"  # This will raise an error

- Common Methods:
    len(colors): Returns the length of the tuple.
    colors.count("red"): Counts occurrences of "red".
    colors.index("green"): Returns the index of "green".


### 3. Dictionaries
- Dictionaries store key-value pairs. Keys must be unique and immutable.
- Creating Dictionaries:
    student = {"name": "Alice", "age": 25, "is_student": True}

- Accessing Values: Use keys to retrieve values.
    print(student["name"])  # Output: "Alice"
    print(student.get("age"))  # Output: 25

- Adding/Modifying Items:
    student["grade"] = "A"  # Adds a new key-value pair
    student["age"] = 26  # Updates the value of "age"

- Removing Items:
    student.pop("is_student")  # Removes the key-value pair
    del student["age"]  # Deletes the key-value pair

- Common Methods:
    student.keys(): Returns all keys.
    student.values(): Returns all values.
    student.items(): Returns key-value pairs as tuples.
    len(student): Returns the number of key-value pairs.


### 4. Sets
- Sets are unordered collections of unique elements.
- Creating Sets:
    fruits = {"apple", "banana", "cherry"}
    numbers = {1, 2, 3, 4, 5}

- Adding Items:
    fruits.add("orange")  # Adds "orange" to the set

- Removing Items:
    fruits.remove("banana")  # Removes "banana" (raises error if not found)
    fruits.discard("banana")  # Removes "banana" (no error if not found)

- Set Operations:
    Union: Combines two sets.
        set1 = {1, 2, 3}
        set2 = {3, 4, 5}
        print(set1.union(set2))  # Output: {1, 2, 3, 4, 5}

    Intersection: Finds common elements.
        print(set1.intersection(set2))  # Output: {3}

    Difference: Finds elements in one set but not the other.
        print(set1.difference(set2))  # Output: {1, 2}

    Symmetric Difference: Finds elements in either set but not both.
        print(set1.symmetric_difference(set2))  # Output: {1, 2, 4, 5}

- Common Methods:
    len(fruits): Returns the number of elements.
    fruits.clear(): Removes all elements.



### Practice Exercises
Lists:
Create a list of your favorite movies. Add a new movie, remove one, and print the list.

Tuples:
Create a tuple of coordinates. Try to modify it (observe the error).

Dictionaries:
Create a dictionary for a person (name, age, city). Update the city and print all keys.

Sets:
Create two sets of numbers. Find their union, intersection, and difference.
