#!/bin/bash

CLIENT_IP=$(curl -s https://api.ipify.org)
HOST_IP=$(hostname -i)

cat <<EOF > /usr/share/nginx/html/index.html
<!DOCTYPE html>
<html>
<head>
  <title>🌐 Custom NGINX</title>
  <meta charset="UTF-8">
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f2f2f2;
      color: #333;
      text-align: center;
      padding: 50px;
    }
    h1 {
      font-size: 2.5em;
      color: #2c3e50;
    }
    p {
      font-size: 1.3em;
      margin: 10px 0;
    }
    img {
      margin-top: 30px;
      width: 200px;
      border-radius: 10px;
    }
  </style>
</head>
<body>
  <h1>🚀 Welcome to Custom NGINX Web Server! 🎉</h1>
  <p><strong>🌍 Your IP:</strong> $CLIENT_IP</p>
  <p><strong>🏠 Host IP:</strong> $HOST_IP</p>
  <img src="https://upload.wikimedia.org/wikipedia/commons/c/c5/Nginx_logo.svg" alt="NGINX Logo">
</body>
</html>
EOF
