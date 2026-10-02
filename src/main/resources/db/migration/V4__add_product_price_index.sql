-- Index on a single column (price)
CREATE INDEX idx_product_price 
ON products (price);

-- Composite index on multiple columns (price, rating)
CREATE INDEX idx_product_price_rating 
ON products (price, rating);


-- DROP INDEX IF EXISTS idx_price;
