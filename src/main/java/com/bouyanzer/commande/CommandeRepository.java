package com.bouyanzer.commande;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {

    //  IMPORTANT : FETCH des plats pour éviter le LazyInitializationException
    @Query("SELECT DISTINCT c FROM Commande c LEFT JOIN FETCH c.plats")
    List<Commande> findAllWithPlats();

    @Query("SELECT DISTINCT c FROM Commande c LEFT JOIN FETCH c.plats WHERE c.id = :id")
    Optional<Commande> findByIdWithPlats(@Param("id") Integer id);

    @Query("SELECT DISTINCT c FROM Commande c LEFT JOIN FETCH c.plats WHERE c.client.id = :clientId")
    List<Commande> findByClientIdWithPlats(@Param("clientId") Integer clientId);

    // --- AJOUT ---
    // Récupère les commandes contenant des plats d'un restaurant spécifique
    @Query("SELECT DISTINCT c FROM Commande c LEFT JOIN FETCH c.plats p WHERE p.restaurant.id = :restaurantId")
    List<Commande> findByRestaurantIdWithPlats(@Param("restaurantId") Integer restaurantId);
}