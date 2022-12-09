---- 测试用临时表 ----
CREATE TABLE person
(
    id INT,
    name VARCHAR(20),
    age INT,
    address VARCHAR(255)
);

INSERT INTO person(id, name, age, address) VALUES('100001', 'name_01', '20', 'address_01');
INSERT INTO person(id, name, age, address) VALUES('100002', 'name_02', '30', 'address_02');
INSERT INTO person(id, name, age, address) VALUES('100003', 'name_03', '25', 'address_03');

---- Vault用role ----
CREATE ROLE "vault" WITH SUPERUSER LOGIN ENCRYPTED PASSWORD 'vault-password';

------ Vault用资源 ----
---- ** sql来源：PostgreSQL Storage Backend
---- ** URL：https://developer.hashicorp.com/vault/docs/configuration/storage/postgresql
--CREATE TABLE vault_kv_store (
--  parent_path TEXT COLLATE "C" NOT NULL,
--  path        TEXT COLLATE "C",
--  key         TEXT COLLATE "C",
--  value       BYTEA,
--  CONSTRAINT pkey PRIMARY KEY (path, key)
--);
--CREATE INDEX parent_path_idx ON vault_kv_store (parent_path);
--
--CREATE TABLE vault_ha_locks (
--  ha_key                                      TEXT COLLATE "C" NOT NULL,
--  ha_identity                                 TEXT COLLATE "C" NOT NULL,
--  ha_value                                    TEXT COLLATE "C",
--  valid_until                                 TIMESTAMP WITH TIME ZONE NOT NULL,
--  CONSTRAINT ha_key PRIMARY KEY (ha_key)
--);
--
--CREATE FUNCTION vault_kv_put(_parent_path TEXT, _path TEXT, _key TEXT, _value BYTEA) RETURNS VOID AS
--$$
--BEGIN
--    LOOP
--        -- first try to update the key
--        UPDATE vault_kv_store
--          SET (parent_path, path, key, value) = (_parent_path, _path, _key, _value)
--          WHERE _path = path AND key = _key;
--        IF found THEN
--            RETURN;
--        END IF;
--        -- not there, so try to insert the key
--        -- if someone else inserts the same key concurrently,
--        -- we could get a unique-key failure
--        BEGIN
--            INSERT INTO vault_kv_store (parent_path, path, key, value)
--              VALUES (_parent_path, _path, _key, _value);
--            RETURN;
--        EXCEPTION WHEN unique_violation THEN
--            -- Do nothing, and loop to try the UPDATE again.
--        END;
--    END LOOP;
--END;
--$$
--LANGUAGE plpgsql;