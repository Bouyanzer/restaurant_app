package com.bouyanzer.commande;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour la gestion des commandes
 */
@RestController
@RequestMapping("api/v1/commandes") // URL de base pour les commandes
public class CommandeController {

    // Service contenant la logique métier des commandes
    private final CommandeService commandeService;

    // Injection du service via le constructeur
    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    /**
     * Récupérer toutes les commandes
     */
    @GetMapping
    public List<Commande> getCommandes() {
        return commandeService.getAll();
    }

    /**
     * Récupérer une commande par son id
     */
    @GetMapping("{id}")
    public Commande getCommande(@PathVariable("id") Integer id) {
        return commandeService.getById(id);
    }

    /**
     * Récupérer les commandes d’un client donné
     */
    @GetMapping("client/{clientId}")
    public List<Commande> getCommandesByClientId(
            @PathVariable("clientId") Integer clientId) {
        return commandeService.getCommandesByClientId(clientId);
    }

    /**
     * Récupérer les commandes d’un restaurant donné
     */
    @GetMapping("restaurant/{restaurantId}")
    public List<Commande> getCommandesByRestaurantId(
            @PathVariable("restaurantId") Integer restaurantId) {
        return commandeService.getCommandesByRestaurantId(restaurantId);
    }

    /**
     * Ajouter une nouvelle commande
     */
    @PostMapping
    public void addCommande(@RequestBody CommandeRegistrationRequest request) {
        commandeService.add(request);
    }

    /**
     * Mettre à jour une commande existante
     */
    @PutMapping("{id}")
    public void updateCommande(
            @PathVariable("id") Integer id,
            @RequestBody CommandeUpdateRequest request) {
        commandeService.updateCommande(id, request);
    }
}

