package com.bouyanzer.paiement;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller REST pour la gestion des paiements
 */
@RestController
@RequestMapping("api/v1/paiements") // URL de base pour les paiements
@Validated
public class PaiementController {

    // Service contenant la logique métier liée aux paiements
    private final PaiementService paiementService;

    // Injection du service via le constructeur
    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    /**
     * Récupérer la liste de tous les paiements
     */
    @GetMapping
    public List<Paiement> getPaiements() {
        return paiementService.getAll();
    }

    /**
     * Récupérer un paiement par son identifiant
     */
    @GetMapping("{id}")
    public Paiement getPaiement(@PathVariable("id") Integer id) {
        return paiementService.getById(id);
    }

    /**
     * Créer un nouveau paiement
     * @Valid déclenche la validation des champs du DTO
     */
    @PostMapping
    public void addPaiement(
            @RequestBody @Valid PaiementRegistrationRequest request) {
        paiementService.add(request);
    }

    /**
     * Mettre à jour un paiement existant
     */
    @PutMapping("{id}")
    public void updatePaiement(
            @PathVariable("id") Integer id,
            @RequestBody @Valid PaiementUpdateRequest request) {
        paiementService.updatePaiement(id, request);
    }
}

