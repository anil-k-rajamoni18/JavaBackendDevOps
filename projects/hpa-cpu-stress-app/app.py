# app.py
from flask import Flask
import time
import threading

app = Flask(__name__)

@app.route("/")
def cpu_stress():
    def burn_cpu():
        now = time.time()
        while time.time() - now < 5:
            pass  # Burn CPU for 5 seconds
    thread = threading.Thread(target=burn_cpu)
    thread.start()
    return "CPU Stress Started", 200

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
