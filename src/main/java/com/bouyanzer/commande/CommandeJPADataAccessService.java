package com.bouyanzer.commande;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("commande-jpa")
public class CommandeJPADataAccessService implements CommandeDAO {

    private final CommandeRepository repository;

    public CommandeJPADataAccessService(CommandeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Commande> getById(Integer id) {
        return repository.findByIdWithPlats(id);
    }

    @Override
    public List<Commande> getAll() {
        return repository.findAllWithPlats();
    }

    @Override
    public List<Commande> getCommandesByClientId(Integer clientId) {
        return repository.findByClientIdWithPlats(clientId);
    }

    // --- AJOUTS ---
    @Override
    public List<Commande> getCommandesByRestaurantId(Integer restaurantId) {
        return repository.findByRestaurantIdWithPlats(restaurantId);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }
    // --------------

    @Override
    public void add(Commande commande) {
        repository.save(commande);
    }

    @Override
    public void update(Commande commande) {
        repository.save(commande);
    }
}