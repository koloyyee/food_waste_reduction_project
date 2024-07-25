-- create random items
INSERT INTO fwrp.item
(name, description, expiry_date, price, discount_rate, is_surplus, is_donation, quantity, is_available, created_at, updated_at)
VALUES
('Candy', 'A sweet treat for your sweet tooth', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Chocolate Bar', 'A delicious snack for any time of the day', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Canned Tomato Soup', 'A warm and hearty meal for a cold day', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Ravioli', 'A quick and easy meal for busy days', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Eggs', 'A staple for any kitchen', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Milk', 'A nutritious drink for any time of the day', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Bread', 'A versatile food that can be enjoyed in many ways', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Bananas', 'A healthy and delicious snack', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Apples', 'A crunchy and refreshing fruit', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Oranges', 'A juicy and tangy fruit', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Potatoes', 'A versatile vegetable that can be enjoyed in many ways', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Carrots', 'A crunchy and nutritious vegetable', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Onions', 'A flavorful vegetable that can be used in many dishes', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Tomatoes', 'A juicy and flavorful fruit that can be enjoyed in many ways', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Lettuce', 'A crisp and refreshing vegetable that can be used in salads and sandwiches', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Cucumbers', 'A crunchy and hydrating vegetable that can be enjoyed on its own or in salads', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Bell Peppers', 'A colorful and flavorful vegetable that can be enjoyed raw or cooked', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Broccoli', 'A nutritious and versatile vegetable that can be enjoyed in many ways', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Cauliflower', 'A versatile vegetable that can be enjoyed raw or cooked', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Spinach', 'A nutritious and flavorful leafy green that can be enjoyed raw or cooked', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Kale', 'A nutritious and hearty leafy green that can be enjoyed raw or cooked', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Canned Beans', 'A convenient and versatile pantry staple', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Pasta', 'A versatile and satisfying meal option', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Rice', 'A staple grain that can be enjoyed in many ways', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Olive Oil', 'A flavorful and versatile cooking oil', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Salt', 'A basic seasoning that can enhance the flavor of any dish', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Pepper', 'A versatile spice that can add heat and flavor to any dish', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Sugar', 'A sweetener that can be used in baking and cooking', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Flour', 'A versatile ingredient that can be used in baking and cooking', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Butter', 'A rich and creamy ingredient that can be used in baking and cooking', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now()),
('Cheese', 'A delicious and versatile ingredient that can be enjoyed on its own or in dishes', '2022-12-31', ROUND((RAND() * 10), 2), 0.0, false, false, FLOOR(RAND() * 100), true, now(), now());
