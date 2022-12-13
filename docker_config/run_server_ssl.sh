#!/bin/bash

# 停止/移除既存容器
docker stop postgres-db-server
docker stop vault-server
docker rm postgres-db-server
docker rm vault-server

# 初次启动容器，并初期化Postgresql
docker-compose up -d
sleep 5
docker exec -it postgres-db-server bash /data/postgresql/init_server.sh
docker stop postgres-db-server
docker stop vault-server
docker rm vault-server

# 再次启动容器
sleep 5
docker start postgres-db-server
docker-compose logs -f
