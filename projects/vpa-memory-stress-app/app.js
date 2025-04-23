const express = require('express');
const app = express();

app.get('/', (req, res) => {
  const bigArray = [];
  for (let i = 0; i < 1000000; i++) {
    bigArray.push("This is a memory stress test " + i);
  }
  res.send('Memory stress triggered 🚀');
});

app.listen(3000, () => {
  console.log('Server is running on port 3000');
});
