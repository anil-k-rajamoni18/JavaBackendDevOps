# 🌐 Hosting a Static Website on AWS S3

This guide walks you through hosting a **static website on Amazon S3** using a real example with `index.html`, `about.html`, and `style.css`.

---

## 🎯 Example Project Structure

```text
my-website/
├── index.html
├── about.html
└── style.css
```

## ✅ Steps to Host a Static Website on S3

### 1. Prepare Your Files
Create a folder with your static files. Example content:
**index.html**
```html
<!DOCTYPE html>
<html>
<head>
  <title>Welcome</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
  <h1>Welcome to My Static Site</h1>
  <a href="about.html">About</a>
</body>
</html>
```

**about.html**
```html
<!DOCTYPE html>
<html>
<head>
  <title>About</title>
</head>
<body>
  <h1>About Us</h1>
  <a href="index.html">Home</a>
</body>
</html>
```

**style.css**
```css
body {
  background-color: #f9f9f9;
  font-family: sans-serif;
  text-align: center;
}
```

### 2. Create an S3 Bucket
- Go to AWS Management Console > S3.
- Click “Create bucket”.
- Set a unique bucket name (e.g., my-website-demo-2025).
- Select a region.
- Uncheck “Block all public access”.
- Click Create bucket.

### 3. Upload Files to the Bucket
- Open your bucket.
- Click Upload > Add files.
- Select index.html, about.html, and style.css.
- Click Upload.


### 4. Enable Static Website Hosting
- Go to the Properties tab.
- Scroll to Static website hosting.
- Click Edit and configure:
    - Enable static hosting
    - Index document: index.html
    - Error document (optional): 404.html

- Save changes.
- Note the endpoint URL, e.g.:
```bash
http://my-website-demo-2025.s3-website-us-east-1.amazonaws.com
```

### 5. Set Bucket Policy for Public Access
- Go to the Permissions tab.
- Scroll to Bucket policy and add:

```json 
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "PublicReadGetObject",
      "Effect": "Allow",
      "Principal": "*",
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::my-website-demo-2025/*"
    }
  ]
}
```


### 🌐 Optional: Use Custom Domain
You can:
    - Use Route 53 or another DNS provider to point your domain to the S3 endpoint.
    - Use CloudFront for HTTPS/SSL support and CDN caching.
