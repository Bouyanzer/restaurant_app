package com.bouyanzer.client;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Entité représentant un client
 */
@Entity
@Table(
        name = "client",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "client_email_unique",
                        columnNames = "email"
                )
        }
)
public class Client {

    // Clé primaire du client
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    // Prénom du client
    @Column(name = "firstname", nullable = false)
    private String firstname;

    // Nom du client
    @Column(name = "lastname", nullable = false)
    private String lastname;

    // Email du client (unique)
    @Column(name = "email", nullable = false)
    private String email;

    // Adresse du client
    @Column(name = "address", nullable = false)
    private String address;

    // Numéro de téléphone du client
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    // Constructeur par défaut requis par JPA
    public Client() {
    }

    // Constructeur avec paramètres
    public Client(String firstname, String lastname, String email,
                  String address, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getters et setters

    public Integer getId() {
        return id;
    }

    // Setter utilisé principalement pour les tests ou les DTO
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Méthode de comparaison des clients
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id)
                && Objects.equals(firstname, client.firstname)
                && Objects.equals(lastname, client.lastname)
                && Objects.equals(email, client.email)
                && Objects.equals(address, client.address)
                && Objects.equals(phoneNumber, client.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, email, address, phoneNumber);
    }

    // Représentation textuelle du client
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

