package com.bouyanzer.paiement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Integer> {

    @Query("SELECT p FROM Paiement p JOIN FETCH p.commande c LEFT JOIN FETCH c.plats")
    List<Paiement> findAllWithCommandesAndPlats();

    boolean existsByCommandeId(Integer commandeId);
}