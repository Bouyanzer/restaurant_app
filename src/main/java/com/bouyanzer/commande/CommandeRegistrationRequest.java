package com.bouyanzer.commande;

import com.bouyanzer.plat.Plat;
import com.bouyanzer.client.Client;
import com.bouyanzer.restaurant.Restaurant;

import java.util.List;

public record CommandeRegistrationRequest(
        Client client, List<Plat> plats, String address, Commande.DeliveryType deliveryType
) {
}
