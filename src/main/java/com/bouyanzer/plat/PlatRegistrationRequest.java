package com.bouyanzer.plat;

import com.bouyanzer.restaurant.Restaurant;

public record PlatRegistrationRequest(
        Restaurant restaurant, String name, String description, String category, double price, boolean isVegetarian
) {
}
