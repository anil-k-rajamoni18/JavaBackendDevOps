"""
Calculator Module
=================

A simple calculator that performs basic arithmetic operations:
- Addition
- Subtraction
- Multiplication
- Division

Example:
    >>> from app import add
    >>> add(2, 3)
    5
"""

def add(a, b):
    """Returns the sum of a and b.
    
    Args:
        a (float/int): First number
        b (float/int): Second number
        
    Returns:
        float/int: Sum of a and b
    """
    return a + b

def subtract(a, b):
    """Returns the difference between a and b.
    
    Args:
        a (float/int): First number
        b (float/int): Second number
        
    Returns:
        float/int: Difference between a and b
    """
    return a - b

def multiply(a, b):
    """Returns the product of a and b.
    
    Args:
        a (float/int): First number
        b (float/int): Second number
        
    Returns:
        float/int: Product of a and b
    """
    return a * b

def divide(a, b):
    """Returns the division of a by b.
    
    Args:
        a (float/int): Numerator
        b (float/int): Denominator (cannot be zero)
        
    Returns:
        float: Result of division
        
    Raises:
        ValueError: If b is zero
    """
    if b == 0:
        raise ValueError("Cannot divide by zero!")
    return a / b
