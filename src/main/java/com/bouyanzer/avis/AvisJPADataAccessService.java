package com.bouyanzer.avis;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository("avis-jpa")
public class AvisJPADataAccessService implements AvisDAO {

    private final AvisRepository avisRepository;

    public AvisJPADataAccessService(AvisRepository avisRepository) {
        this.avisRepository = avisRepository;
    }

    @Override
    public List<Avis> getAll() {
        // MODIFICATION ICI : On appelle la méthode avec JOIN FETCH
        return avisRepository.findAllWithDetails();
    }

    // ... Le reste du fichier ne change pas ...
    
    @Override
    public List<Avis> getAvissByRestaurantId(Integer restaurantId) {
        // Tu peux aussi utiliser la version optimisée ici si tu veux
        return avisRepository.findByRestaurantIdWithDetails(restaurantId); 
    }

    @Override
    public List<Avis> getAvissByDate(LocalDateTime date) {
        return avisRepository.findByAvisTime(date);
    }

    @Override
    public List<Avis> getAvissByClientId(Integer clientId) {
        return avisRepository.findByClientId(clientId);
    }

    @Override
    public void add(Avis avis) {
        avisRepository.save(avis);
    }

    @Override
    public void update(Avis avis) {
        avisRepository.save(avis);
    }

    @Override
    public Optional<Avis> getById(Integer id) {
        return avisRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        avisRepository.deleteById(id);
    }

    @Override
    public boolean existsByCommandeId(Integer commandeId) {
        return avisRepository.existsByCommandeId(commandeId);
    }
}