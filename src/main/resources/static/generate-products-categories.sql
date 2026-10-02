BEGIN;

-- 1. Insert 10 Categories
INSERT INTO categories (name, created_at, updated_at)
VALUES 
    ('Electronics', NOW(), NOW()),
    ('Clothing & Apparel', NOW(), NOW()),
    ('Home & Kitchen', NOW(), NOW()),
    ('Books & Stationery', NOW(), NOW()),
    ('Beauty & Personal Care', NOW(), NOW()),
    ('Sports & Outdoors', NOW(), NOW()),
    ('Toys & Games', NOW(), NOW()),
    ('Automotive', NOW(), NOW()),
    ('Health & Wellness', NOW(), NOW()),
    ('Groceries', NOW(), NOW());

-- 2. Insert 1000 Products dynamically
INSERT INTO products (
    title, 
    description, 
    price, 
    category_id, 
    image, 
    rating, 
    created_at, 
    updated_at
)
SELECT 
    -- Generates title like 'Premium Product #1'
    adjectives[1 + floor(random() * array_length(adjectives, 1))::int] || ' ' || 
    nouns[1 + floor(random() * array_length(nouns, 1))::int] || ' ' || g.id AS title,

    -- Dynamic description
    'High-quality item designed for everyday use. Durable, reliable, and rated highly by users.' AS description,

    -- Price between 5.00 and 500.00
    ROUND((random() * 495 + 5)::numeric, 2) AS price,

    -- Assign to a random category ID (1 to 10)
    (SELECT id FROM categories ORDER BY random() LIMIT 1) AS category_id,

    -- Image placeholder URL
    'https://picsum.photos/seed/' || g.id || '/400/400' AS image,

    -- Rating between 1.0 and 5.0 rounded to 1 decimal place
    ROUND((random() * 4.0 + 1.0)::numeric, 1) AS rating,

    -- Random creation timestamp within the past 30 days
    NOW() - (random() * INTERVAL '30 days') AS created_at,
    
    NOW() AS updated_at

FROM generate_series(1, 1000) AS g(id),
LATERAL (
    SELECT 
        ARRAY['Premium', 'Essential', 'Deluxe', 'Compact', 'Pro', 'Smart', 'Classic', 'Ultra', 'Eco-friendly', 'Ergonomic'] AS adjectives,
        ARRAY['Gadget', 'Widget', 'Tool', 'Device', 'Kit', 'Pack', 'Item', 'Unit', 'Set', 'Accessory'] AS nouns
) AS words;

COMMIT;