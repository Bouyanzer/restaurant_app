# restaurant_app
# DOCUMENTATION TECHNIQUE - FOOD DELIVERY API

## 1. Contexte du Projet

Ce projet, **FoodDelivery API**, est une application web dont l'objectif est de fournir une API RESTful pour une plateforme de commande et de livraison de repas en ligne.
L'application vise à formaliser les échanges entre restaurateurs et clients.

## 2. Architecture Technique et Stack

* Backend : Java / Spring Boot  
* Base de Données : PostgreSQL  
* ORM : Hibernate / JPA  
* Conteneurisation : Docker + Docker Compose  

---

## 3. Description des Entités

### 1. CLIENT
| Attribut | Type | Description |
|---------|------|-------------|
| id_client | INT | Identifiant |
| prenom_client | VARCHAR | Prénom |
| nom_client | VARCHAR | Nom |
| email_client | VARCHAR | Email |
| adresse_client | VARCHAR | Adresse |
| telephone_client | VARCHAR | Téléphone |

Relations : 1,N → commandes ; 0,N → avis  

---

### 2. RESTAURANT
| Attribut | Type | Description |
| id_restaurant | INT | Identifiant |
| nom_restaurant | VARCHAR | Nom |
| description_restaurant | TEXT | Description |
| adresse_restaurant | VARCHAR | Adresse |
| quartier_restaurant | VARCHAR | Quartier |
| telephone_restaurant | VARCHAR | Téléphone |

Relations : 1,N → plats ; 0,N → avis  

---

### 3. PLAT
| Attribut | Type | Description |
| id_plat | INT | Identifiant |
| id_restaurant | INT | Restaurant |
| nom_plat | VARCHAR | Nom |
| description_plat | TEXT | Description |
| categorie_plat | VARCHAR | Catégorie |
| prix_plat | DECIMAL | Prix |
| est_vegetarien | BOOLEAN | Végétarien |

---

### 4. COMMANDE
| Attribut | Type | Description |
| id_commande | INT | Identifiant |
| id_client | INT | Client |
| adresse_livraison | VARCHAR | Adresse livraison |
| type_livraison | VARCHAR | SHIPPING ou PICKUP |

---

### 5. PAIEMENT
| Attribut | Type | Description |
| id_commande | INT | Référence commande |
| methode_paiement | VARCHAR | CASH ou CARD |

---

### 6. AVIS
| Attribut | Type | Description |
| id_avis | INT | Identifiant |
| id_client | INT | Client |
| id_restaurant | INT | Restaurant |
| id_commande | INT | Commande |
| note_avis | INT | Note |
| description_avis | TEXT | Commentaire |

---

## 4. Routes API REST

### Clients – `/api/v1/customers`
| Méthode | Route |
| GET | /api/v1/customers |
| GET | /api/v1/customers/{id} |
| GET | /api/v1/customers/email/{email} |
| POST | /api/v1/customers |
| PUT | /api/v1/customers/{id} |
| DELETE | /api/v1/customers/{id} |

---

### Restaurants – `/api/v1/restaurants`
| GET | /api/v1/restaurants |
| GET | /api/v1/restaurants/{id} |
| GET | /api/v1/restaurants/district/{district} |
| GET | /api/v1/restaurants/name/{name} |
| POST | /api/v1/restaurants |
| PUT | /api/v1/restaurants/{id} |
| DELETE | /api/v1/restaurants/{id} |

---

### Plats – `/api/v1/food`
| GET | /api/v1/food |
| GET | /api/v1/food/{id} |
| GET | /api/v1/food/category/{category} |
| GET | /api/v1/food/restaurant/{restaurant_id} |
| GET | /api/v1/food/price-range |
| POST | /api/v1/food |
| PUT | /api/v1/food/{id} |
| DELETE | /api/v1/food/{id} |

---

### Commandes – `/api/v1/orders`
| GET | /api/v1/orders |
| GET | /api/v1/orders/{id} |
| GET | /api/v1/orders/customer/{customerId} |
| POST | /api/v1/orders |
| PUT | /api/v1/orders/{id} |

---

### Paiements – `/api/v1/payments`
| GET | /api/v1/payments |
| GET | /api/v1/payments/{id} |
| POST | /api/v1/payments |
| PUT | /api/v1/payments/{id} |

---

### Avis – `/api/v1/reviews`
| GET | /api/v1/reviews |
| GET | /api/v1/reviews/restaurant/{id} |
| GET | /api/v1/reviews/user/{customerId} |
| POST | /api/v1/reviews |
| DELETE | /api/v1/reviews/{id} |
