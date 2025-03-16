import pytest
from app import app

client = app.test_client()

# Test GET all tasks
def test_get_tasks():
    response = client.get('/tasks')
    assert response.status_code == 200
    assert response.json == {"tasks": []}

# Test POST a new task
def test_add_task():
    response = client.post('/tasks', json={'title': 'Test Task'})
    assert response.status_code == 201
    assert response.json['title'] == 'Test Task'
    assert response.json['done'] is False

# Test PUT update task
def test_update_task():
    response = client.post('/tasks', json={'title': 'Test Task'})
    task_id = response.json['id']
    response = client.put(f'/tasks/{task_id}', json={'title': 'Updated Task', 'done': True})
    assert response.status_code == 200
    assert response.json['title'] == 'Updated Task'
    assert response.json['done'] is True

# Test DELETE task
def test_delete_task():
    response = client.post('/tasks', json={'title': 'Test Task'})
    task_id = response.json['id']
    response = client.delete(f'/tasks/{task_id}')
    assert response.status_code == 200
    assert response.json['message'] == 'Task deleted successfully'

# Test task not found
def test_task_not_found():
    response = client.delete('/tasks/9999')
    assert response.status_code == 404
    assert response.json['error'] == 'Task not found'
