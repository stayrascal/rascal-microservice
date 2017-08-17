# nginx.tpl

upstream api_server {
  least_conn;
  {{range service "identity"}}
  server  {{.Address}}:{{.Port}};
  {{else}}server 127.0.0.1:9191;{{end}}
}

server {
    listen       80;
    server_name  localhost;
    location / {
        root   /usr/share/nginx/html;
        index  index.html index.htm;
    }
    location /api {
      proxy_pass http://api_server;
    }
}

# 启用Nginx的Gzip可以对服务器端响应内容进行压缩从而减少一定的客户端响应时间
gzip on;
gzip_min_length 1k;
gzip_buffers  4 32k;
gzip_types    text/plain application/x-javascript application/javascript text/xml text/css;
gzip_vary on;

# 缓存图片以及其它静态资源可以减少对Zuul实例的请求量
proxy_buffering on;
proxy_cache_valid any 10m;
proxy_cache_path /data/cache levels=1:2 keys_zone=my-cache:8m max_size=1000m inactive=600m;
proxy_temp_path /data/temp;
proxy_buffer_size 4k;
proxy_buffers 100 8k;

location ~* (images)    {
  proxy_pass http://api_server;
  # cache setting
  proxy_cache my-cache;
  proxy_cache_valid 200;
}


# Websocket的代理
location /sockjs {
    proxy_pass http://api_server;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header Host $host;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    # WebSocket support (nginx 1.4)
    proxy_http_version 1.1;
    proxy_set_header Upgrade $http_upgrade;
    proxy_set_header Connection "upgrade";
    proxy_connect_timeout 90;
    proxy_send_timeout 90;
    proxy_read_timeout 90;
    # !!!Support Spring Boot
    proxy_pass_header X-XSRF-TOKEN;
    proxy_set_header Origin "http://localhost:4000";
  }