package com.bouyanzer.avis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AvisRepository extends JpaRepository<Avis, Integer> {

    // --- LA CORRECTION EST ICI ---
    // On charge l'Avis + sa Commande + les Plats de la commande
    @Query("SELECT a FROM Avis a JOIN FETCH a.commande c LEFT JOIN FETCH c.plats")
    List<Avis> findAllWithDetails();
    // -----------------------------

    // Tu peux aussi optimiser les autres si besoin :
    @Query("SELECT a FROM Avis a JOIN FETCH a.commande c LEFT JOIN FETCH c.plats WHERE a.restaurant.id = :restaurantId")
    List<Avis> findByRestaurantIdWithDetails(@Param("restaurantId") Integer restaurantId);

    List<Avis> findByAvisTime(LocalDateTime date);
    List<Avis> findByRestaurantId(Integer restaurantId);
    List<Avis> findByClientId(Integer clientId);
    boolean existsByCommandeId(Integer commandeId);
}