package utils;

import org.akteach.util.MathUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MathUtilTest {
    @Test
    void testIsEven() {
        assertTrue(MathUtil.isEven(4));
        assertFalse(MathUtil.isEven(5));
    }

    @Test
    void testFactorial() {
        assertEquals(120, MathUtil.factorial(5));
    }

    @Test
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> MathUtil.factorial(-1));
    }
}