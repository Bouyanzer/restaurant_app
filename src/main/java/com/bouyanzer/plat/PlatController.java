package com.bouyanzer.plat;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controller REST pour la gestion des plats
 */
@RestController
@RequestMapping("api/v1/plat") // URL de base pour les plats
public class PlatController {

    // Service contenant la logique métier des plats
    private final PlatService platService;

    // Injection du service via le constructeur
    public PlatController(PlatService platService) {
        this.platService = platService;
    }

    /**
     * Récupérer tous les plats
     */
    @GetMapping
    public List<Plat> getAllPlat() {
        return platService.getAllPlat();
    }

    /**
     * Récupérer un plat par son id
     */
    @GetMapping("{id}")
    public Plat getPlat(@PathVariable("id") Integer id) {
        return platService.getPlatById(id);
    }

    /**
     * Récupérer les plats par nom
     */
    @GetMapping("name/{name}")
    public List<Plat> getPlatByName(@PathVariable("name") String name) {
        return platService.getPlatByName(name);
    }

    /**
     * Récupérer les plats d’un restaurant donné
     */
    @GetMapping("restaurant/{restaurantID}")
    public List<Plat> getPlatByRestaurant(
            @PathVariable("restaurantID") Integer restaurantID) {
        return platService.getPlatByRestaurantID(restaurantID);
    }

    /**
     * Récupérer les plats dans une fourchette de prix
     */
    @GetMapping("price-range")
    public List<Plat> getPlatByPriceRange(
            @RequestParam("minPrice") BigDecimal minPrice,
            @RequestParam("maxPrice") BigDecimal maxPrice
    ) {
        return platService.getPlatByPriceRange(minPrice, maxPrice);
    }

    /**
     * Récupérer les plats par catégorie
     */
    @GetMapping("category/{category}")
    public List<Plat> getPlatByCategory(@PathVariable("category") String category) {
        return platService.getPlatByCategory(category);
    }

    /**
     * Récupérer uniquement les plats végétariens
     */
    @GetMapping("type/vegetarian")
    public List<Plat> getVegetarianPlat() {
        return platService.getVegetarianPlat();
    }

    /**
     * Ajouter un nouveau plat
     */
    @PostMapping
    public void addPlat(@RequestBody PlatRegistrationRequest request) {
        platService.addPlat(request);
    }

    /**
     * Supprimer un plat par son id
     */
    @DeleteMapping("{id}")
    public void deletePlat(@PathVariable("id") Integer id) {
        platService.deletePlat(id);
    }

    /**
     * Mettre à jour un plat existant
     */
    @PutMapping("{id}")
    public void updatePlat(
            @PathVariable("id") Integer id,
            @RequestBody PlatUpdateRequest request
    ) {
        platService.updatePlat(id, request);
    }
}

