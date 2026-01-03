package com.bouyanzer.plat;

import com.bouyanzer.restaurant.Restaurant;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entité représentant un plat proposé par un restaurant
 */
@Entity
@Table(name = "plat")
public class Plat {

    // Clé primaire du plat
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    // Nom du plat
    @Column(name = "name", nullable = false)
    private String name;

    // Description du plat
    @Column(name = "description", nullable = false)
    private String description;

    // Catégorie du plat (ex: entrée, plat, dessert)
    @Column(name = "category", nullable = false)
    private String category;

    // Prix du plat
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    // Indique si le plat est végétarien
    @Column(name = "is_vegetarian", nullable = false)
    private boolean isVegetarian;

    // Restaurant auquel appartient le plat
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    // Constructeur par défaut requis par JPA
    public Plat() {
    }

    // Constructeur principal
    public Plat(String name,
                String description,
                String category,
                BigDecimal price,
                boolean isVegetarian,
                Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.isVegetarian = isVegetarian;
        this.restaurant = restaurant;
    }

    // Getters et setters

    public Integer getId() {
        return id;
    }

    // Setter utile pour tests ou mapping
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // Représentation textuelle du plat
    @Override
    public String toString() {
        return "Plat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", isVegetarian=" + isVegetarian +
                '}';
    }

    // Méthodes equals / hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plat plat = (Plat) o;
        return isVegetarian == plat.isVegetarian
                && Objects.equals(id, plat.id)
                && Objects.equals(name, plat.name)
                && Objects.equals(description, plat.description)
                && Objects.equals(category, plat.category)
                && Objects.equals(price, plat.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category, price, isVegetarian);
    }
}

