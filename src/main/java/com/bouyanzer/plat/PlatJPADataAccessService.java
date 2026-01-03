package com.bouyanzer.plat;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implémentation JPA du DAO Plat
 * Utilise PlatRepository pour accéder à la base de données
 */
@Repository("plat-jpa")
public class PlatJPADataAccessService implements PlatDAO {

    // Repository Spring Data JPA pour l’entité Plat
    private final PlatRepository platRepository;

    // Injection du repository via le constructeur
    public PlatJPADataAccessService(PlatRepository platRepository) {
        this.platRepository = platRepository;
    }

    /**
     * Récupérer tous les plats
     */
    @Override
    public List<Plat> getAllPlat() {
        return platRepository.findAll();
    }

    /**
     * Récupérer un plat par son id
     */
    @Override
    public Optional<Plat> getPlatById(Integer id) {
        return platRepository.findById(id);
    }

    /**
     * Récupérer les plats par catégorie
     */
    @Override
    public List<Plat> getPlatByCategory(String category) {
        return platRepository.findByCategory(category);
    }

    /**
     * Récupérer uniquement les plats végétariens
     * (filtrage en mémoire)
     */
    @Override
    public List<Plat> getVegetarianPlat() {
        return platRepository.findAll().stream()
                .filter(Plat::isVegetarian)
                .collect(Collectors.toList());
    }

    /**
     * Ajouter un nouveau plat
     */
    @Override
    public void addPlat(Plat plat) {
        platRepository.save(plat);
    }

    /**
     * Mettre à jour un plat existant
     */
    @Override
    public void updatePlat(Plat plat) {
        platRepository.save(plat);
    }

    /**
     * Supprimer un plat
     */
    @Override
    public void deletePlat(Plat plat) {
        platRepository.delete(plat);
    }

    /**
     * Récupérer les plats d’un restaurant (méthode repository-like)
     */
    @Override
    public List<Plat> findByRestaurantID(Integer restaurantID) {
        return platRepository.findByRestaurantId(restaurantID);
    }

    /**
     * Récupérer les plats dans une fourchette de prix (méthode repository-like)
     */
    @Override
    public List<Plat> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return platRepository.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Récupérer les plats par nom (méthode repository-like)
     */
    @Override
    public List<Plat> findByName(String name) {
        return platRepository.findByName(name);
    }

    /**
     * Récupérer les plats d’un restaurant
     */
    @Override
    public List<Plat> getPlatByRestaurantID(Integer restaurantID) {
        return platRepository.findByRestaurantId(restaurantID);
    }

    /**
     * Récupérer les plats dans une fourchette de prix
     */
    @Override
    public List<Plat> getPlatByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return platRepository.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Récupérer les plats par nom
     */
    @Override
    public List<Plat> getPlatByName(String name) {
        return platRepository.findByName(name);
    }

    /**
     * Récupérer un plat par son id (méthode générique)
     */
    @Override
    public Optional<Plat> getById(Integer platId) {
        return platRepository.findById(platId);
    }
}

