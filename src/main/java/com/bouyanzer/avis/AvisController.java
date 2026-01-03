package com.bouyanzer.avis;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller REST qui gère les requêtes HTTP liées aux avis
 */
@RestController
@RequestMapping("api/v1/aviss") // URL de base pour les avis
public class AvisController {

    // Service contenant la logique métier des avis
    private final AvisService avisService;

    // Injection du service via le constructeur
    public AvisController(AvisService avisService) {
        this.avisService = avisService;
    }

    /**
     * Récupérer tous les avis
     */
    @GetMapping
    public List<Avis> getAviss() {
        return avisService.getAll();
    }

    /**
     * Récupérer un avis par son id
     */
    @GetMapping("{id}")
    public Avis getAvis(@PathVariable("id") Integer id) {
        return avisService.getById(id);
    }

    /**
     * Récupérer les avis d’un restaurant donné
     */
    @GetMapping("restaurant/{restaurantId}")
    public List<Avis> getAvissByRestaurantId(
            @PathVariable("restaurantId") Integer restaurantId) {
        return avisService.getAvissByRestaurantId(restaurantId);
    }

    /**
     * Récupérer les avis par date
     */
    @GetMapping("date/{date}")
    public List<Avis> getAvissByDate(@PathVariable("date") LocalDateTime date) {
        return avisService.getAvissByDate(date);
    }

    /**
     * Récupérer les avis d’un client donné
     */
    @GetMapping("user/{clientId}")
    public List<Avis> getAvissByClientId(
            @PathVariable("clientId") Integer clientId) {
        return avisService.getAvissByClientId(clientId);
    }

    /**
     * Ajouter un nouvel avis
     */
    @PostMapping
    public void addAvis(@RequestBody AvisRegistrationRequest request) {
        avisService.add(request);
    }

    /**
     * Mettre à jour un avis existant
     */
    @PutMapping("{id}")
    public void updateAvis(
            @PathVariable("id") Integer id,
            @RequestBody AvisUpdateRequest request) {
        avisService.update(id, request);
    }

    /**
     * Supprimer un avis par son id
     */
    @DeleteMapping("{id}")
    public void deleteAvis(@PathVariable("id") Integer id) {
        avisService.deleteById(id);
    }
}

