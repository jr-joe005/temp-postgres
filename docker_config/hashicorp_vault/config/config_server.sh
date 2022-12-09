#!/bin/sh

# Configure our access
export VAULT_ADDR="http://127.0.0.1:8200"
export VAULT_TOKEN=$1

# verify the connection
vault status

# Enable the database secrets engine if it is not already enabled
vault secrets enable database

# Configure Vault with the proper plugin and connection information
#  ** db_config/init_db.sql中追加的role：vault。
vault write database/config/test_db \
      plugin_name="postgresql-database-plugin" \
      allowed_roles="dbuser" \
      connection_url="postgresql://{{username}}:{{password}}@192.168.3.67:5432/test_db" \
      username="vault" \
      password="vault-password"

# Configure a role that maps a name in Vault to an SQL statement to execute to create the database credential
vault write database/roles/dbuser \
    db_name="test_db" \
    default_ttl="1h" \
    max_ttl="24h" \
    creation_statements="CREATE USER \"{{name}}\" WITH SUPERUSER ENCRYPTED PASSWORD '{{password}}' VALID UNTIL '{{expiration}}'; GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO \"{{name}}\";" \
    revocation_statements="REVOKE ALL PRIVILEGES ON ALL TABLES IN SCHEMA public FROM \"{{name}}\"; DROP OWNED BY \"{{name}}\"; DROP ROLE \"{{name}}\";"

# 现在我们已经设置了 Vault 角色,让我们通过简单地从database/creds/dbuser读取来尝试创建一些凭据
vault read database/creds/dbuser

