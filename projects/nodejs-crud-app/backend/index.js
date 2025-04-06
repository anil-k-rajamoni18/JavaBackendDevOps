const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');

const app = express();
const PORT = 3000;

app.use(cors());
app.use(bodyParser.json());

let books = [
  { id: 1, title: 'The Alchemist', author: 'Paulo Coelho' },
  { id: 2, title: '1984', author: 'George Orwell' }
];

// Routes
app.get('/books', (req, res) => res.json(books));

app.get('/books/:id', (req, res) => {
  const book = books.find(b => b.id == req.params.id);
  res.json(book);
});

app.post('/books', (req, res) => {
  const newBook = { ...req.body, id: Date.now() };
  books.push(newBook);
  res.status(201).json(newBook);
});

app.put('/books/:id', (req, res) => {
  books = books.map(b => b.id == req.params.id ? { ...b, ...req.body } : b);
  res.json({ message: 'Book updated' });
});

app.delete('/books/:id', (req, res) => {
  books = books.filter(b => b.id != req.params.id);
  res.json({ message: 'Book deleted' });
});

app.listen(PORT, () => console.log(`🚀 Server running on http://localhost:${PORT}`));
