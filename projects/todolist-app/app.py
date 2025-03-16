from flask import Flask, request, jsonify, render_template

app = Flask(__name__)

# In-memory data store
tasks = []

# Route to get all tasks
@app.route('/tasks', methods=['GET'])
def get_tasks():
    return jsonify({"tasks": tasks})

# Route to add a new task
@app.route('/tasks', methods=['POST'])
def add_task():
    data = request.get_json()
    if not data.get('title'):
        return jsonify({"error": "Title is required"}), 400
    task = {
        'id': len(tasks) + 1,
        'title': data['title'],
        'done': False
    }
    tasks.append(task)
    return jsonify(task), 201

# Route to update a task
@app.route('/tasks/<int:task_id>', methods=['PUT'])
def update_task(task_id):
    task = next((task for task in tasks if task['id'] == task_id), None)
    if task is None:
        return jsonify({"error": "Task not found"}), 404

    data = request.get_json()
    task['title'] = data.get('title', task['title'])
    task['done'] = data.get('done', task['done'])
    return jsonify(task)

# Route to delete a task
@app.route('/tasks/<int:task_id>', methods=['DELETE'])
def delete_task(task_id):
    task = next((task for task in tasks if task['id'] == task_id), None)
    if task is None:
        return jsonify({"error": "Task not found"}), 404

    tasks.remove(task)
    return jsonify({"message": "Task deleted successfully"}), 200

# Frontend route (HTML page)
@app.route('/')
def index():
    return render_template('index.html', tasks=tasks)

if __name__ == '__main__':
    app.run(debug=True, host="0.0.0.0", port=5000)