USE recipe_db;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS nutrition;
DROP TABLE IF EXISTS recipe_ingredients;
DROP TABLE IF EXISTS recipes;
DROP TABLE IF EXISTS ingredients;

SET FOREIGN_KEY_CHECKS = 1;
CREATE TABLE recipes (
recipe_id INT AUTO_INCREMENT PRIMARY KEY,
recipe_name VARCHAR(100),
instructions TEXT,
default_servings INT,
prep_time INT,
cook_time INT

);
CREATE TABLE ingredients(
ingredient_id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100)
);

INSERT INTO ingredients (name)
VALUES ('Chicken breast'),
       ('Broccoli'),
       ('Carrot'),
       ('Bell pepper'),
       ('Soy sauce'),
       ('Garlic'),
       ('Ginger'),
       ('Vegetable oil'),
       ('Rice'),
       ('Onion'),
	('Scotch bonnet pepper'),
	('Pepper soup spice'),
('Salt'),
('Water'),
('Black pepper'),
('Mixed herbs'),
('Sweet Potato');

CREATE TABLE recipe_ingredients (
recipe_ingredient_id INT AUTO_INCREMENT PRIMARY KEY,
recipe_id INT,
ingredient_id INT,
quantity DOUBLE,
unit VARCHAR(50),
FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id),
FOREIGN KEY (ingredient_id) REFERENCES ingredients (ingredient_id)

);

CREATE TABLE nutrition (
nutrition_id INT AUTO_INCREMENT PRIMARY KEY,
recipe_id INT,
fat DOUBLE,
protein DOUBLE,
calories DOUBLE,
FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id)

);