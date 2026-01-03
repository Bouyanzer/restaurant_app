package com.bouyanzer.restaurant;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * Entité représentant un restaurant
 */
@Entity
@Table(name = "restaurant")
public class Restaurant {

    // Clé primaire du restaurant
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    // Nom du restaurant
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    // Description du restaurant
    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    // Adresse du restaurant
    @NotBlank
    @Column(name = "address", nullable = false)
    private String address;

    // Quartier / district du restaurant
    @NotBlank
    @Column(name = "district", nullable = false)
    private String district;

    // Numéro de téléphone du restaurant
    @NotBlank
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    // Constructeur par défaut requis par JPA
    public Restaurant() {
    }

    // Constructeur principal
    public Restaurant(String name,
                      String description,
                      String address,
                      String district,
                      String phoneNumber) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.district = district;
        this.phoneNumber = phoneNumber;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // equals / hashCode / toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && Objects.equals(address, that.address)
                && Objects.equals(district, that.district)
                && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, address, district, phoneNumber);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

