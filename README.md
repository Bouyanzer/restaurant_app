# Restaurant_app
# DOCUMENTATION TECHNIQUE - FOOD DELIVERY API


## 1. Contexte du Projet

Ce projet, **FoodDelivery API**, est une application web dont l'objectif est de fournir une API RESTful pour une plateforme de commande et de livraison de repas en ligne.
L'application vise à formaliser les échanges entre restaurateurs et clients en permettant de consulter des cartes de restaurants, de composer des commandes (paniers), de gérer le processus de paiement et de laisser des avis sur la qualité du service.

## 2. Architecture Technique et Stack

L'application est conçue selon une architecture modulaire et conteneurisée.

* **Backend/API RESTful :** Développé en Java/Spring Boot.
* **Base de Données :** PostgreSQL (PSQL).
* **ORM :** Utilisation d'Hibernate/JPA via Spring Data JPA.
* **Conteneurisation :** L'ensemble des services est conteneurisé avec Docker et orchestré via Docker Compose.

---

## 3. Description Structurée des Entités (Modèle de Données)

Cette section détaille les entités principales, leurs attributs et leurs relations.

### 1. Entité : CLIENT
| Attribut | Type | Rôle | Description |
| :--- | :--- | :--- | :--- |
| **id_client** | INT | Clé Primaire (CP) | Identifiant unique du client. |
| prenom_client | VARCHAR | Attribut | Prénom de l'utilisateur. |
| nom_client | VARCHAR | Attribut | Nom de famille de l'utilisateur. |
| email_client | VARCHAR | Attribut | Adresse email (utilisée pour la connexion). |
| adresse_client | VARCHAR | Attribut | Adresse de résidence par défaut. |
| telephone_client | VARCHAR | Attribut | Numéro de téléphone de contact. |

**Relations :**
* **COMMANDE** : Relation 1,N (Un CLIENT passe plusieurs COMMANDEs).
* **AVIS** : Relation 0,N (Un CLIENT donne plusieurs AVIS).

### 2. Entité : RESTAURANT
| Attribut | Type | Rôle | Description |
| :--- | :--- | :--- | :--- |
| **id_restaurant** | INT | Clé Primaire (CP) | Identifiant unique du restaurant. |
| nom_restaurant | VARCHAR | Attribut | Nom commercial de l'établissement. |
| description_restaurant | TEXT | Attribut | Brève description (type de cuisine, ambiance). |
| adresse_restaurant | VARCHAR | Attribut | Adresse physique du restaurant. |
| quartier_restaurant | VARCHAR | Attribut | Quartier ou zone de livraison. |
| telephone_restaurant | VARCHAR | Attribut | Numéro de téléphone du restaurant. |

**Relations :**
* **PLAT** : Relation 1,N (Un RESTAURANT propose plusieurs PLATs).
* **AVIS** : Relation 0,N (Un RESTAURANT reçoit plusieurs AVIS).

### 3. Entité : PLAT (Food)
| Attribut | Type | Rôle | Description |
| :--- | :--- | :--- | :--- |
| **id_plat** | INT | Clé Primaire (CP) | Identifiant unique du plat. |
| **id_restaurant** | INT | Clé Étrangère (CE) | Référence au RESTAURANT qui propose ce plat. |
| nom_plat | VARCHAR | Attribut | Nom du plat. |
| description_plat | TEXT | Attribut | Description des ingrédients/préparation. |
| categorie_plat | VARCHAR | Attribut | Catégorie (ex: Pizza, Pâtes, Dessert). |
| prix_plat | DECIMAL | Attribut | Prix unitaire du plat. |
| est_vegetarien | BOOLEAN | Attribut | Indicateur si le plat est végétarien. |

**Relations :**
* **RESTAURANT** : Relation N,1 (Un PLAT est proposé par un seul RESTAURANT).
* **LIGNE_COMMANDE** : Relation N,N avec COMMANDE.

### 4. Entité : COMMANDE (Orders)
| Attribut | Type | Rôle | Description |
| :--- | :--- | :--- | :--- |
| **id_commande** | INT | Clé Primaire (CP) | Identifiant unique de la commande. |
| **id_client** | INT | Clé Étrangère (CE) | Référence au CLIENT qui a passé la commande. |
| adresse_livraison | VARCHAR | Attribut | Adresse de livraison spécifique. |
| type_livraison | VARCHAR | Attribut | Type de service (ex: SHIPPING, PICKUP). |

**Relations :**
* **CLIENT** : Relation N,1 (Une COMMANDE est passée par un seul CLIENT).
* **LIGNE_COMMANDE** : Relation N,N avec PLAT.
* **PAIEMENT** : Relation 1,1 (Une COMMANDE est liée à un seul PAIEMENT).
* **AVIS** : Relation 0,1 (Une COMMANDE peut avoir un seul AVIS).

