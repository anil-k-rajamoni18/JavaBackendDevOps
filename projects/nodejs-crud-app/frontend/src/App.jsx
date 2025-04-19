import { useEffect, useState } from "react";
import axios from "axios";

// const API_URL = "http://localhost:3000/books"; // node api endpoint
const API_URL = "http://book-nodeapi-app-service:3000/books";  // K8s node service name
function App() {
  const [books, setBooks] = useState([]);
  const [form, setForm] = useState({ title: "", author: "", id: null });

  const fetchBooks = async () => {
    const res = await axios.get(API_URL);
    setBooks(res.data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (form.id) {
      await axios.put(`${API_URL}/${form.id}`, form);
    } else {
      await axios.post(API_URL, form);
    }
    setForm({ title: "", author: "", id: null });
    fetchBooks();
  };

  const handleEdit = (book) => setForm(book);
  const handleDelete = async (id) => {
    await axios.delete(`${API_URL}/${id}`);
    fetchBooks();
  };

  useEffect(() => {
    fetchBooks();
  }, []);

  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-50 to-slate-200 p-6">
      <div className="max-w-3xl mx-auto bg-white rounded-2xl shadow-xl p-8">
        <h1 className="text-4xl font-bold text-center text-blue-800 mb-8">📚 Book Manager</h1>

        <form
          onSubmit={handleSubmit}
          className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-8"
        >
          <input
            type="text"
            placeholder="Book Title"
            value={form.title}
            onChange={(e) => setForm({ ...form, title: e.target.value })}
            className="p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            required
          />
          <input
            type="text"
            placeholder="Author"
            value={form.author}
            onChange={(e) => setForm({ ...form, author: e.target.value })}
            className="p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            required
          />
          <button
            type="submit"
            className="bg-blue-600 hover:bg-blue-700 text-white font-semibold py-3 px-4 rounded-lg transition"
          >
            {form.id ? "Update Book" : "Add Book"}
          </button>
        </form>

        <div className="space-y-4">
          {books.map((book) => (
            <div
              key={book.id}
              className="flex justify-between items-center bg-gray-50 hover:bg-white border border-gray-200 p-4 rounded-lg shadow-sm transition"
            >
              <div>
                <p className="text-lg font-semibold text-gray-800">{book.title}</p>
                <p className="text-sm text-gray-500">by {book.author}</p>
              </div>
              <div className="flex space-x-2">
                <button
                  onClick={() => handleEdit(book)}
                  className="text-yellow-600 hover:text-yellow-700 font-medium transition"
                >
                  Edit
                </button>
                <button
                  onClick={() => handleDelete(book.id)}
                  className="text-red-600 hover:text-red-700 font-medium transition"
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default App;
