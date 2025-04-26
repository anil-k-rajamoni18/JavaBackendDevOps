# main.py
from Python.utils import get_data_from_api, what_time_is_it

def process():
    data = get_data_from_api()
    return f"Processed {data}"

def current_time():
    current_date = what_time_is_it()
    return f"Today: {current_date}"

print(current_time())