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


❌ Error:
```pgsql
Blocked request. This host ("ec2-xx-xxx-xxx-xxx.region.compute.amazonaws.com") is not allowed.
To allow this host, add "ec2-xx-xxx-xxx-xxx.region.compute.amazonaws.com" to `preview.allowedHosts` in vite.config.js.
```
🧠 What It Means:

You're probably trying to preview or access a Vite app that's hosted on an Amazon EC2 server with the hostname:

Vite's preview server, by default, restricts which hosts can access it for security reasons (to prevent unauthorized or unexpected domains from connecting).

Since your EC2 hostname isn't in the list of allowed hosts, Vite is blocking the request.


🛠️ Why This Happens:

When you run:
```bash
npm run preview
```
-  Vite serves the built production files with a simple web server. This is not the same as npm run dev, which is for development only.


✅ How to Fix It:
Update your vite.config.js like this:
```js
// vite.config.js
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    preview: {
      allowedHosts: ['ec2-xx-xxx-xxx-xxx.region.compute.amazonaws.com']
    }
  }
})

```

To allow all host 
```js
export default defineConfig({
  plugins: [react()],
  server: {
    preview: {
      allowedHosts: ['*'] // 🚨 Allows ALL hosts
    }
  }
})
```