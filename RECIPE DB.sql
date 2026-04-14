-- RECIPE 1
INSERT INTO recipes (recipe_name, instructions, default_servings, prep_time, cook_time)
VALUES ('Chicken Stir Fry',
	'1. Cut chicken into thin strips and season with salt and pepper.\n
     2. Chop broccoli into florets, slice carrots into sticks, and cut bell pepper into strips.\n
     3. Mix soy sauce, minced garlic, and grated ginger to make the sauce.\n
     4. Heat oil in a wok or pan, cook chicken for 5–6 minutes until fully cooked.\n
     5. Add vegetables and stir fry for 3–4 minutes until tender.\n
     6. Pour sauce over chicken and vegetables, cook for 2 more minutes.\n
     7. Serve hot with cooked rice.',
	4,
    15,
    10
    );
   
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit)
VALUES
(1, 1, 500, 'grams'),
(1, 2, 200, 'grams'),
(1, 3, 100, 'grams'),
(1, 4, 100, 'grams'),
(1, 5, 60, 'ml'),
(1, 6, 3, 'cloves'),
(1, 7, 1, 'tbsp'),
(1, 8, 2, 'tbsp'),
(1, 9, 400, 'grams');

INSERT INTO nutrition (recipe_id, calories, protein, fat)
VALUES (1, 550, 45, 18);

-- RECIPE 2

INSERT INTO recipes (recipe_name, instructions, default_servings, prep_time, cook_time)
VALUES (
'Nigerian Chicken Pepper Soup',
'1. Wash chicken thoroughly and cut into medium-sized pieces.\n
 2. Peel and finely chop the onion.\n
 3. Crush the garlic and grate the ginger.\n
 4. Place chicken into a pot and add water.\n
 5. Add onion, garlic, ginger, and scotch bonnet pepper.\n
 6. Add pepper soup spice and salt.\n
 7. Bring to a boil, then reduce heat and simmer gently.\n
 8. Cook for 30 minutes until chicken is tender.\n
 9. Taste and adjust seasoning if needed.\n
10. Serve hot as a soup.',
3,
15,
30
);

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit)
VALUES
(2, 1, 700, 'grams'),   -- Chicken breast
(2, 10, 1, 'unit'),    -- Onion
(2, 11, 1, 'unit'),    -- Scotch bonnet pepper
(2, 12, 2, 'tbsp'),    -- Pepper soup spice
(2, 6, 3, 'cloves'),   -- Garlic
(2, 7, 1, 'tbsp'),     -- Ginger
(2, 13, 1, 'tsp'),     -- Salt
(2, 14, 1200, 'ml');   -- Water

INSERT INTO nutrition (recipe_id, calories, protein, fat)
VALUES (2, 380, 42, 10);


-- RECIPE 3

INSERT INTO recipes (recipe_name, instructions, default_servings, prep_time, cook_time)
VALUES (
'Classic Chicken Soup',
'1. Cut chicken into bite-sized pieces.\n
 2. Peel and chop the onion, carrot, and garlic.\n
 3. Add chicken to a pot with water.\n
 4. Add vegetables and season with salt and black pepper.\n
 5. Bring to a boil, then reduce heat.\n
 6. Simmer gently for 25 minutes.\n
 7. Stir occasionally to prevent sticking.\n
 8. Taste and adjust seasoning.\n
 9. Serve warm.',
4,
15,
25
);

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit)
VALUES
(3, 1, 600, 'grams'),   -- Chicken breast
(3, 10, 1, 'unit'),    -- Onion
(3, 3, 150, 'grams'),  -- Carrot
(3, 6, 2, 'cloves'),   -- Garlic
(3, 14, 1000, 'ml'),   -- Water
(3, 13, 1, 'tsp'),     -- Salt
(3, 15, 0.5, 'tsp');   -- Black pepper

INSERT INTO nutrition (recipe_id, calories, protein, fat)
VALUES (3, 420, 40, 12);


-- RECIPE 4
INSERT INTO recipes (recipe_name, instructions, default_servings, prep_time, cook_time)
VALUES (
'Asian Vegetable Fried Rice',
'1. Cook rice and allow it to cool completely.\n
 2. Finely chop broccoli, carrot, bell pepper, garlic, and ginger.\n
 3. Heat vegetable oil in a large pan or wok.\n
 4. Add garlic and ginger and stir until fragrant.\n
 5. Add vegetables and stir fry for 4–5 minutes.\n
 6. Add cooked rice and soy sauce.\n
 7. Stir continuously until evenly heated.\n
 8. Taste and adjust seasoning.\n
 9. Serve hot.',
3,
15,
15
);

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit)
VALUES
(4, 9, 400, 'grams'),   -- Rice
(4, 2, 150, 'grams'),   -- Broccoli
(4, 3, 100, 'grams'),   -- Carrot
(4, 4, 100, 'grams'),   -- Bell pepper
(4, 6, 2, 'cloves'),    -- Garlic
(4, 7, 1, 'tbsp'),      -- Ginger
(4, 5, 40, 'ml'),       -- Soy sauce
(4, 8, 1, 'tbsp');      -- Vegetable oil

INSERT INTO nutrition (recipe_id, calories, protein, fat)
VALUES (4, 410, 10, 12);

 -- RECIPE 5

INSERT INTO recipes (recipe_name, instructions, default_servings, prep_time, cook_time)
VALUES (
'Roasted Sweet Potato and Broccoli with Chicken',
'1. Preheat oven to 200°C.\n
 2. Peel and chop sweet potatoes into cubes.\n
 3. Cut chicken into bite-sized pieces.\n
 4. Chop broccoli into florets.\n
 5. Place sweet potato, broccoli, and chicken on a baking tray.\n
 6. Drizzle with vegetable oil and season with salt and black pepper.\n
 7. Toss everything to coat evenly.\n
 8. Roast in the oven for 30–35 minutes.\n
 9. Turn halfway through cooking.\n
10. Serve hot.',
3,
15,
35
);

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit)
VALUES
(5, 1, 500, 'grams'),   -- Chicken breast
(5, 2, 200, 'grams'),   -- Broccoli
(5, 17, 400, 'grams'),  -- Sweet potato
(5, 8, 1, 'tbsp'),      -- Vegetable oil
(5, 13, 1, 'tsp'),      -- Salt
(5, 15, 0.5, 'tsp');    -- Black pepper

INSERT INTO nutrition (recipe_id, calories, protein, fat)
VALUES (5, 540, 38, 16);

