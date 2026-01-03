package com.bouyanzer.commande;

import com.bouyanzer.client.Client;
import com.bouyanzer.client.ClientDAO;
import com.bouyanzer.exception.NotValidResourceException;
import com.bouyanzer.exception.ResourceNotFoundException;
import com.bouyanzer.plat.Plat;
import com.bouyanzer.plat.PlatDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommandeService {
    private final CommandeDAO commandeDAO;
    private final PlatDAO platDAO;
    private final ClientDAO clientDAO;


    public CommandeService(@Qualifier("commande-jpa") CommandeDAO commandeDAO, PlatDAO platDAO, ClientDAO clientDAO) {
        this.commandeDAO = commandeDAO;
        this.platDAO = platDAO;
        this.clientDAO = clientDAO;
    }

    public Commande getById(Integer id) {
        return commandeDAO.getById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Commande with id [%s] not found".formatted(id))
                );
    }

    public List<Commande> getAll() {
        return commandeDAO.getAll();
    }

    public List<Commande> getCommandesByClientId(Integer clientId) {
        return commandeDAO.getCommandesByClientId(clientId);
    }

    public List<Commande> getCommandesByRestaurantId(Integer restaurantId) {
        return commandeDAO.getCommandesByRestaurantId(restaurantId);
    }

    public void add(CommandeRegistrationRequest commandeRegistrationRequest) {
        if (commandeRegistrationRequest.client() == null || commandeRegistrationRequest.plats() == null ||
                commandeRegistrationRequest.address() == null || commandeRegistrationRequest.deliveryType() == null) {
            throw new NotValidResourceException("Missing data");
        }

        Integer clientId = commandeRegistrationRequest.client().getId();
        Client client = clientDAO.getById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client with id [%s] not found".formatted(clientId)));

        List<Plat> plats = new ArrayList<>();
        for (Plat plat : commandeRegistrationRequest.plats()) {
            Integer platId = plat.getId();
            Plat platFromDb = platDAO.getById(platId)
                    .orElseThrow(() -> new ResourceNotFoundException("Plat with id [%s] not found".formatted(platId)));
            plats.add(platFromDb);
        }

        var address = commandeRegistrationRequest.address();
        var deliveryType = commandeRegistrationRequest.deliveryType();

        Commande commande = new Commande(client, plats, address, deliveryType);
        commandeDAO.add(commande);
    }


    public void updateCommande(Integer id, CommandeUpdateRequest commandeUpdateRequest) {
        Commande commande = getById(id);

        if (commandeUpdateRequest.plats() != null) {
            commande.setPlats(commandeUpdateRequest.plats());
        }

        if (commandeUpdateRequest.address() != null) {
            commande.setAddress(commandeUpdateRequest.address());
        }

        if (commandeUpdateRequest.deliveryType() != null) {
            commande.setDeliveryType(commandeUpdateRequest.deliveryType());
        }

        if (commandeUpdateRequest.status() != null) {
            commande.setStatus(commandeUpdateRequest.status());
        }

        commandeDAO.update(commande);
    }
}
