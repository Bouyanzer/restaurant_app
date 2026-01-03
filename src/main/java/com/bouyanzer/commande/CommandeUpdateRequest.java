package com.bouyanzer.commande;

import com.bouyanzer.plat.Plat;
import java.util.List;

public record CommandeUpdateRequest(
        List<Plat> plats, String address, Commande.DeliveryType deliveryType, Commande.CommandeStatus status
) {
}
