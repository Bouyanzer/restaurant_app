package com.bouyanzer.avis;

import com.bouyanzer.client.Client;
import com.bouyanzer.commande.Commande;
import com.bouyanzer.restaurant.Restaurant;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entité représentant un avis laissé par un client pour un restaurant
 */
@Entity
@Table(name = "aviss") // Nom de la table dans la base de données
public class Avis {

    // Clé primaire de l'entité
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    // Client qui a laissé l’avis
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    // Restaurant concerné par l’avis
    @ManyToOne(optional = false)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    // Commande liée à l’avis
    @OneToOne(optional = false)
    @JoinColumn(name = "commande_id")
    private Commande commande;

    // Nom ou titre de l’avis
    @Column(name = "name", nullable = false)
    private String name;

    // Note donnée par le client
    @Column(name = "rating", nullable = false)
    private Integer rating;

    // Description détaillée de l’avis
    @Column(name = "description", nullable = false)
    private String description;

    // Date et heure de création de l’avis
    @Column(name = "avis_time", nullable = false)
    private LocalDateTime avisTime;

    // Constructeur par défaut requis par JPA
    public Avis() {
    }

    // Constructeur avec paramètres
    public Avis(Client client, Restaurant restaurant, Commande commande,
                String name, Integer rating, String description) {
        this.client = client;
        this.restaurant = restaurant;
        this.commande = commande;
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.avisTime = LocalDateTime.now(); // Initialisation automatique de la date
    }

    // Getters et setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAvisTime() {
        return avisTime;
    }

    public void setAvisTime(LocalDateTime avisTime) {
        this.avisTime = avisTime;
    }

    // Comparaison logique des objets Avis
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avis)) return false;
        Avis avis = (Avis) o;
        return Objects.equals(id, avis.id)
                && Objects.equals(client, avis.client)
                && Objects.equals(restaurant, avis.restaurant)
                && Objects.equals(name, avis.name)
                && Objects.equals(rating, avis.rating)
                && Objects.equals(description, avis.description)
                && Objects.equals(avisTime, avis.avisTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, restaurant, name, rating, description, avisTime);
    }

    // Représentation textuelle de l’objet
    @Override
    public String toString() {
        return "Avis{" +
                "id=" + id +
                ", client=" + client +
                ", restaurant=" + restaurant +
                ", commande=" + commande +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", avisTime=" + avisTime +
                '}';
    }
}

