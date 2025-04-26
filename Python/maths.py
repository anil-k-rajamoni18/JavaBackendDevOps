def add(n1:int, n2:int) -> int:
    return n1 + n2

def subtract(n1:int, n2:int) -> int:
    return n1 - n2

def divide(n1: int, n2:int) -> int | str:
    if n2 == 0:
        return f"Can't divide by {n2}"
    return n1/n2

