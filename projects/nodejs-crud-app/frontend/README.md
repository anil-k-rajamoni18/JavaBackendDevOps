### 🏃 Run the App
1. Start the Backend
```sh
cd backend
npm install
npm start
```

2. Start the Frontend
```sh
cd frontend
npm install
npm run dev
```

Visit 👉 http://localhost:5173


🧠 Why is React running on http://localhost:4173/?

- React (Vite-based projects especially) uses port 5173 in dev mode by default. 
- But if you're seeing 4173, you’re likely running the production build with vite preview, which uses port 4173 by default.

```bash
npm run dev → uses port 5173
npm run build + npm run preview → uses port 4173
```

- You can customize this in vite.config.js like:
```code
export default defineConfig({
  preview: {
    port: 3000, // or whatever you prefer
  }
})
```

🐳 React running in Docker container, but not accessible — why?
Even if you expose the port with EXPOSE in your Dockerfile or -p in docker run, the app must bind to 0.0.0.0, not localhost, inside the container.

🔥 Problem:
Inside Docker, if the app binds to localhost, it’s only accessible within the container.

*vite.config.js*:
```code
export default defineConfig({
  server: {
    host: '0.0.0.0',
    port: 5173
  }
});

```