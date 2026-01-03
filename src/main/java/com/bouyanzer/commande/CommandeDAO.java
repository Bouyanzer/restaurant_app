package com.bouyanzer.commande;

import java.util.List;
import java.util.Optional;

public interface CommandeDAO {
    Optional<Commande> getById(Integer id);
    List<Commande> getAll();
    List<Commande> getCommandesByClientId(Integer clientId);
    
    // --- AJOUTS ---
    List<Commande> getCommandesByRestaurantId(Integer restaurantId);
    boolean existsById(Integer id);
    // --------------

    void add(Commande commande);
    void update(Commande commande);
}