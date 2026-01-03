package com.bouyanzer.restaurant;

import java.util.List;
import java.util.Optional;

/**
 * Interface DAO pour la gestion des restaurants
 * Définit les opérations d’accès aux données
 */
public interface RestaurantDAO {

    /**
     * Récupérer tous les restaurants
     */
    List<Restaurant> getAllRestaurants();

    /**
     * Ajouter un nouveau restaurant
     */
    void addRestaurant(Restaurant restaurant);

    /**
     * Récupérer un restaurant par son id
     */
    Optional<Restaurant> getRestaurantById(Integer id);

    /**
     * Récupérer un restaurant par son district
     */
    Optional<Restaurant> getRestaurantByDistrict(String district);

    /**
     * Supprimer un restaurant
     */
    void deleteRestaurant(Restaurant restaurant);

    /**
     * Mettre à jour les informations d’un restaurant
     */
    void updateRestaurant(Restaurant restaurant);

    /**
     * Récupérer les restaurants par nom
     */
    List<Restaurant> findByName(String name);

    /**
     * Vérifier l’existence d’un restaurant par son id
     */
    boolean existsById(Integer restaurantId);
}

