package com.bouyanzer.client;

import com.bouyanzer.exception.DuplicateResourceException;
import com.bouyanzer.exception.NotValidResourceException;
import com.bouyanzer.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class ClientService {
    private final ClientDAO clientDAO;

    @Autowired
    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public List<Client> getAllClients() {
        return clientDAO.getAllClients();
    }

    public Client getClientById(Integer id) {
        return clientDAO.getClientById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client with id [%s] not found".formatted(id))
                );
    }

    public Client getClientByEmail(String email) {
        return clientDAO.getClientByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client with email [%s] not found".formatted(email))
                );
    }

    private void checkEmailExists(String email) {
        if (clientDAO.existsClientWithEmail(email)) {
            throw new DuplicateResourceException("Email already taken");
        }
    }

    public void addClient(ClientRegistrationRequest clientRegistrationRequest) {
        if (clientRegistrationRequest.firstname() == null || clientRegistrationRequest.lastname() == null || clientRegistrationRequest.email() == null) {
            throw new NotValidResourceException("Missing data");
        }

        String firstname = clientRegistrationRequest.firstname();
        String lastname = clientRegistrationRequest.lastname();
        String email = clientRegistrationRequest.email();
        checkEmailExists(email);
        String address = clientRegistrationRequest.address();
        String phoneNumber = clientRegistrationRequest.phoneNumber();

        Client client = new Client(firstname, lastname, email, address, phoneNumber);
        clientDAO.addClient(client);
    }

    public void deleteClient(Integer id) {
        Client client = clientDAO.getClientById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client with id [%s] not found".formatted(id))
                );

        clientDAO.deleteClient(client);
    }

    private boolean checkEmailValid(String email, String emailRegex) {
        return Pattern.compile(emailRegex)
                .matcher(email)
                .matches();
    }

    public void updateClient(Integer id, ClientUpdateRequest clientUpdateRequest) {
        Client client = clientDAO.getClientById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client with id [%s] not found".formatted(id))
                );

        if (clientUpdateRequest.firstname() != null) {
            String firstname = clientUpdateRequest.firstname();
            client.setFirstname(firstname);
        }

        if (clientUpdateRequest.lastname() != null) {
            String lastname = clientUpdateRequest.lastname();
            client.setLastname(lastname);
        }

        if (clientUpdateRequest.email() != null && !client.getEmail().equals(clientUpdateRequest.email())) {
            String email = clientUpdateRequest.email();
            checkEmailExists(email);
            if (!checkEmailValid(email, "^(.+)@(\\S+)$")) {
                throw new NotValidResourceException("Email not valid");
            }
            client.setEmail(email);
        }

        if (clientUpdateRequest.address() != null) {
            String address = clientUpdateRequest.address();
            client.setAddress(address);
        }

        if (clientUpdateRequest.phoneNumber() != null) {
            String phoneNumber = clientUpdateRequest.phoneNumber();
            client.setPhoneNumber(phoneNumber);
        }

        clientDAO.updateClient(client);
    }
}
