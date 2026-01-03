package com.bouyanzer.avis;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Interface DAO pour la gestion des avis
 * Définit les opérations d’accès aux données
 */
public interface AvisDAO {

    /**
     * Récupérer tous les avis
     */
    List<Avis> getAll();

    /**
     * Récupérer les avis d’un restaurant spécifique
     */
    List<Avis> getAvissByRestaurantId(Integer restaurantId);

    /**
     * Récupérer les avis par date
     */
    List<Avis> getAvissByDate(LocalDateTime date);

    /**
     * Récupérer les avis d’un client spécifique
     */
    List<Avis> getAvissByClientId(Integer clientId);

    /**
     * Ajouter un nouvel avis
     */
    void add(Avis avis);

    /**
     * Mettre à jour un avis existant
     */
    void update(Avis avis);

    /**
     * Récupérer un avis par son identifiant
     */
    Optional<Avis> getById(Integer id);

    /**
     * Supprimer un avis par son identifiant
     */
    void deleteById(Integer id);

    /**
     * Vérifier si un avis existe déjà pour une commande donnée
     */
    boolean existsByCommandeId(Integer commandeId);
}

