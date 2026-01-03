package com.bouyanzer.paiement;

import com.bouyanzer.commande.Commande;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entité représentant un paiement lié à une commande
 */
@Entity
@Table(name = "paiement")
public class Paiement {

    /**
     * Méthodes de paiement possibles
     */
    public enum PaiementMethod {
        BLIK,
        CREDIT_CARD,
        CASH,
        OTHER
    }

    /**
     * Statuts possibles du paiement
     */
    public enum PaiementStatus {
        IN_PROGRESS,
        PAID,
        CANCELLED,
        ERROR
    }

    // Clé primaire du paiement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    // Commande associée au paiement (relation one-to-one)
    @OneToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    // Date et heure du paiement
    @Column(name = "paiement_time", nullable = false)
    private LocalDateTime paiementTime;

    // Méthode de paiement utilisée
    @Enumerated(EnumType.STRING)
    @Column(name = "paiement_method", nullable = false)
    private PaiementMethod paiementMethod;

    // Statut actuel du paiement
    @Enumerated(EnumType.STRING)
    @Column(name = "paiement_status", nullable = false)
    private PaiementStatus paiementStatus;

    // Constructeur par défaut requis par JPA
    public Paiement() {
    }

    // Constructeur principal
    public Paiement(Commande commande, PaiementMethod paiementMethod) {
        this.commande = commande;
        this.paiementTime = LocalDateTime.now();          // Initialisation automatique
        this.paiementMethod = paiementMethod;
        this.paiementStatus = PaiementStatus.IN_PROGRESS; // Statut par défaut
    }

    // Getters / Setters

    public Integer getId() {
        return id;
    }

    // Setter utilisé principalement pour tests ou mapping
    public void setId(Integer paiementId) {
        this.id = paiementId;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public LocalDateTime getPaiementTime() {
        return paiementTime;
    }

    public void setPaiementTime(LocalDateTime paiementTime) {
        this.paiementTime = paiementTime;
    }

    public PaiementStatus getPaiementStatus() {
        return paiementStatus;
    }

    public void setPaiementStatus(PaiementStatus paiementStatus) {
        this.paiementStatus = paiementStatus;
    }

    // equals / hashCode / toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paiement paiement)) return false;
        return Objects.equals(id, paiement.id)
                && Objects.equals(commande, paiement.commande)
                && Objects.equals(paiementTime, paiement.paiementTime)
                && paiementMethod == paiement.paiementMethod
                && paiementStatus == paiement.paiementStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commande, paiementTime, paiementMethod, paiementStatus);
    }

    @Override
    public String toString() {
        return "Paiement{" +
                "id=" + id +
                ", commande=" + commande +
                ", paiementTime=" + paiementTime +
                ", paiementMethod=" + paiementMethod +
                ", paiementStatus=" + paiementStatus +
                '}';
    }
}

