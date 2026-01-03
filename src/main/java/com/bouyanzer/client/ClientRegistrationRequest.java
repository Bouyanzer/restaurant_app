package com.bouyanzer.client;

public record ClientRegistrationRequest(
        String firstname, String lastname, String email, String address, String phoneNumber
) {
}
