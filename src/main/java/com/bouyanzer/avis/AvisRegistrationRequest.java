package com.bouyanzer.avis;

import com.bouyanzer.client.Client;
import com.bouyanzer.plat.Plat;
import com.bouyanzer.commande.Commande;
import com.bouyanzer.restaurant.Restaurant;

import java.time.LocalDateTime;

public record AvisRegistrationRequest(
        Client client,
        Restaurant restaurant,
        Commande commande,
        String name,
        Integer rating,
        String description
) {
}
