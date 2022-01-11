INSERT INTO products (id, name, tenant_id) VALUES (100001, 'Product 1', 'tenant1') ON CONFLICT DO NOTHING;
INSERT INTO products (id, name, tenant_id) VALUES (100002, 'Product 2', 'tenant1') ON CONFLICT DO NOTHING;
INSERT INTO products (id, name, tenant_id) VALUES (100003, 'Product 3', 'tenant2') ON CONFLICT DO NOTHING;
