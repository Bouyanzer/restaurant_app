package com.bouyanzer.plat;

import com.bouyanzer.exception.NotValidResourceException;
import com.bouyanzer.exception.ResourceNotFoundException;
import com.bouyanzer.restaurant.Restaurant;
import com.bouyanzer.restaurant.RestaurantDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlatService {
    private final PlatDAO platDAO;
    private final RestaurantDAO restaurantDAO;

    public PlatService(@Qualifier("plat-jpa") PlatDAO platDAO, RestaurantDAO restaurantDAO) {
        this.platDAO = platDAO;
        this.restaurantDAO = restaurantDAO;
    }

    public List<Plat> getAllPlat() {
        return platDAO.getAllPlat();
    }

    public Plat getPlatById(Integer id) {
        return platDAO.getPlatById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Plat with id [%s] not found".formatted(id))
                );
    }

    public List<Plat> getPlatByRestaurantID(Integer restaurantID) {
        return platDAO.getPlatByRestaurantID(restaurantID);
    }

    public List<Plat> getPlatByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return platDAO.getPlatByPriceRange(minPrice, maxPrice);
    }

    public List<Plat> getPlatByCategory(String category) {
        return platDAO.getPlatByCategory(category);
    }

    public List<Plat> getVegetarianPlat() {
        return platDAO.getVegetarianPlat();
    }

    public void addPlat(PlatRegistrationRequest platRegistrationRequest) {
        String name = platRegistrationRequest.name();
        String description = platRegistrationRequest.description();
        String category = platRegistrationRequest.category();
        BigDecimal price = BigDecimal.valueOf(platRegistrationRequest.price());
        boolean isVegetarian = platRegistrationRequest.isVegetarian();
        Restaurant restaurant = platRegistrationRequest.restaurant();

        if (name == null || description == null || category == null || price.compareTo(BigDecimal.ZERO) <= 0 || restaurant == null) {
            throw new NotValidResourceException("Missing or invalid data");
        }


        Integer restaurantId = restaurant.getId();
        if (!restaurantDAO.existsById(restaurantId)) {
            throw new ResourceNotFoundException("Restaurant with id [%s] not found".formatted(restaurantId));
        }

        Plat plat = new Plat(name, description, category, price, isVegetarian, restaurant);
        platDAO.addPlat(plat);
    }

    public void deletePlat(Integer id) {
        Plat plat = platDAO.getPlatById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Plat with id [%s] not found".formatted(id))
                );

        platDAO.deletePlat(plat);
    }

    public List<Plat> getPlatByName(String name) {
        return platDAO.getPlatByName(name);
    }

    public void updatePlat(Integer id, PlatUpdateRequest platUpdateRequest) {
        Plat plat = platDAO.getPlatById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Plat with id [%s] not found".formatted(id))
                );

        if (platUpdateRequest.price() != 0) {
            BigDecimal price = BigDecimal.valueOf(platUpdateRequest.price());

            if (price.compareTo(BigDecimal.ZERO) <= 0) {
                throw new NotValidResourceException("Price must be a positive value");
            }

            plat.setPrice(price);
        }

        if (platUpdateRequest.name() != null) {
            plat.setName(platUpdateRequest.name());
        }

        if (platUpdateRequest.description() != null) {
            plat.setDescription(platUpdateRequest.description());
        }

        if (platUpdateRequest.category() != null) {
            plat.setCategory(platUpdateRequest.category());
        }

        if (platUpdateRequest.restaurant() != null) {
            plat.setRestaurant(platUpdateRequest.restaurant());
        }

        platDAO.updatePlat(plat);
    }
}
