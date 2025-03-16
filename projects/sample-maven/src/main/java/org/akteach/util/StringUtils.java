package org.akteach.util;

public class StringUtils {

    // Reverse a string
    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // Check if a string is a palindrome
    public static boolean isPalindrome(String str) {
        String reversed = reverse(str);
        return str.equalsIgnoreCase(reversed);
    }

    // Count the number of vowels in a string
    public static int countVowels(String str) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (char c : str.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    // Check if a string is empty or null
    public static boolean isEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
    }
}
