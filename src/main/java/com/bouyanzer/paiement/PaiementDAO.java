package com.bouyanzer.paiement;

import java.util.List;
import java.util.Optional;

/**
 * Interface DAO pour la gestion des paiements
 * Définit les opérations d’accès aux données
 */
public interface PaiementDAO {

    /**
     * Récupérer tous les paiements
     */
    List<Paiement> getAll();

    /**
     * Ajouter un nouveau paiement
     */
    void add(Paiement paiement);

    /**
     * Récupérer un paiement par son id
     */
    Optional<Paiement> getById(Integer id);

    /**
     * Mettre à jour un paiement existant
     */
    void update(Paiement paiement);

    /**
     * Vérifier s’il existe déjà un paiement pour une commande donnée
     */
    boolean existsByCommandeId(Integer commandeId);
}

