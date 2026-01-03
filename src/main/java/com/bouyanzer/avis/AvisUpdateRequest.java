package com.bouyanzer.avis;

public record AvisUpdateRequest(
        String name,
        Integer rating,
        String description
) {
}
