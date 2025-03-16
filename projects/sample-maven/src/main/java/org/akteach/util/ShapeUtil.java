package org.akteach.util;

public class ShapeUtil {

    // Calculate the area of a circle
    public static double circleArea(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        return Math.PI * radius * radius;
    }

    // Calculate the area of a rectangle
    public static double rectangleArea(double length, double width) {
        if (length <= 0 || width <= 0) {
            throw new IllegalArgumentException("Length and width must be positive");
        }
        return length * width;
    }

    // Calculate the area of a triangle
    public static double triangleArea(double base, double height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Base and height must be positive");
        }
        return 0.5 * base * height;
    }
}