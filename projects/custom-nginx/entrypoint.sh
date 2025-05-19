#!/bin/bash

# Generate the dynamic HTML
/index.sh

# Start NGINX in the foreground
nginx -g "daemon off;"