### 5. Entité : PAIEMENT
| Attribut | Type | Rôle | Description |
| :--- | :--- | :--- | :--- |
| **id_commande** | INT | CP & CE | Référence à la COMMANDE (Assure l'unicité 1,1). |
| methode_paiement | VARCHAR | Attribut | Méthode utilisée (ex: CASH, CARD). |

**Relations :**
* **COMMANDE** : Relation 1,1 (Un PAIEMENT est lié à une seule COMMANDE).

### 6. Entité : AVIS (Reviews)
| Attribut | Type | Rôle | Description |
| :--- | :--- | :--- | :--- |
| **id_avis** | INT | Clé Primaire (CP) | Identifiant unique de l'avis. |
| **id_client** | INT | Clé Étrangère (CE) | Auteur de l'avis. |
| **id_restaurant** | INT | Clé Étrangère (CE) | Restaurant évalué. |
| **id_commande** | INT | Clé Étrangère (CE) | Commande concernée. |
| titre_avis | VARCHAR | Attribut | Titre de l'avis. |
| note_avis | INT | Attribut | Note donnée (ex: sur 5). |
| description_avis | TEXT | Attribut | Contenu du commentaire. |

**Relations :**
* **COMMANDE** : Relation 1,1 (Un AVIS concerne une seule COMMANDE).

### 7. Table d'Association : LIGNE_COMMANDE
| Attribut | Type | Rôle | Description |
| :--- | :--- | :--- | :--- |
| **id_commande** | INT | CP & CE | Référence à la COMMANDE. |
| **id_plat** | INT | CP & CE | Référence au PLAT. |
| quantite | INT | Attribut | Nombre d'unités commandées pour ce plat. |

---

## 4. Documentation des Routes API REST

### Gestion des Clients (`/api/v1/customers`)
| Méthode | Route | Description | Explication |
| :--- | :--- | :--- | :--- |
| GET | `/api/v1/customers` | Liste tous les clients. | CRUD (Lecture) |
| GET | `/api/v1/customers/{id}` | Client par ID. | Lecture par ID |
| GET | `/api/v1/customers/email/{email}` | Client par Email. | Lecture par attribut unique |
| POST | `/api/v1/customers` | Créer un client. | CRUD (Création) |
| PUT | `/api/v1/customers/{id}` | Modifier un client. | CRUD (Mise à jour) |
| DELETE | `/api/v1/customers/{id}` | Supprimer un client. | CRUD (Suppression) |

### Gestion des Restaurants (`/api/v1/restaurants`)
| Méthode | Route | Description | Explication |
| :--- | :--- | :--- | :--- |
| GET | `/api/v1/restaurants` | Liste tous les restaurants. | CRUD (Lecture) |
| GET | `/api/v1/restaurants/{id}` | Restaurant par ID. | Lecture par ID |
| GET | `/api/v1/restaurants/district/{district}` | Filtrer par quartier. | Requête filtrée |
| GET | `/api/v1/restaurants/name/{name}` | Filtrer par nom. | Requête filtrée |
| POST | `/api/v1/restaurants` | Créer un restaurant. | CRUD (Création) |
| PUT | `/api/v1/restaurants/{id}` | Modifier un restaurant. | CRUD (Mise à jour) |
| DELETE | `/api/v1/restaurants/{id}` | Supprimer un restaurant. | CRUD (Suppression) |

### Gestion des Plats (`/api/v1/food`)
| Méthode | Route | Description | Explication |
| :--- | :--- | :--- | :--- |
| GET | `/api/v1/food` | Liste tous les plats. | CRUD (Lecture) |
| GET | `/api/v1/food/{id}` | Plat par ID. | Lecture par ID |
| GET | `/api/v1/food/category/{category}` | Filtrer par catégorie. | Requête filtrée |
| GET | `/api/v1/food/restaurant/{restaurant_id}` | Plats d'un restaurant. | Relation 1,N |
| GET | `/api/v1/food/price-range` | Plats par fourchette de prix. | Requête filtrée |
| POST | `/api/v1/food` | Créer un plat. | CRUD (Création) |
| PUT | `/api/v1/food/{id}` | Modifier un plat. | CRUD (Mise à jour) |
| DELETE | `/api/v1/food/{id}` | Supprimer un plat. | CRUD (Suppression) |

### Gestion des Commandes (`/api/v1/orders`)
| Méthode | Route | Description | Explication |
| :--- | :--- | :--- | :--- |
| GET | `/api/v1/orders` | Liste toutes les commandes. | CRUD (Lecture) |
| GET | `/api/v1/orders/{id}` | Commande par ID. | Lecture par ID |
| GET | `/api/v1/orders/customer/{customerId}` | Commandes d'un client. | Relation 1,N |
| POST | `/api/v1/orders` | Créer une commande. | CRUD (Création) |
| PUT | `/api/v1/orders/{id}` | Mettre à jour une commande. | CRUD (Mise à jour) |

### Gestion des Paiements (`/api/v1/payments`)
| Méthode | Route | Description | Explication |
| :--- | :--- | :--- | :--- |
| GET | `/api/v1/payments` | Liste tous les paiements. | CRUD (Lecture) |
| GET | `/api/v1/payments/{id}` | Paiement par ID. | Lecture par ID |
| POST | `/api/v1/payments` | Effectuer un paiement. | CRUD (Création) |
| PUT | `/api/v1/payments/{id}` | Mettre à jour un paiement. | CRUD (Mise à jour) |

### Gestion des Avis (`/api/v1/reviews`)
| Méthode | Route | Description | Explication |
| :--- | :--- | :--- | :--- |
| GET | `/api/v1/reviews` | Liste tous les avis. | CRUD (Lecture) |
| GET | `/api/v1/reviews/restaurant/{id}` | Avis sur un restaurant. | Relation 1,N |
| GET | `/api/v1/reviews/user/{customerId}` | Avis d'un client. | Relation 1,N |
| POST | `/api/v1/reviews` | Laisser un avis. | CRUD (Création) |
| DELETE | `/api/v1/reviews/{id}` | Supprimer un avis. | CRUD (Suppression) |

## 5. Diagramme : 
### Diagramme de classe
![WhatsApp Image 2025-11-23 à 11 28 03_b2b8c043](https://github.com/user-attachments/assets/41bb66bc-d61c-4587-960d-764922774c8a)
### Diagramme Merise
<img width="509" height="385" alt="Entite_Asso_Diag" src="https://github.com/user-attachments/assets/b2f6e244-82d7-4089-a525-ab7acd3b35df" />


