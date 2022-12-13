#!/bin/sh

# 配置Vault连接
export VAULT_ADDR="http://127.0.0.1:8200"
export VAULT_TOKEN="$1"

# 验证Vault连接状况
vault status

# 创建secret engine：database
vault secrets enable database

# 创建与Postgresql的连接
#  ** 所用账户为db_config/init_db.sql中追加的role：vault。
vault write database/config/test_db \
      plugin_name="postgresql-database-plugin" \
      allowed_roles="dbuser" \
      connection_url="postgresql://{{username}}:{{password}}@192.168.3.137:5432/test_db" \
      username="vault" \
      password="vault-password"

# 创建Vault凭证角色
vault write database/roles/dbuser \
    db_name="test_db" \
    default_ttl="1h" \
    max_ttl="24h" \
    creation_statements="CREATE USER \"{{name}}\" WITH SUPERUSER ENCRYPTED PASSWORD '{{password}}' VALID UNTIL '{{expiration}}'; GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO \"{{name}}\";" \
    revocation_statements="REVOKE ALL PRIVILEGES ON ALL TABLES IN SCHEMA public FROM \"{{name}}\"; DROP OWNED BY \"{{name}}\"; DROP ROLE \"{{name}}\";"

# 使用创建的Postgresql连接和Vault凭证角色，创建并查看Postgresql登录账号和密码
vault read database/creds/dbuser

