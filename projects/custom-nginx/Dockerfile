FROM nginx:alpine

# Install bash and required tools
RUN apk add --no-cache bash curl iproute2

# Copy custom config and scripts
COPY nginx.conf /etc/nginx/nginx.conf
COPY entrypoint.sh /entrypoint.sh
COPY index.sh /index.sh

RUN chmod +x /entrypoint.sh /index.sh

# Run custom entrypoint
ENTRYPOINT ["/entrypoint.sh"]
