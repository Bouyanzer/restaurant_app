package com.bouyanzer.paiement;

import com.bouyanzer.commande.Commande;

public record PaiementRegistrationRequest(
        Commande commande, Paiement.PaiementMethod paiementMethod
) {
}
