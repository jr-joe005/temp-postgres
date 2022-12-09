#!/bin/bash
docker stop postgres-db-server
docker stop vault-server
docker rm postgres-db-server
docker rm vault-server

docker-compose up -d

sleep 5
docker stop vault-server
docker start vault-server
docker-compose logs -f

