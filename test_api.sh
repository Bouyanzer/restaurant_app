#!/bin/bash

# Configuration de base
BASE_URL="http://localhost:8080/api/v1"

echo "=========================================="
echo "🚀 DÉBUT DU TEST AUTOMATISÉ DE L'API"
echo "=========================================="
echo ""

# --- CLIENTS ---
echo "👤 [1/6] MODULE CLIENTS"
echo "------------------------------------------"

echo "A. Liste des clients :"
curl -s -i -X GET "$BASE_URL/clients"
echo -e "\n"

echo "B. Récupérer un client par Email :"
curl -s -i -X GET "$BASE_URL/clients/email/client1@test.com"
echo -e "\n"

echo "C. Créer un nouveau client :"
curl -s -i -X POST "$BASE_URL/clients" \
-H "Content-Type: application/json" \
-d '{"firstname": "Nouveau", "lastname": "Client", "email": "new@test.com", "address": "Paris", "phoneNumber": "0600000000"}'
echo -e "\n"

echo "D. Modifier le client 1 (Adresse) :"
curl -s -i -X PUT "$BASE_URL/clients/1" \
-H "Content-Type: application/json" \
-d '{"address": "15 Rue Nouvelle, Paris"}'
echo -e "\n\n"


# --- RESTAURANTS ---
echo "mn [2/6] MODULE RESTAURANTS"
echo "------------------------------------------"

echo "A. Liste des restaurants :"
curl -s -i -X GET "$BASE_URL/restaurants"
echo -e "\n"

echo "B. Chercher par quartier (Nord) :"
curl -s -i -X GET "$BASE_URL/restaurants/district/Nord"
echo -e "\n"

echo "C. Créer un restaurant :"
curl -s -i -X POST "$BASE_URL/restaurants" \
-H "Content-Type: application/json" \
-d '{"name": "Sushi Bar", "description": "Japonais", "address": "Lyon", "district": "Sud", "phoneNumber": "0102030405"}'
echo -e "\n\n"


# --- PLATS ---
echo "🍔 [3/6] MODULE PLATS"
echo "------------------------------------------"

echo "A. Liste de tous les plats :"
curl -s -i -X GET "$BASE_URL/plat"
echo -e "\n"

echo "B. Filtre : Végétariens :"
curl -s -i -X GET "$BASE_URL/plat/type/vegetarian"
echo -e "\n"

echo "C. Filtre : Prix 10-15€ :"
curl -s -i -X GET "$BASE_URL/plat/price-range?minPrice=10&maxPrice=15"
echo -e "\n"

echo "D. Ajouter un plat au restaurant 1 :"
curl -s -i -X POST "$BASE_URL/plat" \
-H "Content-Type: application/json" \
-d '{
  "name": "Soupe Oignon",
  "description": "Traditionnelle",
  "category": "Entree",
  "price": 8.50,
  "isVegetarian": true,
  "restaurant": {"id": 1}
}'
echo -e "\n\n"


# --- COMMANDES ---
echo "📦 [4/6] MODULE COMMANDES"
echo "------------------------------------------"

echo "A. Liste des commandes :"
curl -s -i -X GET "$BASE_URL/commandes"
echo -e "\n"

echo "B. Commandes du Client 1 :"
curl -s -i -X GET "$BASE_URL/commandes/client/1"
echo -e "\n"

echo "C. Créer une commande (Client 1, Plat 3) :"
curl -s -i -X POST "$BASE_URL/commandes" \
-H "Content-Type: application/json" \
-d '{
    "client": {"id": 1},
    "plats": [{"id": 3}],
    "address": "Chez Mohammed",
    "deliveryType": "SHIPPING"
}'
echo -e "\n"

echo "D. Mettre à jour commande 1 (Statut DELIVERED) :"
curl -s -i -X PUT "$BASE_URL/commandes/1" \
-H "Content-Type: application/json" \
-d '{"status": "DELIVERED"}'
echo -e "\n\n"


# --- PAIEMENTS ---
echo "💳 [5/6] MODULE PAIEMENTS"
echo "------------------------------------------"

echo "A. Liste des paiements :"
curl -s -i -X GET "$BASE_URL/paiements"
echo -e "\n"

echo "B. Payer la commande 1 :"
curl -s -i -X POST "$BASE_URL/paiements" \
-H "Content-Type: application/json" \
-d '{
    "commande": {"id": 1},
    "paiementMethod": "CASH"
}'
echo -e "\n\n"


# --- AVIS ---
echo "⭐ [6/6] MODULE AVIS"
echo "------------------------------------------"

echo "A. Liste des avis :"
curl -s -i -X GET "$BASE_URL/aviss"
echo -e "\n"

echo "B. Ajouter un avis sur la commande 1 :"
curl -s -i -X POST "$BASE_URL/aviss" \
-H "Content-Type: application/json" \
-d '{
    "client": {"id": 1},
    "restaurant": {"id": 1},
    "commande": {"id": 1},
    "name": "Super Dej",
    "rating": 4,
    "description": "C était très bon, merci."
}'
echo -e "\n\n"

echo "=========================================="
echo "✅ TESTS TERMINÉS AVEC SUCCÈS"
echo "=========================================="
