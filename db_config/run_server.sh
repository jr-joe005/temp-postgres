docker stop postgres-db-server
docker rm postgres-db-server
docker-compose up
#docker-compose up -d
#sleep 10
#docker exec -it postgres-db-server bash /data/postgresql/init_server.sh
#docker stop postgres-db-server
#sleep 5
#docker start postgres-db-server
#docker-compose logs -f
