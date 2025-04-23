def even_or_odd(num: int) -> str:
    if num < 0:
        return f"Can't test for negative number: {num}"
    else:
        return "even" if num % 2 == 0 else "odd"