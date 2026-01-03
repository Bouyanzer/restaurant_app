package com.bouyanzer.plat;

import com.bouyanzer.restaurant.Restaurant;

public record PlatUpdateRequest(
        String name, String description, String category, double price, boolean isVegetarian, Restaurant restaurant
) {
}
