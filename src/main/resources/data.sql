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


INSERT INTO `account` (status, created_at, created_by, updated_at, updated_by, mail, hash_password, phone) VALUES
                                                                                                               (0, '2025-02-28 10:00:00.000000', NULL, '2025-02-28 10:00:00.000000', NULL, 'admin@admin.com', '$2a$10$w7USNBRLKpcukgoSdGh9OOf6MO9VK7pb1dw6OM3cDD7Y0JBx4NpYG', '0325978045'),
                                                                                                               (0, '2025-02-28 10:00:00.000000', NULL, '2025-02-28 10:00:00.000000', NULL, 'user2@example.com', '$2a$10$w7USNBRLKpcukgoSdGh9OOf6MO9VK7pb1dw6OM3cDD7Y0JBx4NpYG', NULL),
                                                                                                               (0, '2025-02-28 10:00:00.000000', NULL, '2025-02-28 10:00:00.000000', NULL, 'user3@example.com', '$2a$10$w7USNBRLKpcukgoSdGh9OOf6MO9VK7pb1dw6OM3cDD7Y0JBx4NpYG', NULL),
                                                                                                               (0, '2025-02-28 10:00:00.000000', NULL, '2025-02-28 10:00:00.000000', NULL, 'user4@example.com', '$2a$10$w7USNBRLKpcukgoSdGh9OOf6MO9VK7pb1dw6OM3cDD7Y0JBx4NpYG', NULL),
                                                                                                               (0, '2025-02-28 10:00:00.000000', NULL, '2025-02-28 10:00:00.000000', NULL, 'user5@example.com', '$2a$10$w7USNBRLKpcukgoSdGh9OOf6MO9VK7pb1dw6OM3cDD7Y0JBx4NpYG', NULL);

INSERT INTO `account_has_role` (account_id, role_id) VALUES
                                                         (1, 2),
                                                         (2, 1),
                                                         (3, 1),
                                                         (4, 1),
                                                         (5, 1);

INSERT INTO `user_info` (date_of_birth, account_id, created_at, created_by, updated_at, updated_by, avatar, description, first_name, last_name) VALUES
                                                                                                                                                    ('1990-01-01', 1, '2025-02-28 10:00:00.000000', NULL, '2025-02-28 10:00:00.000000', NULL, NULL, NULL, 'Bui', 'Chi Hieu'),
                                                                                                                                                    ('1990-01-01', 2, '2025-02-28 10:00:00.000000', NULL, '2025-02-28 10:00:00.000000', NULL, NULL, NULL, 'Bob', 'Johnson'),
                                                                                                                                                    ('1990-01-01', 3, '2025-02-28 10:00:00.000000', NULL, '2025-02-28 10:00:00.000000', NULL, NULL, NULL, 'Charlie', 'Brown'),
                                                                                                                                                    ('1990-01-01', 4, '2025-02-28 10:00:00.000000', NULL, '2025-02-28 10:00:00.000000', NULL, NULL, NULL, 'David', 'Williams'),
                                                                                                                                                    ('1990-01-01', 5, '2025-02-28 10:00:00.000000', NULL, '2025-02-28 10:00:00.000000', NULL, NULL, NULL, 'Eve', 'Davis');

INSERT INTO `category` (created_at, created_by, updated_at, updated_by, description, name, slug) VALUES
    ('2025-02-28 10:00:00.000000', 1, '2025-02-28 10:00:00.000000', NULL, 'Áo chuyên dụng cho việc vận động, thoải mái thoáng mát', 'Giày Thể Thao', 'giay-the-thao');


INSERT INTO `brand` (created_at, created_by, updated_at, updated_by, description, logo_url, name) VALUES
    ('2025-02-28 10:00:00.000000', 1, '2025-02-28 10:00:00.000000', NULL, 'Thương hiệu đồ thể thao', NULL, 'Nike');

INSERT INTO `product` (brand_id, category_id, created_at, created_by, updated_at, updated_by, description, name) VALUES
    (1, 1, '2025-02-28 10:00:00.000000', 1, '2025-02-28 10:00:00.000000', NULL, 'Giày chạy bộ', 'Air Max');

INSERT INTO `product_variant` (price, stock, created_at, created_by, product_id, updated_at, updated_by, color, size, sku) VALUES
                                                                                                                               (100.0, 10, '2025-02-28 10:00:00.000000', 2, 1, '2025-02-28 10:00:00.000000', NULL, 'Red', '42', 'AIRMAX-RED-42'),
                                                                                                                               (105.0, 5, '2025-02-28 10:00:00.000000', 2, 1, '2025-02-28 10:00:00.000000', NULL, 'Blue', '43', 'AIRMAX-BLUE-43');
INSERT INTO `cart` (account_id, created_at, created_by, updated_at, updated_by) VALUES
                                                                                    (1, '2025-02-28 10:00:00.000000', 1, '2025-02-28 10:00:00.000000', NULL),
                                                                                    (2, '2025-02-28 10:00:00.000000', 2, '2025-02-28 10:00:00.000000', NULL),
                                                                                    (3, '2025-02-28 10:00:00.000000', 3, '2025-02-28 10:00:00.000000', NULL),
                                                                                    (4, '2025-02-28 10:00:00.000000', 4, '2025-02-28 10:00:00.000000', NULL),
                                                                                    (5, '2025-02-28 10:00:00.000000', 5, '2025-02-28 10:00:00.000000', NULL);

INSERT INTO `cart_item` (quantity, cart_id, created_at, created_by, product_variant_id, updated_at, updated_by) VALUES
                                                                                                                    (2, 1, '2025-02-28 10:00:00.000000', 1, 1, '2025-02-28 10:00:00.000000', NULL),
                                                                                                                    (1, 2, '2025-02-28 10:00:00.000000', 2, 2, '2025-02-28 10:00:00.000000', NULL),
                                                                                                                    (3, 3, '2025-02-28 10:00:00.000000', 3, 1, '2025-02-28 10:00:00.000000', NULL),
                                                                                                                    (1, 4, '2025-02-28 10:00:00.000000', 4, 2, '2025-02-28 10:00:00.000000', NULL),
                                                                                                                    (2, 5, '2025-02-28 10:00:00.000000', 5, 1, '2025-02-28 10:00:00.000000', NULL);

