package com.bouyanzer.avis;

import com.bouyanzer.client.Client;
import com.bouyanzer.client.ClientDAO;
import com.bouyanzer.exception.NotValidResourceException;
import com.bouyanzer.exception.ResourceNotFoundException;
import com.bouyanzer.plat.Plat;
import com.bouyanzer.commande.Commande;
import com.bouyanzer.commande.CommandeDAO;
import com.bouyanzer.restaurant.Restaurant;
import com.bouyanzer.restaurant.RestaurantDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AvisService {
    private final AvisDAO avisDAO;
    private final ClientDAO clientDAO;
    private final RestaurantDAO restaurantDAO;
    private final CommandeDAO commandeDAO;

    public AvisService(@Qualifier("avis-jpa") AvisDAO avisDAO,
                         ClientDAO clientDAO,
                         RestaurantDAO restaurantDAO,
                         CommandeDAO commandeDAO) {
        this.avisDAO = avisDAO;
        this.clientDAO = clientDAO;
        this.restaurantDAO = restaurantDAO;
        this.commandeDAO = commandeDAO;
    }

    public Avis getById(Integer id) {
        return avisDAO.getById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Avis with id [%s] not found".formatted(id))
                );
    }

    public List<Avis> getAll() {
        return avisDAO.getAll();
    }

    public List<Avis> getAvissByRestaurantId(Integer restaurantId) {
        return avisDAO.getAvissByRestaurantId(restaurantId);
    }

    public List<Avis> getAvissByDate(LocalDateTime date) {
        return avisDAO.getAvissByDate(date);
    }

    public List<Avis> getAvissByClientId(Integer clientId) {
        return avisDAO.getAvissByClientId(clientId);
    }

    public void add(AvisRegistrationRequest avisRegistrationRequest) {
        if (avisRegistrationRequest.client() == null ||
                avisRegistrationRequest.commande() == null ||
                avisRegistrationRequest.restaurant() == null ||
                avisRegistrationRequest.name() == null ||
                avisRegistrationRequest.rating() == null ||
                avisRegistrationRequest.description() == null) {
            throw new NotValidResourceException("Missing data");
        }

        Client client = avisRegistrationRequest.client();
        Commande commande = avisRegistrationRequest.commande();
        Restaurant restaurant = avisRegistrationRequest.restaurant();
        String name = avisRegistrationRequest.name();
        Integer rating = avisRegistrationRequest.rating();
        String description = avisRegistrationRequest.description();


        Integer commandeId = commande.getId();
        if (avisDAO.existsByCommandeId(commandeId)) {
            throw new NotValidResourceException("Avis for commande with id [%s] already exists".formatted(commandeId));
        }


        Integer clientId = client.getId();
        if (!clientDAO.existsById(clientId)) {
            throw new ResourceNotFoundException("Client with id [%s] not found".formatted(clientId));
        }


        Integer restaurantId = restaurant.getId();
        if (!restaurantDAO.existsById(restaurantId)) {
            throw new ResourceNotFoundException("Restaurant with id [%s] not found".formatted(restaurantId));
        }


        if (!commandeDAO.existsById(commandeId)) {
            throw new ResourceNotFoundException("Commande with id [%s] not found".formatted(commandeId));
        }

        Avis avis = new Avis(client, restaurant, commande, name, rating, description);
        avisDAO.add(avis);
    }

    public void update(Integer id, AvisUpdateRequest avisUpdateRequest) {
        Avis avis = avisDAO.getById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Avis with id [%s] not found".formatted(id))
                );

        if (avisUpdateRequest.name() != null) {
            avis.setName(avisUpdateRequest.name());
        }

        if (avisUpdateRequest.rating() != null) {
            avis.setRating(avisUpdateRequest.rating());
        }

        if (avisUpdateRequest.description() != null) {
            avis.setDescription(avisUpdateRequest.description());
        }

        avisDAO.update(avis);
    }

    public void deleteById(Integer id) {
        avisDAO.getById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Avis with id [%s] not found".formatted(id))
                );
    }
}
