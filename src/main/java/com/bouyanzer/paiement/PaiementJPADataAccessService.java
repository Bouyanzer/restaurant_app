package com.bouyanzer.paiement;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implémentation JPA du DAO Paiement
 * Utilise PaiementRepository pour accéder à la base de données
 */
@Repository("paiement-jpa")
public class PaiementJPADataAccessService implements PaiementDAO {

    // Repository Spring Data JPA pour l’entité Paiement
    private final PaiementRepository paiementRepository;

    // Injection du repository via le constructeur
    public PaiementJPADataAccessService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    /**
     * Récupérer tous les paiements
     * MODIFICATION : Utilise la requête avec FETCH pour charger la commande et ses plats
     */
    @Override
    public List<Paiement> getAll() {
        // C'est ici que la magie opère pour remplir la liste des plats dans le JSON
        return paiementRepository.findAllWithCommandesAndPlats();
    }

    /**
     * Ajouter un nouveau paiement
     */
    @Override
    public void add(Paiement paiement) {
        paiementRepository.save(paiement);
    }

    /**
     * Récupérer un paiement par son id
     */
    @Override
    public Optional<Paiement> getById(Integer id) {
        return paiementRepository.findById(id);
    }

    /**
     * Mettre à jour un paiement existant
     */
    @Override
    public void update(Paiement paiement) {
        paiementRepository.save(paiement);
    }

    /**
     * Vérifier s’il existe déjà un paiement pour une commande donnée
     */
    @Override
    public boolean existsByCommandeId(Integer commandeId) {
        return paiementRepository.existsByCommandeId(commandeId);
    }
}