package com.bouyanzer.client;

public record ClientUpdateRequest(
        String firstname, String lastname, String email, String address, String phoneNumber
) {
}
