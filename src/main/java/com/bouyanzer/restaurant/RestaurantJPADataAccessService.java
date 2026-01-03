package com.bouyanzer.restaurant;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implémentation JPA du DAO Restaurant
 * Utilise RestaurantRepository pour accéder à la base de données
 */
@Repository("restaurant-jpa")
public class RestaurantJPADataAccessService implements RestaurantDAO {

    // Repository Spring Data JPA pour l’entité Restaurant
    private final RestaurantRepository restaurantRepository;

    // Injection du repository via le constructeur
    public RestaurantJPADataAccessService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Récupérer tous les restaurants
     */
    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    /**
     * Ajouter un nouveau restaurant
     */
    @Override
    public void addRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    /**
     * Récupérer un restaurant par son id
     */
    @Override
    public Optional<Restaurant> getRestaurantById(Integer id) {
        return restaurantRepository.findById(id);
    }

    /**
     * Récupérer un restaurant par son district
     */
    @Override
    public Optional<Restaurant> getRestaurantByDistrict(String district) {
        return restaurantRepository.findByDistrict(district);
    }

    /**
     * Supprimer un restaurant
     */
    @Override
    public void deleteRestaurant(Restaurant restaurant) {
        restaurantRepository.delete(restaurant);
    }

    /**
     * Mettre à jour un restaurant existant
     */
    @Override
    public void updateRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    /**
     * Récupérer les restaurants par nom
     */
    @Override
    public List<Restaurant> findByName(String name) {
        return restaurantRepository.findByName(name);
    }

    /**
     * Vérifier l’existence d’un restaurant par son id
     */
    @Override
    public boolean existsById(Integer restaurantId) {
        return restaurantRepository.existsById(restaurantId);
    }
}

