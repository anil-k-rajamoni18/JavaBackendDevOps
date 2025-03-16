package utils;

import org.akteach.util.ShapeUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShapeUtilTest {

    @Test
    void testCircleArea() {
        assertEquals(78.53981633974483, ShapeUtil.circleArea(5), 0.0001);
    }

    @Test
    void testCircleAreaInvalidRadius() {
        assertThrows(IllegalArgumentException.class, () -> ShapeUtil.circleArea(-1));
    }

    @Test
    void testRectangleArea() {
        assertEquals(20, ShapeUtil.rectangleArea(4, 5));
    }

    @Test
    void testRectangleAreaInvalidDimensions() {
        assertThrows(IllegalArgumentException.class, () -> ShapeUtil.rectangleArea(-1, 5));
    }

    @Test
    void testTriangleArea() {
        assertEquals(10, ShapeUtil.triangleArea(5, 4));
    }

    @Test
    void testTriangleAreaInvalidDimensions() {
        assertThrows(IllegalArgumentException.class, () -> ShapeUtil.triangleArea(-1, 5));
    }
}