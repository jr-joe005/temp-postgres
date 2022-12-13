#!/bin/bash

# 停止/移除既存容器
docker stop postgres-db-server
docker stop vault-server
docker rm postgres-db-server
docker rm vault-server

# 初次启动容器
docker-compose up -d

# 再次启动Vault服务。* 初期启动时，DB服务可能未就绪。
sleep 5
docker stop vault-server
docker start vault-server
docker-compose logs -f

