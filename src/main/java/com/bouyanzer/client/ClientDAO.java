package com.bouyanzer.client;

import java.util.List;
import java.util.Optional;

/**
 * Interface DAO pour la gestion des clients
 * Définit les opérations d’accès aux données
 */
public interface ClientDAO {

    /**
     * Récupérer tous les clients
     */
    List<Client> getAllClients();

    /**
     * Ajouter un nouveau client
     */
    void addClient(Client client);

    /**
     * Récupérer un client par son id
     */
    Optional<Client> getClientById(Integer id);

    /**
     * Récupérer un client par son email
     */
    Optional<Client> getClientByEmail(String email);

    /**
     * Vérifier si un client existe avec un email donné
     */
    boolean existsClientWithEmail(String email);

    /**
     * Supprimer un client
     */
    void deleteClient(Client client);

    /**
     * Mettre à jour les informations d’un client
     */
    void updateClient(Client client);

    /**
     * Récupérer un client par son id (méthode générique)
     */
    Optional<Client> getById(Integer clientId);

    /**
     * Vérifier l’existence d’un client par son id
     */
    boolean existsById(Integer clientId);
}

