package com.bouyanzer.client;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implémentation JPA du DAO Client
 * Utilise ClientRepository pour accéder à la base de données
 */
@Repository
public class ClientJPADataAccessService implements ClientDAO {

    // Repository Spring Data JPA pour l’entité Client
    private final ClientRepository clientRepository;

    // Injection du repository via le constructeur
    public ClientJPADataAccessService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Récupérer tous les clients
     */
    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Ajouter un nouveau client
     */
    @Override
    public void addClient(Client client) {
        clientRepository.save(client);
    }

    /**
     * Récupérer un client par son id
     */
    @Override
    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }

    /**
     * Récupérer un client par son email
     */
    @Override
    public Optional<Client> getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    /**
     * Vérifier l’existence d’un client avec un email donné
     */
    @Override
    public boolean existsClientWithEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    /**
     * Supprimer un client
     */
    @Override
    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    /**
     * Mettre à jour un client
     */
    @Override
    public void updateClient(Client client) {
        clientRepository.save(client);
    }

    /**
     * Récupérer un client par son id (méthode générique)
     */
    @Override
    public Optional<Client> getById(Integer clientId) {
        return clientRepository.findById(clientId);
    }

    /**
     * Vérifier l’existence d’un client par son id
     */
    @Override
    public boolean existsById(Integer clientId) {
        return clientRepository.existsById(clientId);
    }
}

