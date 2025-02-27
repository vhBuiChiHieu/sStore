USE gredirect2;

INSERT INTO role (created_at, created_by, updated_at, updated_by, description, name)
VALUES
    (NOW(6), NULL, NOW(6), NULL, 'Standard user role with basic privileges', 'USER'),
    (NOW(6), NULL, NOW(6), NULL, 'Administrator role with full privileges', 'ADMIN'),
    (NOW(6), NULL, NOW(6), NULL, 'Guest role with minimal privileges', 'GUEST');

INSERT INTO permission (created_at, created_by, updated_at, updated_by, description, name)
VALUES
    (NOW(6), NULL, NOW(6), NULL, 'Read all account information', 'ACCOUNT_READ'),
    (NOW(6), NULL, NOW(6), NULL, 'Read own account information', 'ACCOUNT_READ_OWN'),
    (NOW(6), NULL, NOW(6), NULL, 'Delete accounts', 'ACCOUNT_DELETE'),
    (NOW(6), NULL, NOW(6), NULL, 'Update all account information', 'ACCOUNT_UPDATE'),
    (NOW(6), NULL, NOW(6), NULL, 'Update own account information', 'ACCOUNT_UPDATE_OWN'),
    (NOW(6), NULL, NOW(6), NULL, 'Create new accounts', 'ACCOUNT_CREATE');

-- Gán permissions cho USER
INSERT INTO role_has_permission (role_id, permission_id)
SELECT
    r.id,
    p.id
FROM role r
         JOIN permission p ON p.name IN ('ACCOUNT_READ_OWN', 'ACCOUNT_UPDATE_OWN')
WHERE r.name = 'USER';

-- Gán permissions cho ADMIN
INSERT INTO role_has_permission (role_id, permission_id)
SELECT
    r.id,
    p.id
FROM role r
         JOIN permission p ON p.name IN ('ACCOUNT_READ', 'ACCOUNT_READ_OWN', 'ACCOUNT_DELETE', 'ACCOUNT_UPDATE', 'ACCOUNT_UPDATE_OWN', 'ACCOUNT_CREATE')
WHERE r.name = 'ADMIN';
