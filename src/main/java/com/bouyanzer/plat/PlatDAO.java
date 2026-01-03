package com.bouyanzer.plat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Interface DAO pour la gestion des plats
 * Définit les opérations d’accès aux données
 */
public interface PlatDAO {

    /**
     * Récupérer tous les plats
     */
    List<Plat> getAllPlat();

    /**
     * Récupérer un plat par son id
     */
    Optional<Plat> getPlatById(Integer id);

    /**
     * Récupérer les plats par catégorie
     */
    List<Plat> getPlatByCategory(String category);

    /**
     * Récupérer uniquement les plats végétariens
     */
    List<Plat> getVegetarianPlat();

    /**
     * Ajouter un nouveau plat
     */
    void addPlat(Plat plat);

    /**
     * Mettre à jour un plat existant
     */
    void updatePlat(Plat plat);

    /**
     * Supprimer un plat
     */
    void deletePlat(Plat plat);

    /**
     * Récupérer les plats d’un restaurant (méthode repository-like)
     */
    List<Plat> findByRestaurantID(Integer restaurantID);

    /**
     * Récupérer les plats dans une fourchette de prix (méthode repository-like)
     */
    List<Plat> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Récupérer les plats par nom (méthode repository-like)
     */
    List<Plat> findByName(String name);

    /**
     * Récupérer les plats d’un restaurant
     */
    List<Plat> getPlatByRestaurantID(Integer restaurantID);

    /**
     * Récupérer les plats dans une fourchette de prix
     */
    List<Plat> getPlatByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Récupérer les plats par nom
     */
    List<Plat> getPlatByName(String name);

    /**
     * Récupérer un plat par son id (méthode générique)
     */
    Optional<Plat> getById(Integer platId);
}

