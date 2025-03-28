"""
Unit Tests for Calculator Operations
===================================

Test cases for verifying the correctness of basic arithmetic operations:
- Addition
- Subtraction
- Multiplication
- Division
"""

import unittest
from app import add, subtract, multiply, divide

class TestApp(unittest.TestCase):
    """Test cases for calculator application functions."""
    def test_add(self):
        """Test addition operation with various number combinations."""
        self.assertEqual(add(2, 3), 5)
        self.assertEqual(add(-1, 1), 0)
        self.assertEqual(add(0, 0), 0)

    def test_subtract(self):
        """Test subtraction operation with positive and negative numbers."""
        self.assertEqual(subtract(5, 3), 2)
        self.assertEqual(subtract(3, 5), -2)
        self.assertEqual(subtract(0, 0), 0)

    def test_multiply(self):
        """Test multiplication operation including zero cases."""
        self.assertEqual(multiply(2, 3), 6)
        self.assertEqual(multiply(-2, 3), -6)
        self.assertEqual(multiply(0, 5), 0)

    def test_divide(self):
        """Test division operation and zero division handling."""
        self.assertEqual(divide(6, 2), 3)
        self.assertEqual(divide(-6, 2), -3)
        with self.assertRaises(ValueError):
            divide(5, 0)

if __name__ == "__main__":
    unittest.main()
