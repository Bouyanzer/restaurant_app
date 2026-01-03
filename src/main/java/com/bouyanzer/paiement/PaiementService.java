package com.bouyanzer.paiement;

import com.bouyanzer.exception.NotValidResourceException;
import com.bouyanzer.exception.ResourceNotFoundException;
import com.bouyanzer.commande.CommandeDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class PaiementService {
    private final PaiementDAO paiementDAO;
    private final CommandeDAO commandeDAO;

    public PaiementService(
            @Qualifier("commande-jpa") CommandeDAO commandeDAO,
            @Qualifier("paiement-jpa") PaiementDAO paiementDAO
    ) {
        this.paiementDAO = paiementDAO;
        this.commandeDAO = commandeDAO;
    }

    public Paiement getById(Integer id) {
        return paiementDAO.getById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Commande with id [%s] not found".formatted(id))
                );
    }

    public List<Paiement> getAll() {
        return paiementDAO.getAll();
    }

    public void add(@NotNull @Valid PaiementRegistrationRequest paiementRegistrationRequest) {
        if (paiementRegistrationRequest.commande() == null || paiementRegistrationRequest.commande().getId() == null) {
            throw new NotValidResourceException("Missing data");
        }

        var commandeId = paiementRegistrationRequest.commande().getId();
        var commande = commandeDAO.getById(commandeId).orElseThrow(() ->
                new ResourceNotFoundException("Commande with id [%s] not found".formatted(commandeId)));

        // Sprawdź czy płatność dla zamówienia już istnieje
        if (paiementDAO.existsByCommandeId(commandeId)) {
            throw new NotValidResourceException("Paiement for commande with id [%s] already exists".formatted(commandeId));
        }

        // Update commande status
        commande.setPaid(true);
        commandeDAO.update(commande);

        // Add paiement record
        Paiement paiement = new Paiement(commande, paiementRegistrationRequest.paiementMethod());
        paiementDAO.add(paiement);
    }

    public void updatePaiement(Integer id, @NotNull @Valid PaiementUpdateRequest paiementUpdateRequest) {
        var paiement = paiementDAO.getById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Paiement with id [%s] not found".formatted(id))
                );

        if (paiementUpdateRequest.paiementStatus() != null) {
            paiement.setPaiementStatus(paiementUpdateRequest.paiementStatus());
        }

        paiementDAO.update(paiement);
    }
}
