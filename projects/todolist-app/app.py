"""
Flask Task API
==============

A simple RESTful API for task management with Flask.
Includes CRUD operations and a frontend interface.
"""

from flask import Flask, request, jsonify, render_template

app = Flask(__name__)

# In-memory data store
tasks = []

@app.route('/tasks', methods=['GET'])
def get_tasks():
    """Retrieve all tasks from the in-memory store.
    
    Returns:
        JSON: List of all tasks with status code 200
    """
    return jsonify({"tasks": tasks})

@app.route('/tasks', methods=['POST'])
def add_task():
    """Add a new task to the in-memory store.
    
    Returns:
        JSON: Newly created task with status code 201
        JSON: Error message with status code 400 if title is missing
    """
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

@app.route('/tasks/<int:task_id>', methods=['PUT'])
def update_task(task_id):
    """Update an existing task.
    
    Args:
        task_id (int): ID of the task to update
        
    Returns:
        JSON: Updated task with status code 200
        JSON: Error message with status code 404 if task not found
    """
    task = next((task for task in tasks if task['id'] == task_id), None)
    if task is None:
        return jsonify({"error": "Task not found"}), 404

    data = request.get_json()
    task['title'] = data.get('title', task['title'])
    task['done'] = data.get('done', task['done'])
    return jsonify(task)

@app.route('/tasks/<int:task_id>', methods=['DELETE'])
def delete_task(task_id):
    """Delete a task from the in-memory store.
    
    Args:
        task_id (int): ID of the task to delete
        
    Returns:
        JSON: Success message with status code 200
        JSON: Error message with status code 404 if task not found
    """
    task = next((task for task in tasks if task['id'] == task_id), None)
    if task is None:
        return jsonify({"error": "Task not found"}), 404

    tasks.remove(task)
    return jsonify({"message": "Task deleted successfully"}), 200

@app.route('/')
def index():
    """Render the frontend HTML interface.
    
    Returns:
        HTML: Rendered template with tasks data
    """
    return render_template('index.html', tasks=tasks)

if __name__ == '__main__':
    app.run(debug=True, host="0.0.0.0", port=5000)