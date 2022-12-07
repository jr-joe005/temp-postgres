#!/bin/bash
echo "conf file move ... START"
cp -f /data/postgresql/config_file/pg_hba.conf /var/lib/postgresql/data
cp -f /data/postgresql/config_file/postgresql.conf /var/lib/postgresql/data
cp -f /data/postgresql/ssl_file/root.crt /var/lib/postgresql/data
cp -f /data/postgresql/ssl_file/server.crt /var/lib/postgresql/data
cp -f /data/postgresql/ssl_file/server.key /var/lib/postgresql/data
echo "conf file move ... END"

echo "authority modify ... START"
cd /var/lib/postgresql/
chown -R postgres:postgres data
chmod -R 0700 data
echo "authority modify ... END"
