package utils;

import org.akteach.util.StringUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testReverse() {
        assertEquals("olleh", StringUtils.reverse("hello"));
    }

    @Test
    void testIsPalindrome() {
        assertTrue(StringUtils.isPalindrome("madam"));
        assertFalse(StringUtils.isPalindrome("hello"));
    }

    @Test
    void testCountVowels() {
        assertEquals(3, StringUtils.countVowels("hello"));
        assertEquals(5, StringUtils.countVowels("aeiou"));
    }

    @Test
    void testIsEmptyOrNull() {
        assertTrue(StringUtils.isEmptyOrNull(""));
        assertTrue(StringUtils.isEmptyOrNull(null));
        assertFalse(StringUtils.isEmptyOrNull("hello"));
    }
}
