package com.bouyanzer.paiement;

public record PaiementUpdateRequest(
        Paiement.PaiementStatus paiementStatus) {
}
