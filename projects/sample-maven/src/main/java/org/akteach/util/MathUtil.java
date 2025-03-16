package org.akteach.util;

public class MathUtil {

    // Check if a number is even
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    // Calculate factorial of a number
    public static int factorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number cannot be negative");
        }
        if (number == 0 || number == 1) {
            return 1;
        }
        return number * factorial(number - 1);
    }
}