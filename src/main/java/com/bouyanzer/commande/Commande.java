package com.bouyanzer.commande;

import com.bouyanzer.client.Client;
import com.bouyanzer.plat.Plat;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entité représentant une commande passée par un client
 */
@Entity
@Table(name = "commandes")
public class Commande {

    /**
     * Statut possible d'une commande
     */
    public enum CommandeStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        CANCELLED,
        AWAITING_PICKUP,
        READY_FOR_SHIPPING,
        SHIPPED,
        DELIVERED
    }

    /**
     * Type de livraison
     */
    public enum DeliveryType {
        PICKUP,
        SHIPPING,
        OTHER
    }

    // Clé primaire
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    // Client qui a passé la commande
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // Liste des plats de la commande (relation many-to-many)
    // Important : FetchType.LAZY est le défaut ici
    @ManyToMany
    @JoinTable(
            name = "commande_plat",
            joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "plat_id")
    )
    private List<Plat> plats = new ArrayList<>();

    /**
     * Total calculé via une formule SQL (somme des prix des plats liés à la commande)
     * COALESCE permet de retourner 0 si aucun plat n'est trouvé
     */
    @Formula(
            "(SELECT COALESCE(SUM(f.price), 0) " +
            "FROM plat f " +
            "JOIN commande_plat cp ON f.id = cp.plat_id " +
            "WHERE cp.commande_id = id)"
    )
    private BigDecimal totalAmount;

    // Adresse de livraison / retrait
    @Column(name = "address", nullable = false)
    private String address;

    // Type de livraison (en String dans la BDD)
    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_type", nullable = false)
    private DeliveryType deliveryType;

    // Date et heure de création de la commande
    @Column(name = "commande_time", nullable = false)
    private LocalDateTime commandeTime;

    // Indique si la commande est payée
    @Column(name = "is_paid", nullable = false)
    private boolean isPaid;

    // Statut actuel de la commande
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CommandeStatus status;

    // Constructeur par défaut requis par JPA
    public Commande() {
    }

    // Constructeur principal
    public Commande(Client client, List<Plat> plats, String address, DeliveryType deliveryType) {
        this.client = client;
        this.plats = plats;
        this.address = address;
        this.deliveryType = deliveryType;
        this.commandeTime = LocalDateTime.now(); // Initialisation automatique
        this.isPaid = false;                     // Par défaut : non payée
        this.status = CommandeStatus.PENDING;    // Par défaut : en attente
    }

    // Getters / Setters

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public List<Plat> getPlats() {
        return plats;
    }

    public String getAddress() {
        return address;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public LocalDateTime getCommandeTime() {
        return commandeTime;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public CommandeStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setPlats(List<Plat> plats) {
        this.plats = plats;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public void setStatus(CommandeStatus status) {
        this.status = status;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    // --- CORRECTION CRITIQUE CI-DESSOUS ---
    // Suppression de 'plats' dans equals, hashCode et toString pour éviter le chargement Lazy

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commande commande = (Commande) o;
        // On compare uniquement l'ID (clé primaire)
        return Objects.equals(id, commande.id);
    }

    @Override
    public int hashCode() {
        // On utilise le hashCode de la classe pour éviter de toucher aux champs non initialisés
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", client=" + client +
                // ", plats=" + plats +  <-- RETIRÉ (Cause de l'erreur LazyInitializationException)
                ", address='" + address + '\'' +
                ", deliveryType=" + deliveryType +
                ", commandeTime=" + commandeTime +
                ", isPaid=" + isPaid +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
