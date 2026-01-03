-- Seed minimal pour tester toute l'API (mon_restaurant)
-- Tables: client, restaurant, plat, commandes, commande_plat, paiement, aviss

SET FOREIGN_KEY_CHECKS=0;

-- Vidage des tables dans l'ordre inverse des dépendances
DELETE FROM aviss;
DELETE FROM paiement;
DELETE FROM commande_plat;
DELETE FROM commandes;
DELETE FROM plat;
DELETE FROM restaurant;
DELETE FROM client;

-- Réinitialisation des auto-incréments pour commencer à l'ID 1
ALTER TABLE aviss AUTO_INCREMENT = 1;
ALTER TABLE paiement AUTO_INCREMENT = 1;
ALTER TABLE commandes AUTO_INCREMENT = 1;
ALTER TABLE plat AUTO_INCREMENT = 1;
ALTER TABLE restaurant AUTO_INCREMENT = 1;
ALTER TABLE client AUTO_INCREMENT = 1;

SET FOREIGN_KEY_CHECKS=1;

-- 1. CLIENTS
INSERT INTO client (firstname, lastname, email, address, phone_number) VALUES
('Mohammed', 'Bouyanzer', 'client1@test.com', '10 Rue du Centre, Paris', '+33600000001'),
('Sara',     'Amrani',    'client2@test.com', '22 Avenue du Nord, Paris', '+33600000002');

-- 2. RESTAURANTS
INSERT INTO restaurant (name, description, address, district, phone_number) VALUES
('Le Jardin',  'Cuisine maison',         '10 Rue du Centre, Paris', 'Centre', '+33100000010'),
('Atlas Food', 'Specialites marocaines', '22 Avenue du Nord, Paris', 'Nord',   '+33100000011');

-- 3. PLATS (Note : 0 = Non Végétarien, 1 = Végétarien)
INSERT INTO plat (restaurant_id, name, description, category, price, is_vegetarian) VALUES
(1, 'Burger Maison',     'Burger classique',    'FastFood', 12.90, 0),
(1, 'Salade Verte',      'Salade fraiche',      'Salades',   9.50, 1),
(2, 'Pizza Margherita',  'Tomate mozzarella',   'Pizzas',   11.00, 1),
(2, 'Tajine Poulet',     'Tajine traditionnel', 'Marocain', 14.50, 0);

-- 4. COMMANDES (Note : 0 = Non payé, 1 = Payé)
-- Commande 1 : En attente, non payée
INSERT INTO commandes (client_id, address, delivery_type, commande_time, is_paid, status) VALUES
(1, '10 Rue du Centre, Paris',  'SHIPPING', '2026-01-01 10:00:00', 0, 'PENDING'),
-- Commande 2 : Terminée, payée
(2, '22 Avenue du Nord, Paris', 'PICKUP',   '2026-01-01 10:10:00', 1, 'COMPLETED');

-- 5. COMMANDE_PLAT (Lien entre commandes et plats)
INSERT INTO commande_plat (commande_id, plat_id) VALUES
(1, 1), -- Commande 1 contient Burger
(1, 2), -- Commande 1 contient Salade
(2, 3), -- Commande 2 contient Pizza
(2, 4); -- Commande 2 contient Tajine

-- 6. PAIEMENT (Uniquement pour la commande 2 qui est payée)
INSERT INTO paiement (commande_id, paiement_method, paiement_status, paiement_time) VALUES
(2, 'CREDIT_CARD', 'PAID', '2026-01-01 10:12:00');

-- 7. AVIS (1 avis laissé par le client 2 sur la commande 2 pour le restaurant 2)
INSERT INTO aviss (client_id, restaurant_id, commande_id, name, rating, description, avis_time) VALUES
(2, 2, 2, 'Avis commande 2', 5, 'Tres bon, je recommande.', '2026-01-01 11:00:00');

-- Résumé pour vérification
SELECT 'client' AS t, COUNT(*) AS n FROM client
UNION ALL SELECT 'restaurant', COUNT(*) FROM restaurant
UNION ALL SELECT 'plat', COUNT(*) FROM plat
UNION ALL SELECT 'commandes', COUNT(*) FROM commandes
UNION ALL SELECT 'commande_plat', COUNT(*) FROM commande_plat
UNION ALL SELECT 'paiement', COUNT(*) FROM paiement
UNION ALL SELECT 'aviss', COUNT(*) FROM aviss;