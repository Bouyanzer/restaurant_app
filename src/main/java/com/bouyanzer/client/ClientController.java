package com.bouyanzer.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour la gestion des clients
 */
@RestController
@RequestMapping("api/v1/clients") // URL de base pour les clients
public class ClientController {

    // Service contenant la logique métier liée aux clients
    private final ClientService clientService;

    // Injection du service via le constructeur
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Récupérer la liste de tous les clients
     */
    @GetMapping
    public List<Client> getClients() {
        return clientService.getAllClients();
    }

    /**
     * Récupérer un client par son id
     */
    @GetMapping("{id}")
    public Client getClient(@PathVariable("id") Integer id) {
        return clientService.getClientById(id);
    }

    /**
     * Récupérer un client à partir de son email
     */
    @GetMapping("email/{email}")
    public Client getClientByEmail(@PathVariable("email") String email) {
        return clientService.getClientByEmail(email);
    }

    /**
     * Ajouter un nouveau client
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addClient(@RequestBody ClientRegistrationRequest request) {
        clientService.addClient(request);
    }

    /**
     * Supprimer un client par son id
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable("id") Integer id) {
        clientService.deleteClient(id);
    }

    /**
     * Mettre à jour les informations d’un client
     */
    @PutMapping("{id}")
    public void updateClient(
            @PathVariable("id") Integer id,
            @RequestBody ClientUpdateRequest request
    ) {
        clientService.updateClient(id, request);
    }
}

